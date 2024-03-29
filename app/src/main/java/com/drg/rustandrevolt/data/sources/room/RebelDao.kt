package com.drg.rustandrevolt.data.sources.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RebelDao {
    @Query("SELECT * FROM RebelEntity")
    fun getAll(): MutableList<RebelEntity>

    @Insert
    fun insertAll( rebelList : List<RebelEntity>)

    @Insert
    fun insert(rebel : RebelEntity)

    @Delete
    fun delete(rebel: RebelEntity)
}