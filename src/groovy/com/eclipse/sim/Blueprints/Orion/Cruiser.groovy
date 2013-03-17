package com.eclipse.sim.Blueprints.Orion

import com.eclipse.sim.Blueprints.Ship
import com.eclipse.sim.part.Computers.ElectronComputer
import com.eclipse.sim.part.Cannons.IonCannon
import com.eclipse.sim.part.Hulls.Hull
import com.eclipse.sim.part.Drives.NuclearDrive
import com.eclipse.sim.part.Sources.NuclearSource
import com.eclipse.sim.part.Shields.GaussShield


class Cruiser extends Ship {
    Cruiser() {
        this.maxParts = 6
        this.initiativeBonus = 2
        this.powerModifier = 2
        partList.add(ElectronComputer)
        partList.add(IonCannon)
        partList.add(Hull)
        partList.add(NuclearDrive)
        partList.add(NuclearSource)
        partList.add(GaussShield)
    }
}
