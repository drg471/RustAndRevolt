package com.drg.rustandrevolt.domain

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Player @Inject constructor (var name : String) {

    private val id : Int = 0
    var score : Int = 0
        get() = field
        set (value){
            field = when {
                value < score -> score
                else -> value
            }
        }
}