package com.drg.rustandrevolt.repositories

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerRepository @Inject constructor() {
    fun getPlayer() : String{
        return "Player1"
    }
}