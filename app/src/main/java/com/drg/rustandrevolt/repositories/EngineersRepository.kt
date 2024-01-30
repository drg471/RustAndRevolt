package com.drg.rustandrevolt.repositories

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EngineersRepository @Inject constructor() {
    fun getEngineers() : MutableList<MutableList<String>>{
        val engineersDataList : MutableList<MutableList<String>> = mutableListOf()

        val engineer1DataList : MutableList<String> = mutableListOf(
            "Ingeniero 1",
            "imagedflt5",
            "imageusrdflt",
            "imagedflt5"
        )

        val engineer2DataList : MutableList<String> = mutableListOf(
            "Ingeniero 2",
            "imagedflt6",
            "imageusrdflt",
            "imagedflt6"
        )

        engineersDataList.add(engineer1DataList)
        engineersDataList.add(engineer2DataList)

        return engineersDataList
    }
}