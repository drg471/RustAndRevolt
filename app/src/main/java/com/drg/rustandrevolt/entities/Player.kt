package com.drg.rustandrevolt.entities

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Player @Inject constructor (name : String) {

    var name: String = ""
        get() = field
        set(value){
            field = value
        }

    private val id : String = ""
    private var score : Int = 0

    lateinit var currentGameCharacter : Character
}