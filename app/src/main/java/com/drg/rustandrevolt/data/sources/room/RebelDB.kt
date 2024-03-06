package com.drg.rustandrevolt.data.sources.room

import androidx.room.Database
import androidx.room.RoomDatabase

const val DATABASE_VERSION = 1

@Database(entities = [RebelEntity::class], version = DATABASE_VERSION)
abstract class RebelDB : RoomDatabase() {
    abstract fun rebelDao(): RebelDao
}