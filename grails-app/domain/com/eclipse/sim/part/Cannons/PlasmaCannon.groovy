package com.eclipse.sim.part.Cannons

import com.eclipse.sim.part.ShipPart


class PlasmaCannon extends ShipPart{
    PlasmaCannon() {
        this.damagePerShot = 2
        this.powerModifier = -2
        this.techRequired = true
    }
}
