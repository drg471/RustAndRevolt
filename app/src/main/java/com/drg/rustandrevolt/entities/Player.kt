package com.drg.rustandrevolt.entities

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Player @Inject constructor (var name : String) {

    private val id : String = ""
    private var score : Int = 0

    lateinit var currentGameCharacter : Character
}