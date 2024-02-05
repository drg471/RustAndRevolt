package com.drg.rustandrevolt.usecases

import com.drg.rustandrevolt.domain.Character
import com.drg.rustandrevolt.domain.Engineer
import com.drg.rustandrevolt.domain.Rebel
import com.drg.rustandrevolt.repositories.RebelsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
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
                "Rebelde One",
                "imagedflt",
                "imageusrdflt",
                "imagedflt"
            )
        )
        rebelsRepository.saveRebel(
            Rebel(
                "Rebelde 2",
                "imagedflt2",
                "imageusrdflt",
                "imagedflt2"
            )
        )
    }
}