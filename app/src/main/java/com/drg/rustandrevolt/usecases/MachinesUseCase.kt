package com.drg.rustandrevolt.usecases

import com.drg.rustandrevolt.entities.Character
import com.drg.rustandrevolt.entities.Engineer
import com.drg.rustandrevolt.entities.Machine
import com.drg.rustandrevolt.repositories.MachinesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MachinesUseCase @Inject constructor(
    private val machineRepository: MachinesRepository
) {
    fun getAll () : MutableList<Character> {
        val machinesDataList = machineRepository.getMachines()
        val machinesList : MutableList<Character> = mutableListOf()

        for (machineDataList in machinesDataList){
            machinesList.add(
                Engineer(
                    machineDataList.get(0),
                    machineDataList.get(1),
                    machineDataList.get(2),
                    machineDataList.get(3)
                )
            )
        }

        return machinesList
    }
}