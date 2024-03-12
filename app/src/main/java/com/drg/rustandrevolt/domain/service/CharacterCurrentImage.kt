package com.drg.rustandrevolt.domain.service

import android.content.Context
import com.drg.rustandrevolt.domain.Character
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CharacterCurrentImage @Inject constructor(
    @ApplicationContext private val context: Context,

) {
    fun getCurrentImageIndex(characterList: MutableList<Character>, currentCharacterIndex: Int,): Int{
        return  context.resources.getIdentifier(characterList.get(currentCharacterIndex).imageCardResource, "drawable", context.packageName)
    }
}