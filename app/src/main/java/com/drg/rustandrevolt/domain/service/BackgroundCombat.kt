package com.drg.rustandrevolt.domain.service

import android.content.Context
import android.util.Log
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.domain.Character
import kotlin.random.Random

object BackgroundCombat {
    private var BackgroundCombatList: List<Int> = listOf(
        R.drawable.cmb_background01,
        R.drawable.cmb_background02,
        R.drawable.cmb_background03
    )

    fun getRandomBackground(): Int{
        var random = Random.nextInt(0, BackgroundCombatList.size)
        return BackgroundCombatList.get(random)
    }

}