package com.eclipse.sim.factory

import com.eclipse.sim.part.Cannons.IonCannon
import com.eclipse.sim.part.Drives.NuclearDrive
import com.eclipse.sim.part.Sources.NuclearSource
import com.eclipse.sim.part.Computers.ElectronComputer
import com.eclipse.sim.part.Hulls.Hull
import com.eclipse.sim.part.Shields.GaussShield
import com.eclipse.sim.Blueprints.Ship
import com.eclipse.sim.ship.ShipType

class OrionShipFactory {
    static Ship getInterceptor() {
        return new Ship(race: Race.ORION,
                        type: ShipType.INTERCEPTOR,
                        maxParts: 4,
                        initiativeBonus: 3,
                        powerModifier: 1,
                        partList:[new IonCannon(),
                            new NuclearDrive(),
                            new NuclearSource(),
                            new GaussShield() ])
    }

    static Ship getCruiser() {
        return new Ship(race: Race.ORION,
                        type: ShipType.CRUISER,
                        maxParts: 6,
                        initiativeBonus: 2,
                        powerModifier: 2,
                        partList:[new ElectronComputer(),
                            new IonCannon(),
                            new Hull(),
                            new NuclearDrive(),
                            new NuclearSource(),
                            new GaussShield() ])
    }

    static Ship getDreadnought() {
        return new Ship(race: Race.ORION,
                        type: ShipType.DREADNOUGHT,
                        maxParts: 8,
                        initiativeBonus: 1,
                        powerModifier: 3,
                        partList:[new ElectronComputer(),
                            new IonCannon(),
                            new IonCannon(),
                            new Hull(),
                            new Hull(),
                            new NuclearDrive(),
                            new NuclearSource(),
                            new GaussShield() ])
    }

    static Ship getStarbase() {
        return new Ship(race: Race.ORION,
                        type: ShipType.STARBASE,
                        maxParts: 5,
                        initiativeBonus: 5,
                        powerModifier: 3,
                        driveRequired: false,
                        partList:[new IonCannon(),
                            new Hull(),
                            new Hull(),
                            new ElectronComputer(),
                            new GaussShield() ])
    }
}
