package com.drg.rustandrevolt.usecases

import com.drg.rustandrevolt.domain.Character
import com.drg.rustandrevolt.domain.Player
import com.drg.rustandrevolt.repositories.PlayerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerUseCase @Inject constructor(
    private val playerRepository: PlayerRepository
) {
    fun getPlayer () : Player {
        return playerRepository.getPlayer()
    }
}