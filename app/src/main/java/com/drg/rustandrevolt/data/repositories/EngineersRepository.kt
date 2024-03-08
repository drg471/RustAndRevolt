package com.drg.rustandrevolt.data.repositories

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EngineersRepository @Inject constructor() {
    fun getEngineers() : MutableList<MutableList<String>>{
        val engineersDataList : MutableList<MutableList<String>> = mutableListOf()

        val engineer1DataList : MutableList<String> = mutableListOf(
            "Profesor Cogsworth",
            "ingeniero",
            "ingenierocombplayer",
            "ingenierocomb"
        )

        val engineer2DataList : MutableList<String> = mutableListOf(
            "Sable Steamshadow",
            "ingeniera",
            "ingenieracombplayer",
            "ingenieracomb"
        )

        engineersDataList.add(engineer1DataList)
        engineersDataList.add(engineer2DataList)

        return engineersDataList
    }
}