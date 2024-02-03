package com.drg.rustandrevolt.viewmodels

import android.content.Context
import android.os.Looper
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.drg.rustandrevolt.domain.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import android.os.Handler
import com.drg.rustandrevolt.service.AppContextSingleton
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.service.RandomEnemyAI
import com.drg.rustandrevolt.domain.regenerateLifeWithPotions
import com.drg.rustandrevolt.domain.totalStrongAttacks
import com.drg.rustandrevolt.domain.totalVeryStrongAttacks
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
    private val randomEnemyAI: RandomEnemyAI,
) : ViewModel() {

    var context : Context = AppContextSingleton.getContext()

    val characterPlayer : Character = playerUseCase.getPlayer().currentGameCharacter
    val characterEnemyAI : Character = randomEnemyAI.getRandomEnemyAI()

    var imagePlayerCombat : Int = getImagePlayer(characterPlayer)
    var imageEnemyCombat : Int = getImageEnemy(characterEnemyAI)

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
    fun getImagePlayer(player : Character) : Int{
        if (context!= null){
            return context!!.resources.getIdentifier(player.imageCombatPlayerResource, "drawable", context!!.packageName)
        }
        return R.drawable.imageusrdflt
    }

    fun getImageEnemy(enemy : Character) : Int{
        if (context!= null){
            return context!!.resources.getIdentifier(enemy.imageCombatEnemyResource, "drawable", context!!.packageName)
        }
        return R.drawable.imagedflt
    }
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
        mutableSeqtext = "Turno de ${characterEnemyAI.name}"

        handler.postDelayed({
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
        }, 1500)
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

        if (characterEnemyAI.remainingStrongAttacks > (totalStrongAttacks -2) || characterEnemyAI.remainingVeryStrongAttacks > (totalVeryStrongAttacks -2)){
            return Random.nextInt(strongAttack, veryStrongAttack + 1)
        }
        else if (characterEnemyAI.remainingStrongAttacks > 0 && characterEnemyAI.remainingVeryStrongAttacks > 0){
            return Random.nextInt(normalAttack, veryStrongAttack + 1)
        }

        if (characterEnemyAI.remainingStrongAttacks == 0 && characterEnemyAI.remainingVeryStrongAttacks > 0){
            var optionAttack : Int = strongAttack
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
            mutableSeqtext = "${playerUseCase.getPlayer().name} has hecho ${calculateGameScore()} pts."

            handler.postDelayed({
                mutableShowSeqText = false
                mutableShowBtnEndGame = true
                characterPlayer.resetCharacterData()
                characterEnemyAI.resetCharacterData()
            }, 2500)
                            }, 2500)



    }

    fun calculateGameScore() : Int {
        var score = (characterPlayer.life * 250) + (characterPlayer.remainingHealPotions * 500) + (characterPlayer.remainingStrongAttacks * 150) + (characterPlayer.remainingVeryStrongAttacks * 250)
        playerUseCase.getPlayer().score = score
        return score
    }

    //****************************************************************
}