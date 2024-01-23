package com.drg.rustandrevolt.viewmodels

import android.os.Looper
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.drg.rustandrevolt.entities.Character
import com.drg.rustandrevolt.entities.Machine
import com.drg.rustandrevolt.entities.Rebel
import dagger.hilt.android.lifecycle.HiltViewModel
import android.os.Handler
import javax.inject.Inject

const val normalAttack = 1
const val strongAttack = 2
const val veryStrongAttack = 3
const val specialAttack = 4

@HiltViewModel
class CombatViewModel @Inject constructor() : ViewModel() {
    val characterPlayer : Character = Rebel("Rebelde 1")
    val characterEnemyAI : Character = Machine("Maquina 1")
    private val handler = Handler(Looper.getMainLooper())

    //Variables mutables para regenerar vista Compose
    var mutableShowSeqText by mutableStateOf(false)
        private set
    var mutableSeqtext by mutableStateOf("")
        private set
    var mutablePlayerLife by mutableStateOf(characterPlayer.life.toFloat()/100)
        private set
    var mutablePlayerChargeSpecialAttack by mutableStateOf(characterPlayer.chargeForSpecialAttack.toFloat()/100)
        private set
    var mutablePlayerRemainingHealPotions by mutableStateOf(characterPlayer.remainingHealPotions)
        private set
    var mutableEnableBtnHeal by mutableStateOf(true)
        private set
    var mutableEnableBtnStrongAttack by mutableStateOf(true)
        private set
    var mutableEnableBtnVeryStrongAttack by mutableStateOf(true)
        private set
    var mutableEnableBtnSpecialAttack by mutableStateOf(false)
        private set
    var mutableEnemyAILife by mutableStateOf(characterEnemyAI.life.toFloat()/100)
        private set
    var mutableEnemyAIDamage by mutableStateOf("")
        private set
    var mutablePlayerDamageRedColor by mutableStateOf(true)
        private set
    var mutablePlayerDamage by mutableStateOf("")
        private set

    fun playerHealSequence(){
        mutableShowSeqText = true
        characterPlayer.heal()
        mutablePlayerRemainingHealPotions = characterPlayer.remainingHealPotions
        mutableSeqtext = "Te has curado! (+15 de vida)"
        mutablePlayerDamageRedColor = false
        mutablePlayerDamage = "+15"
        mutablePlayerLife = characterPlayer.life.toFloat()/100
        handler.postDelayed({
            // Realiza alguna acción después de la pausa
            mutableShowSeqText = false
            mutablePlayerDamage = ""
            mutablePlayerDamageRedColor = true
            enableBtnsPlayerControl()
        }, 1250) // 1250 milisegundos
    }

    fun playerAttackSequence (attackType : Int){
        characterPlayer.attack(characterEnemyAI, attackType)
        mutableShowSeqText = true
        mutableSeqtext = "Has realizado un ataque con ${characterPlayer.damageAttacking}pts. de daño"
        mutableEnemyAIDamage = "-${characterPlayer.damageAttacking}"
        mutableEnemyAILife = characterEnemyAI.life.toFloat()/100

        handler.postDelayed({
            mutableShowSeqText = false
            mutableEnemyAIDamage = ""
            mutablePlayerChargeSpecialAttack = (characterPlayer.chargeForSpecialAttack.toFloat()/100)*2
            enableBtnsPlayerControl()
        }, 1250)

/*        if (!checkWinner()){
            enemyAISequence()
        }
*/
    }

    fun enableBtnsPlayerControl(){
        if (characterPlayer.remainingHealPotions > 0){
            mutableEnableBtnHeal = true
        }
        else{
            mutableEnableBtnHeal = false
        }

        if (characterPlayer.remainingStrongAttacks > 0){
            mutableEnableBtnStrongAttack = true
        }
        else{
            mutableEnableBtnStrongAttack = false
        }

        if (characterPlayer.remainingVeryStrongAttacks > 0){
            mutableEnableBtnVeryStrongAttack = true
        }
        else{
            mutableEnableBtnVeryStrongAttack = false
        }

        if (characterPlayer.checkEspecialAttack()){
            mutableEnableBtnSpecialAttack = true
        }
        else{
            mutableEnableBtnSpecialAttack = false
        }
    }
}