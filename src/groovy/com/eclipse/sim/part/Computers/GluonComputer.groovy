package com.eclipse.sim.part.Computers

import com.eclipse.sim.part.ShipPart


class GluonComputer extends ShipPart {
    GluonComputer() {
        this.bonusToHit = 3
        this.initiativeBonus = 2
        this.powerModifier = -2
        this.techRequired = true
    }
}
