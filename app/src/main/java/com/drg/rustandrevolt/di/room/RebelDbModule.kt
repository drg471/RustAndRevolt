package com.drg.rustandrevolt.di.room

import android.content.Context
import androidx.room.Room
import com.drg.rustandrevolt.data.sources.room.RebelDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RebelDbModule {
    const val DATABASE_NAME = "RebelsRoom.db"

    @Provides
    fun providesRebelsDbRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        RebelDB::class.java, DATABASE_NAME
    ).build()

    @Provides
    fun providesRebelDao(rebelsDB: RebelDB) = rebelsDB.rebelDao()
}