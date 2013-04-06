package com.eclipse.sim.Blueprints

import com.eclipse.sim.part.ShipPart
import com.eclipse.sim.Contender

class Ship {
    def race
    def type
    def maxParts = 0
    def powerModifier = 0
    def bonusToHit = 0
    def initiativeBonus = 0
    def driveRequired = true

    // Combat variables
    def damageTaken = 0

    static hasMany = [partList: ShipPart]
    static belongsTo = [contender: Contender]

    static constraints = {
        race bindable: true
        type bindable: true
        maxParts bindable: true
        powerModifier bindable: true
        bonusToHit bindable: true
        initiativeBonus bindable: true
        driveRequired bindable: true
        contender nullable: true
    }

    String toString() {
        return  "Class:"+this.getClass() + "\n"+
                "DamageTaken:"+this.damageTaken + " maxParts:"+this.maxParts+"\n"+
                "powerModifier:"+this.powerModifier+" bonusToHit:"+this.bonusToHit+"\n"+
                "InitiativeBonus:"+this.initiativeBonus+" partList:"+this.partList
    }
}
