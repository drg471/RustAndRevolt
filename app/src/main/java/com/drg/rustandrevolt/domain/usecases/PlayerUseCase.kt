package com.drg.rustandrevolt.domain.usecases

import com.drg.rustandrevolt.domain.Player
import com.drg.rustandrevolt.data.repositories.PlayerRepository
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