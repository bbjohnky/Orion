package com.eclipse.sim.part.Drives

import com.eclipse.sim.part.ShipPart


class NuclearDrive extends ShipPart {
    NuclearDrive() {
        this.moveBonus = 1
        this.initiativeBonus = 1
        this.powerModifier = -1
    }
}
