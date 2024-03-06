package com.drg.rustandrevolt.ui.screens.select_character

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drg.rustandrevolt.domain.Character
import com.drg.rustandrevolt.domain.usecases.GetAllEngineeringUseCase
import com.drg.rustandrevolt.domain.usecases.MachinesUseCase
import com.drg.rustandrevolt.domain.usecases.PlayerUseCase
import com.drg.rustandrevolt.domain.usecases.RebelsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.data.sources.datastore.CharacterSelectedDataStore
import com.drg.rustandrevolt.data.sources.sharedpreferences.MusicPreferences
import com.drg.rustandrevolt.sound.FxButtons
import com.drg.rustandrevolt.sound.MusicPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@HiltViewModel
class CharacterSelectionViewModel @Inject constructor(
    private val rebelsUseCase : RebelsUseCase,
    private val machinesUseCase : MachinesUseCase,
    private val getAllEngineering : GetAllEngineeringUseCase,
    private val playerUseCase: PlayerUseCase,
    private val characterSelectedDataStore: CharacterSelectedDataStore,
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
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                updatePlayerCharacter()
            }
        }
    }

    fun showPreviousCharacter() {
        currentCharacterIndex = (currentCharacterIndex - 1 + characterList.size) % characterList.size
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                updatePlayerCharacter()
            }
        }
    }

    fun loadMachineList(){
        resetDataList()
        characterList = machinesUseCase.getAll()
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
    }

    fun loadEngineerList(){
        resetDataList()
        characterList = getAllEngineering()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                updatePlayerCharacter()
            }
        }
    }

    fun resetDataList(){
        if (characterList.isNotEmpty()) {
            characterList.clear()
            currentCharacterIndex = 0
        }
    }


    suspend fun updatePlayerCharacter(){
        characterSelectedDataStore.saveCharacterName(characterList.get(currentCharacterIndex).name)
        getImage()
    }

    fun getImage(){
        if (context!= null){
            currentImageIndex = context!!.resources.getIdentifier(characterList.get(currentCharacterIndex).imageCardResource, "drawable", context!!.packageName)
        }
    }

    //*********************
    //Sounds
    fun buttonSelectCharacterTypeSound(){
        if (MusicPreferences.isMusicEnabledCompanion) {
            val musicPlayer = MusicPlayer(context!!)
            musicPlayer.playFX(FxButtons.FxButtonSelectCharacterType)
        }
    }
    fun buttonPlaySound(){
        if (MusicPreferences.isMusicEnabledCompanion){
            val musicPlayer = MusicPlayer(context!!)
            musicPlayer.playFX(FxButtons.FxButtonPlay)
        }
    }

    fun buttonChangeCharacterSound(){
        if (MusicPreferences.isMusicEnabledCompanion) {
            val musicPlayer = MusicPlayer(context!!)
            musicPlayer.playFX(FxButtons.FxButtonChangeCharacter)
        }
    }
    //*********************
}