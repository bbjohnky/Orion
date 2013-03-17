package com.eclipse.sim.Blueprints.Human

import com.eclipse.sim.part.Cannons.IonCannon
import com.eclipse.sim.part.Drives.NuclearDrive
import com.eclipse.sim.part.Sources.NuclearSource
import com.eclipse.sim.part.Hulls.Hull
import com.eclipse.sim.part.Computers.ElectronComputer
import com.eclipse.sim.Blueprints.Ship


class Cruiser extends Ship {
    Cruiser() {
        this.maxParts = 6
        this.initiativeBonus = 1
        partList.add(new ElectronComputer())
        partList.add(new IonCannon())
        partList.add(new Hull())
        partList.add(new NuclearDrive())
        partList.add(new NuclearSource())
    }
}
