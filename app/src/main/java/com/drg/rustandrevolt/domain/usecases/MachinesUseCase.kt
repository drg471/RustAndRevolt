package com.drg.rustandrevolt.domain.usecases

import com.drg.rustandrevolt.domain.Character
import com.drg.rustandrevolt.domain.Engineer
import com.drg.rustandrevolt.data.repositories.MachinesRepository
import javax.inject.Inject
import javax.inject.Singleton

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