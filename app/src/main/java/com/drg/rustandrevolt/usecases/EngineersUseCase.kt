package com.drg.rustandrevolt.usecases

import com.drg.rustandrevolt.entities.Character
import com.drg.rustandrevolt.entities.Engineer
import com.drg.rustandrevolt.repositories.EngineersRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EngineersUseCase @Inject constructor(
    private val engineersRepository: EngineersRepository
) {
    fun getAll() : MutableList<Character>{
        val engineersNameList = engineersRepository.getEngineers()
        val engineersList : MutableList<Character> = mutableListOf()

        for (engineerName in engineersNameList){
            engineersList.add(Engineer(engineerName))
        }

        return engineersList
    }
}