package com.drg.rustandrevolt.usecases

import com.drg.rustandrevolt.entities.Character
import com.drg.rustandrevolt.entities.Machine
import com.drg.rustandrevolt.repositories.MachinesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MachinesUseCase @Inject constructor(
    private val machineRepository: MachinesRepository
) {
    fun getAll () : MutableList<Character> {
        val machineNameList = machineRepository.getMachines()
        val machineList : MutableList<Character> = mutableListOf()

        for (machineName in machineNameList){
            machineList.add(Machine(machineName))
        }

        return machineList
    }
}