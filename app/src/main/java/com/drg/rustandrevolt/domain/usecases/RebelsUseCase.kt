package com.drg.rustandrevolt.domain.usecases

import com.drg.rustandrevolt.domain.Character
import com.drg.rustandrevolt.domain.Rebel
import com.drg.rustandrevolt.data.repositories.RebelsRepository
import javax.inject.Inject
import javax.inject.Singleton

class RebelsUseCase @Inject constructor(
    private val rebelsRepository : RebelsRepository
) {
    fun getAll() : MutableList<Character>{
        val rebelsDataList = rebelsRepository.getRebels()
        return rebelsDataList
    }

    fun delete (rebel : Rebel){
        rebelsRepository.deleteRebel(rebel)
    }

    fun save(){
        val rebelsDataList = getAll()

        if (rebelsDataList.isNotEmpty()){
            for (rebel in rebelsDataList){
                delete(rebel as Rebel)
            }
        }

        rebelsRepository.saveRebel(
            Rebel(
                "Luna Steamweaver",
                "rebeldem",
                "rebeldemcombplayer",
                "rebeldemcomb"
            )
        )
        rebelsRepository.saveRebel(
            Rebel(
                "Captain Gearheart",
                "rebeldeh",
                "rebeldehcombplayer",
                "rebeldehcomb"
            )
        )
    }
}