package com.eclipse.sim.part.Drives

import com.eclipse.sim.part.ShipPart


class TachyonDrive extends ShipPart {
    TachyonDrive() {
        this.moveBonus = 3
        this.initiativeBonus = 3
        this.powerModifier = -3
    }
}
