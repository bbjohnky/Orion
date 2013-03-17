package com.eclipse.sim.Blueprints

class Ship {
    def partList = []
    int maxParts = 0
    int powerModifier = 0
    int bonusToHit = 0
    int initiativeBonus = 0
    boolean driveRequired = true

    // Combat variables
    int damageTaken = 0
}
