package com.eclipse.sim

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.codehaus.groovy.grails.validation.exceptions.ConstraintException
import javax.xml.bind.ValidationException
import grails.validation.ValidationException
import com.eclipse.sim.Blueprints.Ship
import com.eclipse.sim.part.ShipPart


class BattleService {
    private static final Logger logger = LoggerFactory.getLogger(BattleService.class)

    Random random = new Random()
    def contenderService
    def shipService

    void saveBattle(Battle battle) {
        if(battle.validate()) {
            battle.save(flush: true)
        }
        else {
            battle.errors.allErrors.each {
                logger.error("Contender Save error:{}",it)
            }
        }
    }

    Battle addContender(Battle battle, Contender contender) {
        battle.addToContenders(contender)
        saveBattle(battle)
        return battle
    }

    void validateBattle(Battle battle) {
        if(!battle.contenders.size().equals(2)) {
            logger.error("Battle has {} contenders.", battle.contenders.size())
            throw new ConstraintException("Battle contains invalid number of contenders.  Should be 2.")
        }

        def battleContainsAttacker = false
        def battleContainsDefender = false
        battle.contenders.toList().each { contender ->
            if(contender.isAttacker) {
                battleContainsAttacker = true
            }
            else {
                battleContainsDefender = true
            }
        }

        if(!battleContainsAttacker) {
            logger.error("Battle does not contain an attacker.")
            throw new ConstraintException("Battle does not contain an attacker.")
        }
        if(!battleContainsDefender) {
            logger.error("Battle does not contain an defender.")
            throw new ConstraintException("Battle does not contain a defender.")
        }
    }

    void simulateBattle(Battle battle, int numberOfSimulations) {
        validateBattle(battle)

        for (i in 0..numberOfSimulations) {
            simulateOneBattle(battle)
            resetContenderDamage(battle)
        }
    }

    void simulateOneBattle(Battle battle) {
        int round = 0

        while(!allAttackersDestroyed(battle)
                && !allDefendersDestroyed(battle)) {
            round++
            simulateRound(battle)
        }
        updateWinners(battle)
    }

    void simulateRound(battle) {
        def initiativeList = getInitiativeList(battle)
        def rollList = []
        boolean attackRollListIsAgainstAttackers = true
        initiativeList.each {Ship ship ->
            if(!shipService.shipDestroyed(ship)) {
                if(isRollListProcessRequired(attackRollListIsAgainstAttackers,ship.contender.isAttacker)) {
                    battle = assignDamage(battle, rollList, attackRollListIsAgainstAttackers)
                    rollList.clear()
                    attackRollListIsAgainstAttackers = !ship.contender.isAttacker
                }
                rollList.add(getAttackResults(ship))
            }
        }
    }

    def getAttackResults(Ship ship) {
        def attackList = []
        ship.partList.each {ShipPart part ->
            for(i in 0..part.numberOfShots) {
                Attack attack = new Attack()
                attack.hit = random.nextInt(6) + 1 + ship.bonusToHit
                attack.damage = part.damagePerShot
                attackList.add(attack)
            }
        }
        return attackList
    }

    Battle assignDamage(Battle battle, def rollList, boolean attackRollListIsAgainstAttackers) {
        //Move this to new service
        return battle
    }

    boolean isRollListProcessRequired(boolean currentAttackRollIsAgainstAttackers, boolean shipIsAttacker) {
        if(!currentAttackRollIsAgainstAttackers) {
            return shipIsAttacker
        }
        else {
            return !shipIsAttacker
        }
    }

    def getInitiativeList(Battle battle) {
        def initiativeList = contenderService.getRemainingActiveShips(battle.contenders.asList()[0])
        initiativeList.addAll(contenderService.getRemainingActiveShips(battle.contenders.asList()[1]))
        initiativeList.sort {a,b ->
            a.initiativeBonus <=> b.initiativeBonus ?: (a.contender.isAttacker ? -1 : 1)
        }
        return initiativeList
    }

    void updateWinners(battle) {
        if(allAttackersDestroyed(battle)) {
            Contender contender = battle.contenders.find {it.isAttacker} as Contender
            contenderService.declareWinner(contender)
        }
        else if(allDefendersDestroyed(battle)) {
            Contender contender = battle.contenders.find {!it.isAttacker} as Contender
            contenderService.declareWinner(contender)
        }
        else {
            logger.error("Unable to update winners for battle.")
        }
    }

    boolean allAttackersDestroyed(Battle battle) {
        def contender = battle.contenders.find {it.isAttacker}

        return contenderService.allShipsDestroyed(contender)
    }

    boolean allDefendersDestroyed(Battle battle) {
        def contender = battle.contenders.find {!it.isAttacker}

        return contenderService.allShipsDestroyed(contender)
    }

    void resetContenderDamage(Battle battle) {
        battle.contenders.toList().each {contender ->
            contenderService.resetShipDamage(contender)
        }
        saveBattle(battle)
    }
}
