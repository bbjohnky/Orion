package com.eclipse.sim.factory

import com.eclipse.sim.part.Computers.ElectronComputer
import com.eclipse.sim.part.Cannons.IonCannon
import com.eclipse.sim.part.Hulls.Hull
import com.eclipse.sim.part.Drives.NuclearDrive
import com.eclipse.sim.part.Sources.NuclearSource
import com.eclipse.sim.Blueprints.Ship
import com.eclipse.sim.ship.ShipType


class HumanShipFactory {
    static Ship getInterceptor() {
        return new Ship(race: Race.HUMAN,
                        type: ShipType.INTERCEPTOR,
                        maxParts: 4,
                        initiativeBonus: 2,
                        partList:[new IonCannon(),
                            new NuclearDrive(),
                            new NuclearSource()])
    }

    static Ship getCruiser() {
        return new Ship(race: Race.HUMAN,
                        type: ShipType.CRUISER,
                        maxParts: 6,
                        initiativeBonus: 1,
                        partList:[new ElectronComputer(),
                            new IonCannon(),
                            new Hull(),
                            new NuclearDrive(),
                            new NuclearSource() ])
    }

    static Ship getDreadnought() {
        return new Ship(race: Race.HUMAN,
                        type: ShipType.DREADNOUGHT,
                        maxParts: 8,
                        partList:[new ElectronComputer(),
                            new IonCannon(),
                            new IonCannon(),
                            new Hull(),
                            new Hull(),
                            new NuclearDrive(),
                            new NuclearSource() ])
    }

    static Ship getStarbase() {
        return new Ship(race: Race.HUMAN,
                        type: ShipType.STARBASE,
                        maxParts: 5,
                        initiativeBonus: 4,
                        powerModifier: 3,
                        driveRequired: false,
                        partList:[new IonCannon(),
                            new Hull(),
                            new Hull(),
                            new ElectronComputer() ])
    }
}
