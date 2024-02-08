package com.drg.rustandrevolt.repositories

import android.util.Log
import com.drg.rustandrevolt.domain.Character
import com.drg.rustandrevolt.domain.Rebel
import com.drg.rustandrevolt.domain.toEntity
import com.drg.rustandrevolt.room.RebelDao
import com.drg.rustandrevolt.room.toDomain
import javax.inject.Inject
import javax.inject.Singleton

//REPOSITORIO DE BASE DE DATOS ROOM
@Singleton
class RebelsRepository @Inject constructor(
    private val rebelDao: RebelDao
){
    fun getRebels() : MutableList<Character>{
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