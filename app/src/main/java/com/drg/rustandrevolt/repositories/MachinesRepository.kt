package com.drg.rustandrevolt.repositories

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MachinesRepository @Inject constructor() {
    fun getMachines () : MutableList<MutableList<String>>{
        val machinesDataList : MutableList<MutableList<String>> = mutableListOf()

        val machine1DataList : MutableList<String> = mutableListOf(
            "Maquina 1",
            "imagedflt3",
            "imageusrdflt",
            "imagedflt3"
        )

        val machine2DataList : MutableList<String> = mutableListOf(
            "Maquina 2",
            "imagedflt4",
            "imageusrdflt",
            "imagedflt4"
        )

        machinesDataList.add(machine1DataList)
        machinesDataList.add(machine2DataList)

        return machinesDataList
    }
}