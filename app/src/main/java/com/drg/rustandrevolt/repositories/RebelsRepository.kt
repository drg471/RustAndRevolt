package com.drg.rustandrevolt.repositories

import javax.inject.Inject
import javax.inject.Singleton

//EL REPOSITORIO SERIA PARA IR A BASE DE DATOS
@Singleton
class RebelsRepository @Inject constructor(){
    fun getRebels() : List<String>{
        val rebelsNameList : List<String> = listOf("Rebelde 1", "Rebelde 2")
        return rebelsNameList
    }
}