package com.drg.rustandrevolt

import com.drg.rustandrevolt.entities.Character
import com.drg.rustandrevolt.usecases.EngineersUseCase
import com.drg.rustandrevolt.usecases.MachinesUseCase
import com.drg.rustandrevolt.usecases.RebelsUseCase
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random



@Singleton
class RandomEnemyAI @Inject constructor(
    private val rebelsUseCase : RebelsUseCase,
    private val machinesUseCase : MachinesUseCase,
    private val engineersUseCase : EngineersUseCase
) {
        fun getRandomEnemyAI() :Character {

            val rebelsList : MutableList<Character> = rebelsUseCase.getAll()
            val machinesList : MutableList<Character> = machinesUseCase.getAll()
            val engineersList : MutableList<Character> = engineersUseCase.getAll()
            val allCharactersList : MutableList<Character> = mutableListOf()

            allCharactersList.addAll(rebelsList)
            allCharactersList.addAll(machinesList)
            allCharactersList.addAll(engineersList)

            return allCharactersList.get(Random.nextInt(0,allCharactersList.count() + 1))
        }
}