package com.eclipse.sim.factory

import grails.test.GrailsUnitTestCase
import com.eclipse.sim.Blueprints.Ship
import com.eclipse.sim.ship.ShipType
import com.eclipse.sim.factory.OrionShipFactory
import com.eclipse.sim.factory.Race


class OrionShipFactoryTests extends GrailsUnitTestCase {
    void test_new_interceptor() {
        mockDomain(Ship)
        def ship = OrionShipFactory.interceptor

        assert ship.partList.size().equals(4)
        assert ship.maxParts.equals(4)
        assert ship.initiativeBonus.equals(3)
        assert ship.powerModifier.equals(1)
        assert ship.race.equals(Race.ORION)
        assert ship.type.equals(ShipType.INTERCEPTOR)

        ship.save(flush: true)
        def shipFound = Ship.findById(ship.id)
        assert shipFound.equals(ship)
        assert shipFound instanceof Ship
    }

    void test_new_cruiser() {
        mockDomain(Ship)
        def ship = OrionShipFactory.cruiser

        assert ship.partList.size().equals(6)
        assert ship.maxParts.equals(6)
        assert ship.initiativeBonus.equals(2)
        assert ship.powerModifier.equals(2)
        assert ship.race.equals(Race.ORION)
        assert ship.type.equals(ShipType.CRUISER)

        ship.save(flush: true)
        def shipFound = Ship.findById(ship.id)
        assert shipFound.equals(ship)
        assert shipFound instanceof Ship
    }

    void test_new_dreadnought() {
        mockDomain(Ship)
        def ship = OrionShipFactory.dreadnought

        assert ship.partList.size().equals(8)
        assert ship.maxParts.equals(8)
        assert ship.initiativeBonus.equals(1)
        assert ship.powerModifier.equals(3)
        assert ship.race.equals(Race.ORION)
        assert ship.type.equals(ShipType.DREADNOUGHT)

        ship.save(flush: true)
        def shipFound = Ship.findById(ship.id)
        assert shipFound.equals(ship)
        assert shipFound instanceof Ship
    }

    void test_new_starbase() {
        mockDomain(Ship)
        def ship = OrionShipFactory.starbase

        assert ship.partList.size().equals(5)
        assert ship.maxParts.equals(5)
        assert ship.initiativeBonus.equals(5)
        assert ship.powerModifier.equals(3)
        assert !ship.driveRequired
        assert ship.race.equals(Race.ORION)
        assert ship.type.equals(ShipType.STARBASE)

        ship.save(flush: true)
        def shipFound = Ship.findById(ship.id)
        assert shipFound.equals(ship)
        assert shipFound instanceof Ship
    }
}
