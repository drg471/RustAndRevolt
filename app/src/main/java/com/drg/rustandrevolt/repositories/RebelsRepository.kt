package com.drg.rustandrevolt.repositories

import android.util.Log
import com.drg.rustandrevolt.domain.Character
import com.drg.rustandrevolt.domain.Rebel
import com.drg.rustandrevolt.domain.toEntity
import com.drg.rustandrevolt.room.RebelDao
import com.drg.rustandrevolt.room.toDomain
import javax.inject.Inject
import javax.inject.Singleton

//EL REPOSITORIO SERIA PARA IR A BASE DE DATOS
@Singleton
class RebelsRepository @Inject constructor(
    private val rebelDao: RebelDao
){
    fun getRebels() : MutableList<Character>{
        /*val rebelsDataList : MutableList<MutableList<String>> = mutableListOf()

        val rebel1DataList : MutableList<String> = mutableListOf(
            "Rebelde 1",
            "imagedflt",
            "imageusrdflt",
            "imagedflt"
        )

        val rebel2DataList : MutableList<String> = mutableListOf(
            "Rebelde 2",
            "imagedflt2",
            "imageusrdflt",
            "imagedflt2"
        )

        rebelsDataList.add(rebel1DataList)
        rebelsDataList.add(rebel2DataList)

        return rebelsDataList*/

        val rebelsMutableList : MutableList<Character> = mutableListOf()

        for (rebel in rebelDao.getAll()){
            rebelsMutableList.add(rebel.toDomain())
        }

        return rebelsMutableList
    }

    fun saveRebel(rebel: Rebel) {
        rebelDao.insert(rebel.toEntity())
    }

    fun deleteRebel(rebel: Rebel) {
        rebelDao.delete(rebel.toEntity())
    }

}