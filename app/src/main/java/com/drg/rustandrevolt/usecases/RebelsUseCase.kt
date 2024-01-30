package com.drg.rustandrevolt.usecases

import com.drg.rustandrevolt.entities.Character
import com.drg.rustandrevolt.entities.Engineer
import com.drg.rustandrevolt.entities.Rebel
import com.drg.rustandrevolt.repositories.RebelsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RebelsUseCase @Inject constructor(
    private val rebelsRepository : RebelsRepository
) {
    fun getAll() : MutableList<Character>{
        val rebelsDataList = rebelsRepository.getRebels()
        val rebelsList : MutableList<Character> = mutableListOf()

        for (rebelDataList in rebelsDataList){
            rebelsList.add(
                Engineer(
                    rebelDataList.get(0),
                    rebelDataList.get(1),
                    rebelDataList.get(2),
                    rebelDataList.get(3)
                )
            )
        }

        return rebelsList
    }
}