package com.drg.rustandrevolt.sound

import android.content.Context
import android.util.Log
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.domain.Character
import kotlin.random.Random

object Music {
    private var musicMenuList: List<Int> = listOf(
        R.raw.musicmenu_1,
        R.raw.musicmenu_2,
        R.raw.musicmenu_3,
        R.raw.musicmenu_4
    )

    fun getRandomMenuMusic(): Int{
        var random = Random.nextInt(0, musicMenuList.size)
        return musicMenuList.get(random)
    }
}