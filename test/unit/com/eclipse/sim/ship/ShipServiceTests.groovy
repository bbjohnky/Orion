package com.eclipse.sim.ship

import grails.test.mixin.TestFor
import com.eclipse.sim.Blueprints.Ship
import com.eclipse.sim.part.Hulls.ImprovedHull
import com.eclipse.sim.part.Hulls.Hull
import com.eclipse.sim.part.Cannons.AntimatterCannon
import com.eclipse.sim.part.ShipPart
import com.eclipse.sim.part.Sources.TachyonSource
import com.eclipse.sim.part.Sources.NuclearSource
import com.eclipse.sim.part.Drives.NuclearDrive
import com.eclipse.sim.part.Cannons.IonCannon
import com.eclipse.sim.part.Cannons.PlasmaCannon
import com.eclipse.sim.part.Shields.GaussShield
import grails.test.mixin.Mock
import com.eclipse.sim.part.Computers.ElectronComputer
import com.eclipse.sim.factory.HumanShipFactory

@TestFor (ShipService)
@Mock([Ship, ShipPart, ElectronComputer,
        IonCannon, Hull, NuclearDrive, NuclearSource,
        ImprovedHull, TachyonSource, AntimatterCannon,
        PlasmaCannon])
class ShipServiceTests {
    def shipService
    def ship

    void setUp() {
        ship = HumanShipFactory.cruiser
        shipService = new ShipService()
    }

    void test_getTotalBonusToHit() {
        def totalBonus = shipService.getTotalBonusToHit(ship)

        assert totalBonus.equals(1)
    }

    void test_getTotalShipShield() {
        ship.partList.add(new GaussShield())

        def shield = shipService.getShipShields(ship)

        assert shield.equals(-1)
    }

    void test_getTotalInitiativeBonus() {
        def totalIntBonus = shipService.getTotalInitiativeBonus(ship)

        assert totalIntBonus.equals(2)
    }

    void test_validShipDesign() {
        def validShipDesign = shipService.shipDesignIsValid(ship)

        assert validShipDesign
    }

    void test_invalidShipDesign_tooManyParts() {
        ship.partList.add(new Hull())
        ship.partList.add(new ImprovedHull())

        def validShipDesign = shipService.shipDesignIsValid(ship)

        assert !validShipDesign
    }

    void test_invalidShipDesign_tooMuchEnergyUsage() {
        ship.partList.add(new AntimatterCannon())

        def validShipDesign = shipService.shipDesignIsValid(ship)

        assert !validShipDesign
    }

    void test_addNewPartToShip_valid() {
        ship = shipService.addOrReplacePartToShip(ship,ImprovedHull)
        ShipPart part = shipService.getPartFromShip(ship,ImprovedHull)

        assert part instanceof ImprovedHull
        assert ship.partList.contains(part)
        assert ship.partList.size().equals(6)
        assert ship instanceof Ship
    }

    void test_addPartOnShip_invalid() {
        ship = shipService.addOrReplacePartToShip(ship,AntimatterCannon)
        ShipPart part = shipService.getPartFromShip(ship,AntimatterCannon)

        assert part.equals(null)
        assert ship.partList.size().equals(5)
    }

    void test_replacePartOnShip_valid() {
        ship = shipService.addOrReplacePartToShip(ship,TachyonSource, NuclearSource)
        ShipPart part = shipService.getPartFromShip(ship,TachyonSource)

        assert part instanceof TachyonSource
        assert ship.partList.contains(part)
        assert ship.partList.size().equals(5)
        assert ship instanceof Ship
    }

    void test_replacePartOnShip_invalid() {
        ship = shipService.addOrReplacePartToShip(ship,AntimatterCannon,NuclearDrive)
        ShipPart part = shipService.getPartFromShip(ship,AntimatterCannon)

        assert part.equals(null)
        assert ship.partList.size().equals(5)

        part = shipService.getPartFromShip(ship,NuclearDrive)

        assert part instanceof NuclearDrive
        assert ship.partList.contains(part)
        assert ship.partList.size().equals(5)
        assert ship instanceof Ship
    }

    void test_shipDestroyed_should_be_false() {
        ship = shipService.addOrReplacePartToShip(ship,ImprovedHull)
        ship = shipService.assignDamage(ship,2)

        assert !shipService.shipDestroyed(ship)
    }

    void test_shipDestroyed_should_be_true() {
        ship = shipService.addOrReplacePartToShip(ship,ImprovedHull)
        ship = shipService.assignDamage(ship,4)

        assert shipService.shipDestroyed(ship)
    }

    void test_getTotalTechRequired() {
        ship = shipService.addOrReplacePartToShip(ship,TachyonSource,NuclearSource)
        ship = shipService.addOrReplacePartToShip(ship,PlasmaCannon)
        ship = shipService.addOrReplacePartToShip(ship,PlasmaCannon,IonCannon)

        def totalNumberOfTechs = shipService.getNumberOfTechRequired(ship)
        shipService.printShipPartsForShip(ship)
        assert totalNumberOfTechs.equals(2)
    }
}