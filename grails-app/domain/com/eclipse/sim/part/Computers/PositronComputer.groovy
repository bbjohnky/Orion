package com.eclipse.sim.part.Computers

import com.eclipse.sim.part.ShipPart


class PositronComputer extends ShipPart {
    PositronComputer() {
        this.bonusToHit = 2
        this.initiativeBonus = 1
        this.powerModifier = -1
        this.techRequired = true
    }
}
