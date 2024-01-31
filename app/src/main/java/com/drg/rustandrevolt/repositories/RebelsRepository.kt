package com.drg.rustandrevolt.repositories

import javax.inject.Inject
import javax.inject.Singleton

//EL REPOSITORIO SERIA PARA IR A BASE DE DATOS
@Singleton
class RebelsRepository @Inject constructor(){
    fun getRebels() : MutableList<MutableList<String>>{
        val rebelsDataList : MutableList<MutableList<String>> = mutableListOf()

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

        return rebelsDataList
    }
}