package com.eclipse.sim

import com.eclipse.sim.Blueprints.Ship


class Contender {
    boolean isAttacker = false
    int roundsWon = 0
    int shipsRemainingWhenWinner = 0

    static hasMany = [ships: Ship]
    static belongsTo = [battle: Battle]

    String toString() {
        return "Contender id:"+id+" Attacker:"+isAttacker+
                " Won:"+roundsWon+" shipsRemaining:"+shipsRemainingWhenWinner+"\n"+
                "Ships:"+ships
    }
}
