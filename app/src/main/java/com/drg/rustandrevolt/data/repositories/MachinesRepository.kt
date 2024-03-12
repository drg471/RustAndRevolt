package com.drg.rustandrevolt.data.repositories

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MachinesRepository @Inject constructor() {
    fun getMachines () : MutableList<MutableList<String>>{
        val machinesDataList : MutableList<MutableList<String>> = mutableListOf()

        val machine1DataList : MutableList<String> = mutableListOf(
            "Voltatron",
            "maquina1",
            "maquina1combplayer",
            "maquina1comb"
        )

        val machine2DataList : MutableList<String> = mutableListOf(
            "Steelcrank",
            "maquina2",
            "maquina2combplayer",
            "maquina2comb"
        )

        machinesDataList.add(machine1DataList)
        machinesDataList.add(machine2DataList)

        return machinesDataList
    }
}