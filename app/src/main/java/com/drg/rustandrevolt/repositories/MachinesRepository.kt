package com.drg.rustandrevolt.repositories

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MachinesRepository @Inject constructor() {
    fun getMachines () : List<String> {
        val machinesNamesList : List<String> = listOf("Maquina 1", "Maquina 2")
        return machinesNamesList
    }
}