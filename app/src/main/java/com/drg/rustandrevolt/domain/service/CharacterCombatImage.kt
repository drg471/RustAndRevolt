package com.drg.rustandrevolt.domain.service

import android.content.Context
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.domain.Character
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CharacterCombatImage @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getImagePlayer(player : Character) : Int{
        if (context!= null){
            return context!!.resources.getIdentifier(player.imageCombatPlayerResource, "drawable", context!!.packageName)
        }
        return R.drawable.imagedflt
    }

    fun getImageEnemy(enemy : Character) : Int{
        if (context!= null){
            return context!!.resources.getIdentifier(enemy.imageCombatEnemyResource, "drawable", context!!.packageName)
        }
        return R.drawable.imagedflt
    }
}