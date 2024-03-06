package com.drg.rustandrevolt.domain.service

import com.drg.rustandrevolt.domain.Character
import com.drg.rustandrevolt.domain.usecases.GetAllEngineeringUseCase
import com.drg.rustandrevolt.domain.usecases.MachinesUseCase
import com.drg.rustandrevolt.domain.usecases.RebelsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RandomEnemyAI @Inject constructor(
    private val rebelsUseCase: RebelsUseCase,
    private val machinesUseCase: MachinesUseCase,
    private val getAllEngineeringUseCase: GetAllEngineeringUseCase
) {
    suspend fun getRandomEnemyAI(): Character = withContext(Dispatchers.IO) {
        val rebelsList: MutableList<Character> = rebelsUseCase.getAll()

        val machinesList: MutableList<Character> = machinesUseCase.getAll()
        val engineersList: MutableList<Character> = getAllEngineeringUseCase.getAll()
        val allCharactersList: MutableList<Character> = mutableListOf()

        allCharactersList.addAll(rebelsList)
        allCharactersList.addAll(machinesList)
        allCharactersList.addAll(engineersList)

        return@withContext allCharactersList.random()
    }
}