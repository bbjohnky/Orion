package com.eclipse.sim.part.Missiles

import com.eclipse.sim.part.ShipPart


class PlasmaMissiles extends ShipPart {
    PlasmaMissiles() {
        this.damagePerShot = 2
        this.numberOfShots = 2
        this.preemptiveAttack = true
    }
}
