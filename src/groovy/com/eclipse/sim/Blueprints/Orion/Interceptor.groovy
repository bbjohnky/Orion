package com.eclipse.sim.Blueprints.Orion

import com.eclipse.sim.Blueprints.Ship
import com.eclipse.sim.part.Cannons.IonCannon
import com.eclipse.sim.part.Drives.NuclearDrive
import com.eclipse.sim.part.Sources.NuclearSource
import com.eclipse.sim.part.Shields.GaussShield


class Interceptor extends Ship {
    Interceptor() {
        this.maxParts = 4
        this.initiativeBonus = 3
        this.powerModifier = 1
        partList.add(IonCannon)
        partList.add(NuclearDrive)
        partList.add(NuclearSource)
        partList.add(GaussShield)
    }
}
