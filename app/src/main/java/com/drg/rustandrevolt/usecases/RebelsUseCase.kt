package com.drg.rustandrevolt.usecases

import com.drg.rustandrevolt.entities.Character
import com.drg.rustandrevolt.entities.Rebel
import com.drg.rustandrevolt.repositories.RebelsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RebelsUseCase @Inject constructor(
    private val rebelsRepository : RebelsRepository
) {
    fun getAll() : MutableList<Character>{
        val rebelsNameList = rebelsRepository.getRebels()
        val rebelsList : MutableList<Character> = mutableListOf()

        for (rebelName in rebelsNameList){
            rebelsList.add(Rebel(rebelName))
        }

        return rebelsList
    }
}