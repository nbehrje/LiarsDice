package com.example.liarsdice

import kotlin.random.Random

class PlayerState {
    var dice = intArrayOf(0,0,0,0,0)

    init {
        for (i in 0..4) {
            dice.set(i, Random.nextInt(1,7))
        }
    }
}