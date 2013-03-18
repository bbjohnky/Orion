package com.eclipse.sim.ship

import com.eclipse.sim.Blueprints.Ship
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import com.eclipse.sim.part.ShipPart


class ShipService {
    private static final Logger logger = LoggerFactory.getLogger(ShipService.class)

    int getTotalBonusToHit(Ship ship) {
        int totalBonusToHit = ship.bonusToHit
        ship.partList.each { part ->
            totalBonusToHit += part.bonusToHit
        }
        return totalBonusToHit
    }

    int getTotalInitiativeBonus(Ship ship) {
        int totalInitiativeBonus = ship.initiativeBonus
        ship.partList.each { part ->
            totalInitiativeBonus += part.initiativeBonus
        }
        return totalInitiativeBonus
    }

    int getNumberOfTechRequired(Ship ship) {
        def techsRequired = []
        ship.partList.each { part ->
            if(part.techRequired) {
                techsRequired.add(part.getClass().toString())
            }
        }
        techsRequired.unique()
        return techsRequired.size()
    }

    boolean shipDesignIsValid(Ship ship) {
         return shipPowerIsValid(ship) && shipPartSizeIsValid(ship) && shipHasValidNumberOfDrives(ship)
    }

    boolean shipHasValidNumberOfDrives(Ship ship) {
        boolean driveFound = false
        ship.partList.each { part ->
            if(part.moveBonus > 0) {
                driveFound = true
            }
        }
        return (ship.driveRequired && driveFound) || (!ship.driveRequired && !driveFound)
    }

    boolean shipPartSizeIsValid(Ship ship) {
        return ship.partList.size() <= ship.maxParts
    }

    boolean shipPowerIsValid(Ship ship) {
        int netPower = ship.powerModifier
        ship.partList.each { part ->
            netPower += part.powerModifier
        }

        return (netPower > -1)
    }

    boolean shipDestroyed(Ship ship) {
        int totalHitsRequiredToDestroy = 1
        ship.partList.each { part ->
            totalHitsRequiredToDestroy += part.damageAbsorb
        }
        return ship.damageTaken >= totalHitsRequiredToDestroy
    }

    Ship addOrReplacePartToShip(Ship ship, Class newPart = null, Class partToReplace = null) {
        ship = removePartFromShip(ship,partToReplace)
        ship = addPartToShip(ship,newPart)

        if(shipDesignIsValid(ship)) {
            logger.debug("Added {} to {}",newPart, ship)
            return ship
        }
        else {
            logger.debug("Adding {} to {} would result in invalid ship design.",newPart.getName(), ship)
            ship = removePartFromShip(ship,newPart)
            ship = addPartToShip(ship,partToReplace)
            return ship
        }
    }

    Ship addPartToShip(Ship ship, Class partClass) {
        if(partClass) {
            ship.partList.add(partClass.newInstance())
        }
        return ship
    }

    Ship removePartFromShip(Ship ship, Class partClass) {
        if(partClass.equals(null)) {
            logger.debug("The part to remove from ship was null.  Ignoring.")
            return ship
        }

        if(ship.partList.remove(getPartFromShip(ship,partClass))) {
            logger.debug("Part {} removed from ship", partClass.getName())
            return ship
        }
        else {
            logger.debug("Errors when removing part {}", partClass.getName())
            return ship
        }
    }

    ShipPart getPartFromShip(Ship ship, Class partClass) {
        ShipPart partToReturn
        ship.partList.each {part ->
            if(part.class.equals(partClass)) {
                logger.debug("Found {} on ship", partClass.getName())
                partToReturn = part
            }
        }
        return partToReturn
    }

    Ship assignDamage(Ship ship, int damage) {
        ship.damageTaken += damage
        return ship
    }

    void printShipPartsForShip(Ship ship) {
        logger.info("Printing ship parts for {}",ship.getClass())
        ship.partList.each {part ->
            logger.info("Part: {}",part.getClass())
        }
    }
}
