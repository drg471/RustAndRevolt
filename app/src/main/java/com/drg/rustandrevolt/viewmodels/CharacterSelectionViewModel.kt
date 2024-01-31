package com.drg.rustandrevolt.viewmodels

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.drg.rustandrevolt.entities.Character
import com.drg.rustandrevolt.usecases.EngineersUseCase
import com.drg.rustandrevolt.usecases.MachinesUseCase
import com.drg.rustandrevolt.usecases.PlayerUseCase
import com.drg.rustandrevolt.usecases.RebelsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.drg.rustandrevolt.R


@HiltViewModel
class CharacterSelectionViewModel @Inject constructor(
    private val rebelsUseCase : RebelsUseCase,
    private val machinesUseCase : MachinesUseCase,
    private val engineersUseCase : EngineersUseCase,
    private val playerUseCase: PlayerUseCase,
) : ViewModel() {

    //Variables mutables para regenerar vista Compose
    var characterList by mutableStateOf<MutableList<Character>>(mutableListOf())
        private set
    var currentCharacterIndex by mutableStateOf(0) //cero = Valor inicial
        private set

    var imageCardDefault : Int = R.drawable.imagedflt
    var currentImageIndex by mutableStateOf(imageCardDefault) //cero = Valor inicial
        private set

    var context : Context? = null

    //init = inicializa el viewmodel con una lista de personajes
    init {
        loadRebelList()
        updatePlayerCharacter()
    }

    fun showNextCharacter() {
        currentCharacterIndex = (currentCharacterIndex + 1) % characterList.size
        updatePlayerCharacter()
    }

    fun showPreviousCharacter() {
        currentCharacterIndex = (currentCharacterIndex - 1 + characterList.size) % characterList.size
        updatePlayerCharacter()
    }

    fun loadMachineList(){
        resetDataList()
        characterList = machinesUseCase.getAll()
        updatePlayerCharacter()
    }

    fun loadRebelList(){
        resetDataList()
        characterList = rebelsUseCase.getAll()
        updatePlayerCharacter()
    }

    fun loadEngineerList(){
        resetDataList()
        characterList = engineersUseCase.getAll()
        updatePlayerCharacter()
    }

    fun resetDataList(){
        characterList.clear()
        currentCharacterIndex = 0
    }

    fun updatePlayerCharacter(){
        playerUseCase.setCharacter(characterList.get(currentCharacterIndex))
        getImage()
    }

    fun getImage(){
        if (context!= null){
            currentImageIndex = context!!.resources.getIdentifier(characterList.get(currentCharacterIndex).imageCardResource, "drawable", context!!.packageName)
        }
    }
}