package com.eclipse.sim.part.Shields

import com.eclipse.sim.part.ShipPart


class PhaseShield extends ShipPart {
    PhaseShield() {
        this.modifierToAttacker = -2
        this.powerModifier = -1
    }
}
