package com.drg.rustandrevolt.viewmodels

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.drg.rustandrevolt.entities.Character
import com.drg.rustandrevolt.usecases.EngineersUseCase
import com.drg.rustandrevolt.usecases.MachinesUseCase
import com.drg.rustandrevolt.usecases.RebelsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterSelectionViewModel @Inject constructor(
    private val rebelsUseCase : RebelsUseCase,
    private val machinesUseCase : MachinesUseCase,
    private val engineersUseCase : EngineersUseCase
) : ViewModel() {

    //Variables mutables para regenerar vista Compose
    var characterList by mutableStateOf<MutableList<Character>>(mutableListOf())
        private set

    var currentCharacterIndex by mutableStateOf(0) //cero = Valor inicial
        private set

    //init = inicializa el viewmodel con una lista de personajes
    init {
        loadRebelList()
    }

    fun showNextCharacter() {
        currentCharacterIndex = (currentCharacterIndex + 1) % characterList.size
    }

    fun showPreviousCharacter() {
        currentCharacterIndex = (currentCharacterIndex - 1 + characterList.size) % characterList.size
    }

    fun loadMachineList(){
        characterList.clear()
        currentCharacterIndex = 0
        characterList = machinesUseCase.getAll()
    }

    fun loadRebelList(){
        characterList.clear()
        currentCharacterIndex = 0
        characterList = rebelsUseCase.getAll()
    }

    fun loadEngineerList(){
        characterList.clear()
        currentCharacterIndex = 0
        characterList = engineersUseCase.getAll()
    }
}