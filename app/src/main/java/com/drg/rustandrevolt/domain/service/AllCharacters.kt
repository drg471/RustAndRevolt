package com.drg.rustandrevolt.domain.service

import com.drg.rustandrevolt.domain.Character
import com.drg.rustandrevolt.domain.usecases.GetAllEngineeringUseCase
import com.drg.rustandrevolt.domain.usecases.MachinesUseCase
import com.drg.rustandrevolt.domain.usecases.RebelsUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AllCharacters @Inject constructor(
    private val rebelsUseCase: RebelsUseCase,
    private val machinesUseCase: MachinesUseCase,
    private val getAllEngineeringUseCase: GetAllEngineeringUseCase
) {
    suspend fun getCharacterForName(characterName: String): Character {
        val rebelsList: MutableList<Character> = rebelsUseCase.getAll()
        val machinesList: MutableList<Character> = machinesUseCase.getAll()
        val engineersList: MutableList<Character> = getAllEngineeringUseCase()
        val allCharactersList: MutableList<Character> = mutableListOf()

        var characterSelected: Character = rebelsUseCase.getAll().get(0)

        allCharactersList.addAll(rebelsList)
        allCharactersList.addAll(machinesList)
        allCharactersList.addAll(engineersList)

        for (character in allCharactersList){
            if (character.name.contains(characterName)){
                characterSelected = character
            }
        }
        return characterSelected
    }
}