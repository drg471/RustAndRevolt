package com.drg.rustandrevolt.entities

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Player @Inject constructor (var name : String) {

    private val id : String = ""
    var score : Int = 0
        get() = field
        set (value){
            field = when {
                value < score -> score
                else -> value
            }
        }

    lateinit var currentGameCharacter : Character
}