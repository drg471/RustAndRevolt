package com.drg.rustandrevolt.repositories

import com.drg.rustandrevolt.entities.Player
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerRepository @Inject constructor() {

    private var player : Player = Player("Player 1")
    fun setPlayer(player : Player) {
        this.player = player
    }

    fun getPlayer() : Player{
        return player
    }
}