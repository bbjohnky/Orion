package com.eclipse.sim.part.Drives

import com.eclipse.sim.part.ShipPart


class FusionDrive extends ShipPart {
    FusionDrive() {
        this.moveBonus = 2
        this.initiativeBonus = 2
        this.powerModifier = -2
        this.techRequired = true
    }
}
