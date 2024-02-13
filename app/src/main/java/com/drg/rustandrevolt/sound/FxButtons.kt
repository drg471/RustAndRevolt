package com.drg.rustandrevolt.sound

import com.drg.rustandrevolt.R

object FxButtons {
    var FxButton1: Int = R.raw.fxbtn1
    var FxButtonSelectCharacterType: Int = R.raw.fxbtn2
    var FxButtonPlay: Int = R.raw.fxbtn3
    var FxButtonSelectPlayerControl: Int = R.raw.fxbtn4
    var FxButtonEndGame: Int = R.raw.fxbtn5
    var FxButtonChangeCharacter: Int = R.raw.fxbtn6
}

object FxPlayerCards {
    var fxPotionLife: Int = R.raw.fxcardlife
    var fxCardAttack1: Int = R.raw.fxcardattack1
    var fxCardAttack2: Int = R.raw.fxcardattack2
    var fxCardAttack3: Int = R.raw.fxcardattack3
    var fxCardSuperAttack: Int = R.raw.fxcardsuperattack

    val fxList: List<Int> = listOf(fxCardAttack1, fxCardAttack2, fxCardAttack3, fxCardSuperAttack)
}