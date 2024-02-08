package com.drg.rustandrevolt.service

import androidx.lifecycle.viewModelScope
import com.drg.rustandrevolt.domain.Character
import com.drg.rustandrevolt.usecases.EngineersUseCase
import com.drg.rustandrevolt.usecases.MachinesUseCase
import com.drg.rustandrevolt.usecases.RebelsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class AllCharacters @Inject constructor(
    private val rebelsUseCase: RebelsUseCase,
    private val machinesUseCase: MachinesUseCase,
    private val engineersUseCase: EngineersUseCase
) {
    suspend fun getCharacterForName(characterName: String): Character {
        val rebelsList: MutableList<Character> = rebelsUseCase.getAll()
        val machinesList: MutableList<Character> = machinesUseCase.getAll()
        val engineersList: MutableList<Character> = engineersUseCase.getAll()
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