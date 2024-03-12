package com.drg.rustandrevolt.ui.screens.select_character

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drg.rustandrevolt.domain.Character
import com.drg.rustandrevolt.domain.usecases.EngineersUseCase
import com.drg.rustandrevolt.domain.usecases.MachinesUseCase
import com.drg.rustandrevolt.domain.usecases.PlayerUseCase
import com.drg.rustandrevolt.domain.usecases.RebelsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.data.sources.datastore.CharacterSelectedDataStore
import com.drg.rustandrevolt.data.sources.sharedpreferences.MusicPreferences
import com.drg.rustandrevolt.domain.service.CharacterCurrentImage
import com.drg.rustandrevolt.sound.FxButtons
import com.drg.rustandrevolt.sound.MusicPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@HiltViewModel
class CharacterSelectionViewModel @Inject constructor(
    private val rebelsUseCase : RebelsUseCase,
    private val machinesUseCase : MachinesUseCase,
    private val engineersUseCase : EngineersUseCase,
    private val playerUseCase: PlayerUseCase,
    private val characterSelectedDataStore: CharacterSelectedDataStore,
    private val musicPlayer: MusicPlayer,
    private val characterCurrentImage: CharacterCurrentImage,
) : ViewModel() {

    private val _state = MutableStateFlow<CharacterSelectionState>(CharacterSelectionState.Loading)
    val state: StateFlow<CharacterSelectionState> = _state

    var characterList: MutableList<Character> = mutableListOf()
    var currentCharacterIndex: Int = 0
    var currentImageIndex: Int = 0 //cero = Valor inicial


    //init = inicializa el viewmodel con una lista de personajes
    init {
        //****************************************************
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                characterSelectedDataStore.resetDataStore()
                rebelsUseCase.save()
                loadRebelList()
                updatePlayerCharacter()
            }
        }
        //****************************************************
    }

    fun showNextCharacter() {
        currentCharacterIndex = (currentCharacterIndex + 1) % characterList.size
        setCurrentImage()
        _state.value = CharacterSelectionState.Success(characterList, currentCharacterIndex, currentImageIndex)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                updatePlayerCharacter()
            }
        }
    }

    fun showPreviousCharacter() {
        currentCharacterIndex = (currentCharacterIndex - 1 + characterList.size) % characterList.size
        setCurrentImage()
        _state.value = CharacterSelectionState.Success(characterList, currentCharacterIndex, currentImageIndex)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                updatePlayerCharacter()
            }
        }
    }

    fun loadMachineList(){
        resetDataList()
        characterList = machinesUseCase.getAll()
        currentCharacterIndex = 0
        setCurrentImage()
        _state.value = CharacterSelectionState.Success(characterList, currentCharacterIndex, currentImageIndex)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                updatePlayerCharacter()
            }
        }    }

    fun loadRebelListRoom(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                loadRebelList()
                updatePlayerCharacter()
            }
        }
    }

    fun loadRebelList(){
        resetDataList()
        characterList = rebelsUseCase.getAll()
        currentCharacterIndex = 0
        setCurrentImage()
        _state.value = CharacterSelectionState.Success(characterList, currentCharacterIndex, currentImageIndex)
    }

    fun loadEngineerList(){
        resetDataList()
        characterList = engineersUseCase.getAll()
        currentCharacterIndex = 0
        setCurrentImage()
        _state.value = CharacterSelectionState.Success(characterList, currentCharacterIndex, currentImageIndex)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                updatePlayerCharacter()
            }
        }
    }

    fun resetDataList(){
        currentImageIndex = 0
        currentCharacterIndex = 0
        characterList = mutableListOf()
        _state.value = CharacterSelectionState.Success(characterList, currentCharacterIndex, currentImageIndex)
    }


    suspend fun updatePlayerCharacter(){
        characterSelectedDataStore.saveCharacterName(characterList.get(currentCharacterIndex).name)
        setCurrentImage()
    }

    fun setCurrentImage(){
        currentImageIndex = characterCurrentImage.getCurrentImageIndex(characterList, currentCharacterIndex)
    }

    //*********************
    //Sounds
    fun buttonSelectCharacterTypeSound(){
        if (MusicPreferences.isMusicEnabledCompanion) {
            musicPlayer.playFX(FxButtons.FxButtonSelectCharacterType)
        }
    }
    fun buttonPlaySound(){
        if (MusicPreferences.isMusicEnabledCompanion){
            musicPlayer.playFX(FxButtons.FxButtonPlay)
        }
    }

    fun buttonChangeCharacterSound(){
        if (MusicPreferences.isMusicEnabledCompanion) {
            musicPlayer.playFX(FxButtons.FxButtonChangeCharacter)
        }
    }
    //*********************
}

sealed class CharacterSelectionState{
    object Loading: CharacterSelectionState()
    data class Success(
        val characterList: MutableList<Character>,
        val currentCharacterIndex: Int,
        val currentImageIndex: Int
    ): CharacterSelectionState()
}