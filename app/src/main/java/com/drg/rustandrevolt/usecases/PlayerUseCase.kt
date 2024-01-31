package com.drg.rustandrevolt.usecases

import com.drg.rustandrevolt.entities.Character
import com.drg.rustandrevolt.entities.Player
import com.drg.rustandrevolt.repositories.PlayerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerUseCase @Inject constructor(
    private val playerRepository: PlayerRepository
) {
    fun setCharacter (character : Character) {
        val player : Player = playerRepository.getPlayer()
        player.currentGameCharacter = character
        playerRepository.setPlayer(player)
    }

    fun getPlayer () : Player {
        return playerRepository.getPlayer()
    }
}