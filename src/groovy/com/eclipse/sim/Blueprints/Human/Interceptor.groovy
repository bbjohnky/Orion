package com.eclipse.sim.Blueprints.Human

import com.eclipse.sim.part.Cannons.IonCannon
import com.eclipse.sim.part.Drives.NuclearDrive
import com.eclipse.sim.part.Sources.NuclearSource
import com.eclipse.sim.Blueprints.Ship



class Interceptor extends Ship{
    Interceptor() {
        this.maxParts = 4
        this.initiativeBonus = 2
        partList.add(IonCannon)
        partList.add(NuclearDrive)
        partList.add(NuclearSource)
    }
}
