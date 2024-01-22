package com.drg.rustandrevolt.repositories

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EngineersRepository @Inject constructor() {
    fun getEngineers() : List<String>{
        val engineersNameList : List<String> = listOf("Ingeniero 1", "Ingeniero 2")
        return engineersNameList
    }
}