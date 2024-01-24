package com.drg.rustandrevolt.viewmodels

import android.os.Looper
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.drg.rustandrevolt.entities.Character
import com.drg.rustandrevolt.entities.Machine
import com.drg.rustandrevolt.entities.Rebel
import dagger.hilt.android.lifecycle.HiltViewModel
import android.os.Handler
import com.drg.rustandrevolt.RandomEnemyAI
import com.drg.rustandrevolt.entities.Player
import com.drg.rustandrevolt.entities.regenerateLifeWithPotions
import com.drg.rustandrevolt.ui.navigation.AppScreens
import com.drg.rustandrevolt.usecases.PlayerUseCase
import javax.inject.Inject
import kotlin.random.Random

const val normalAttack = 1
const val strongAttack = 2
const val veryStrongAttack = 3
const val specialAttack = 4

@HiltViewModel
class CombatViewModel @Inject constructor(
    private val playerUseCase: PlayerUseCase,
    private val randomEnemyAI: RandomEnemyAI
) : ViewModel() {

    val characterPlayer : Character = playerUseCase.getPlayer().currentGameCharacter
    val characterEnemyAI : Character = randomEnemyAI.getRandomEnemyAI()

    private val handler = Handler(Looper.getMainLooper())

    //Variables mutables para regenerar vista Compose
    var mutableShowSeqText by mutableStateOf(false)
        private set
    var mutableSeqtext by mutableStateOf("")
        private set
    var mutablePlayerLife by mutableStateOf(characterPlayer.life.toFloat()/100)
        private set
    var mutablePlayerChargeSpecialAttack by mutableStateOf((characterPlayer.chargeForSpecialAttack.toFloat()/100)*2)
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
    var mutableEnemyAIDamageRedColor by mutableStateOf(true)
        private set
    var mutablePlayerDamage by mutableStateOf("")
        private set
    var mutableShowBtnEndGame by mutableStateOf(false)
        private set

    //****************************************************************
    //PLAYER

    fun playerHealSequence(){
        characterPlayer.heal()
        mutablePlayerRemainingHealPotions = characterPlayer.remainingHealPotions
        mutableShowSeqText = true
        mutableSeqtext = "Te has curado! (+${regenerateLifeWithPotions} de vida)"
        mutablePlayerDamageRedColor = false
        mutablePlayerDamage = "+${regenerateLifeWithPotions}"
        mutablePlayerLife = characterPlayer.life.toFloat()/100
        handler.postDelayed({
            //Realiza acción después de la pausa
            mutableShowSeqText = false
            mutablePlayerDamage = ""
            mutablePlayerDamageRedColor = true
            enableBtnsPlayerControl()
        }, 1500) // 1500 milisegundos
    }

    fun playerAttackSequence (attackType : Int){
        characterPlayer.attack(characterEnemyAI, attackType)
        mutableShowSeqText = true
        mutableSeqtext = "Has realizado un ataque con ${characterPlayer.damageAttacking}pts. de daño"
        mutableEnemyAIDamage = "-${characterPlayer.damageAttacking}"
        mutableEnemyAILife = characterEnemyAI.life.toFloat()/100

        handler.postDelayed({
            mutableEnemyAIDamage = ""
            mutablePlayerChargeSpecialAttack = (characterPlayer.chargeForSpecialAttack.toFloat()/100)*2
            enableBtnsPlayerControl()

            when{
                !checkWinner() -> enemyAISequence()
                else -> showWinnerTextAndReturnCharacterSelectionScreen()
            }
        }, 1500)

    }
    //****************************************************************
    //ENEMY AI

    fun enemyAISequence (){
        if (characterEnemyAI.life < 30 && characterEnemyAI.remainingHealPotions > 0){
            characterEnemyAI.heal()
            mutableShowSeqText = true
            mutableSeqtext = "${characterEnemyAI.name} se ha curado! (+${regenerateLifeWithPotions} de vida)"
            mutableEnemyAIDamageRedColor = false
            mutableEnemyAIDamage = "+${regenerateLifeWithPotions}"
            mutableEnemyAILife = characterEnemyAI.life.toFloat()/100

            handler.postDelayed({
                mutableEnemyAIDamage = ""
                mutableEnemyAIDamageRedColor = true
                enemyAIAttackSequence()
            }, 1500)
        }
        else{
            enemyAIAttackSequence()
        }
    }

    fun enemyAIAttackSequence(){
        var attackOption : Int = 0

        //Atack
        when{
            characterEnemyAI.checkEspecialAttack() -> attackOption = specialAttack
            else -> attackOption = getEnemyAiTypeAttack() //Realiza ataque aleatorio
        }

        characterEnemyAI.attack(characterPlayer, attackOption)

        mutableShowSeqText = true
        when{
            attackOption != specialAttack -> mutableSeqtext = "${characterEnemyAI.name} ha realizado un ataque con ${characterEnemyAI.damageAttacking}pts. de daño"
            else -> mutableSeqtext = "${characterEnemyAI.name} ha realizado SUPER ATAQUE!!! con ${characterEnemyAI.damageAttacking}pts. de daño"
        }
        mutablePlayerDamage = "-${characterEnemyAI.damageAttacking}"
        mutablePlayerLife = characterPlayer.life.toFloat()/100

        handler.postDelayed({
            mutablePlayerDamage = ""

            when{
                !checkWinner() -> mutableShowSeqText = false
                else -> showWinnerTextAndReturnCharacterSelectionScreen()
            }
        }, 1500)
    }

    fun getEnemyAiTypeAttack (): Int {

        if (characterEnemyAI.remainingStrongAttacks > 0 && characterEnemyAI.remainingVeryStrongAttacks > 0){
            return Random.nextInt(normalAttack, veryStrongAttack + 1)
        }
        if (characterEnemyAI.remainingStrongAttacks == 0 && characterEnemyAI.remainingVeryStrongAttacks > 0){
            var optionAttack : Int = 0
            while (optionAttack == strongAttack){
                optionAttack = Random.nextInt(normalAttack, veryStrongAttack + 1)
            }
            return optionAttack
        }
        if (characterEnemyAI.remainingStrongAttacks > 0 && characterEnemyAI.remainingVeryStrongAttacks == 0){
            return Random.nextInt(normalAttack, strongAttack + 1)
        }

        return normalAttack
    }
    //****************************************************************

    fun enableBtnsPlayerControl(){
        when{
            characterPlayer.remainingHealPotions > 0 -> mutableEnableBtnHeal = true
            else -> mutableEnableBtnHeal = false
        }

        when{
            characterPlayer.remainingStrongAttacks > 0 -> mutableEnableBtnStrongAttack = true
            else -> mutableEnableBtnStrongAttack = false
        }

        when{
            characterPlayer.remainingVeryStrongAttacks > 0 -> mutableEnableBtnVeryStrongAttack = true
            else -> mutableEnableBtnVeryStrongAttack = false
        }

        when{
            characterPlayer.checkEspecialAttack() -> mutableEnableBtnSpecialAttack = true
            else -> mutableEnableBtnSpecialAttack = false
        }
    }

    fun checkWinner () : Boolean{
        if (characterPlayer.life <= 0 || characterEnemyAI.life <= 0){
            return true
        }
        return false
    }

    fun showWinnerTextAndReturnCharacterSelectionScreen(){
        when{
            characterPlayer.life <= 0 -> mutableSeqtext = "${characterEnemyAI.name} ha ganado..."
            else -> mutableSeqtext = "Has ganado!"
        }

        handler.postDelayed({
            mutableShowSeqText = false
            mutableShowBtnEndGame = true
            characterPlayer.resetCharacterData()
            characterEnemyAI.resetCharacterData()
        }, 3000)

    }
    //****************************************************************
}