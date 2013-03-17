package com.eclipse.sim.Blueprints.Orion

import com.eclipse.sim.Blueprints.Ship
import com.eclipse.sim.part.Cannons.IonCannon
import com.eclipse.sim.part.Hulls.Hull
import com.eclipse.sim.part.Computers.ElectronComputer
import com.eclipse.sim.part.Shields.GaussShield


class Starbase extends Ship {
    Starbase() {
        this.maxParts = 4
        this.initiativeBonus = 5
        this.powerModifier = 3
        this.driveRequired = false
        partList.add(IonCannon)
        partList.add(Hull)
        partList.add(Hull)
        partList.add(ElectronComputer)
        partList.add(GaussShield)
    }
}
