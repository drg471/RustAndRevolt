package com.drg.rustandrevolt.usecases

import com.drg.rustandrevolt.domain.Character
import com.drg.rustandrevolt.domain.Engineer
import com.drg.rustandrevolt.repositories.EngineersRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EngineersUseCase @Inject constructor(
    private val engineersRepository: EngineersRepository
) {
    fun getAll() : MutableList<Character>{
        val engineersDataList = engineersRepository.getEngineers()
        val engineersList : MutableList<Character> = mutableListOf()

        for (engineerDataList in engineersDataList){
            engineersList.add(
                Engineer(
                    engineerDataList.get(0),
                    engineerDataList.get(1),
                    engineerDataList.get(2),
                    engineerDataList.get(3)
                ))
        }

        return engineersList
    }
}