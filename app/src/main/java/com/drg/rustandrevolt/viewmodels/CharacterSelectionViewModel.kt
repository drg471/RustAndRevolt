package com.drg.rustandrevolt.viewmodels

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.drg.rustandrevolt.entities.Character
import com.drg.rustandrevolt.entities.Rebel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterSelectionViewModel @Inject constructor() : ViewModel() {

    //Variables mutables para regenerar vista Compose
    var characterList by mutableStateOf<MutableList<Character>>(mutableListOf())
        private set

    var currentCharacterIndex by mutableStateOf(0) //cero = Valor inicial
        private set

    //init = inicializa el viewmodel con una lista de personajes
    init {
        var rebel1 : Character = Rebel ("Rebelde 1")
        characterList.add(rebel1)
        var rebel2 : Character = Rebel ("Rebelde 2")
        characterList.add(rebel2)
        var rebel3 : Character = Rebel ("Rebelde 3")
        characterList.add(rebel3)
        var rebel4 : Character = Rebel ("Rebelde 4")
        characterList.add(rebel4)
    }

    fun showNextCharacter() {
        currentCharacterIndex = (currentCharacterIndex + 1) % characterList.size
    }

    fun showPreviousCharacter() {
        currentCharacterIndex = (currentCharacterIndex - 1 + characterList.size) % characterList.size
    }
}