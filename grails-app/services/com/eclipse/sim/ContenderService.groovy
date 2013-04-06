package com.eclipse.sim

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import com.eclipse.sim.Blueprints.Ship


class ContenderService {
    private static final Logger logger = LoggerFactory.getLogger(ContenderService.class)
    def shipService

    void saveContender(Contender contender) {
        if(contender.validate()) {
            contender.save(flush: true)
        }
        else {
            contender.errors.allErrors.each {
                logger.error("Contender Save error:{}",it)
            }
        }
    }

    Contender declareWinner(Contender contender) {
        contender.shipsRemainingWhenWinner += numberOfRemainingActiveShips(contender)
        contender.roundsWon++
        saveContender(contender)
        return contender
    }

    Contender resetShipDamage(Contender contender) {
        contender.ships.each {ship ->
            ship.damageTaken = 0
        }
        saveContender(contender)
        return contender
    }

    Contender addShipsToContender(Contender contender, Ship ship) {
        contender.addToShips(ship)
        saveContender(contender)
        return contender
    }

    def getRemainingActiveShips(Contender contender) {
        def shipList = []
        contender.ships.asList().each { ship ->
            if(!shipService.shipDestroyed(ship)) {
                shipList.add(ship)
            }
        }
        return shipList
    }

    int numberOfRemainingActiveShips(Contender contender) {
        return getRemainingActiveShips(contender).size()
    }

    boolean allShipsDestroyed(Contender contender) {
        return numberOfRemainingActiveShips(contender).equals(0)
    }
}
