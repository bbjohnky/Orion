package com.eclipse.sim

import grails.test.mixin.TestFor
import com.eclipse.sim.factory.HumanShipFactory
import grails.test.mixin.Mock
import com.eclipse.sim.part.ShipPart
import com.eclipse.sim.part.Computers.ElectronComputer
import com.eclipse.sim.part.Cannons.IonCannon
import com.eclipse.sim.part.Hulls.Hull
import com.eclipse.sim.part.Drives.NuclearDrive
import com.eclipse.sim.part.Sources.NuclearSource
import com.eclipse.sim.Blueprints.Ship
import com.eclipse.sim.ship.ShipService

@TestFor(ContenderService)
@Mock([Ship,Contender,ShipPart,ElectronComputer,IonCannon,Hull,
       NuclearDrive,NuclearSource, Battle])
class ContenderServiceTests {
    def contenderService
    def shipService
    def contender
    def ship
    def battle

    void setUp() {
        contenderService = new ContenderService()
        shipService = new ShipService()
        contenderService.shipService = shipService
        ship = HumanShipFactory.cruiser
        contender = new Contender()
        battle = new Battle()
        battle.addToContenders(contender)
    }

    void test_addShip() {
        contenderService.addShipsToContender(contender,ship)

        def contenderFind = Contender.findById(contender.id)
        assert contenderFind.equals(contender)
        assert contenderFind.ships.size().equals(1)
        assert contenderFind.ships.asList()[0].equals(ship)
        assert contenderFind.ships.asList()[0].getClass().equals(Ship)
    }

    void test_declareWinner() {
        contenderService.addShipsToContender(contender,ship)
        contenderService.declareWinner(contender)

        def contenderFind = Contender.findById(contender.id)
        assert contenderFind.equals(contender)
        assert contenderFind.roundsWon.equals(1)
        assert contenderFind.shipsRemainingWhenWinner.equals(1)
    }

    void test_allShipsDestroyed_false() {
        contender = contenderService.addShipsToContender(contender,ship)
        contender.ships.asList()[0].damageTaken = 1

        assert !contenderService.allShipsDestroyed(contender)
    }

    void test_allShipsDestroyed_true() {
        contender = contenderService.addShipsToContender(contender,ship)
        contender.ships.asList()[0].damageTaken = 2

        assert contenderService.allShipsDestroyed(contender)
    }

    void test_resetDamage() {
        contender = contenderService.addShipsToContender(contender,ship)
        contender.ships.asList()[0].damageTaken = 2
        assert contenderService.allShipsDestroyed(contender)

        contender = contenderService.resetShipDamage(contender)
        assert !contenderService.allShipsDestroyed(contender)
    }

    void test_numberOfRemainingActiveShips() {
        def newShip = HumanShipFactory.interceptor
        contender = contenderService.addShipsToContender(contender,ship)
        contender = contenderService.addShipsToContender(contender,newShip)
        assert contenderService.numberOfRemainingActiveShips(contender).equals(2)

        contender.ships.asList()[0].damageTaken = 2
        assert contenderService.numberOfRemainingActiveShips(contender).equals(1)
    }
}
