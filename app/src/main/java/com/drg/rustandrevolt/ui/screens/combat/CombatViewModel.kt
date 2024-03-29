package com.drg.rustandrevolt.ui.screens.combat

import android.content.Context
import android.os.Looper
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.drg.rustandrevolt.domain.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import android.os.Handler
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.data.sources.datastore.CharacterSelectedDataStore
import com.drg.rustandrevolt.data.sources.datastore.Vibrator
import com.drg.rustandrevolt.domain.service.RandomEnemyAI
import com.drg.rustandrevolt.domain.regenerateLifeWithPotions
import com.drg.rustandrevolt.domain.totalStrongAttacks
import com.drg.rustandrevolt.domain.totalVeryStrongAttacks
import com.drg.rustandrevolt.domain.usecases.PlayerUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random
import com.drg.rustandrevolt.MyApplication.Companion.musicPreferences
import com.drg.rustandrevolt.domain.service.AllCharacters
import com.drg.rustandrevolt.domain.service.BackgroundCombat
import com.drg.rustandrevolt.data.sources.sharedpreferences.MusicPreferences
import com.drg.rustandrevolt.domain.service.CharacterCombatImage
import com.drg.rustandrevolt.sound.FxButtons
import com.drg.rustandrevolt.sound.FxPlayerCards
import com.drg.rustandrevolt.sound.MusicPlayer


const val normalAttack = 1
const val strongAttack = 2
const val veryStrongAttack = 3
const val specialAttack = 4

@HiltViewModel
class CombatViewModel @Inject constructor(
    private val playerUseCase: PlayerUseCase,
    private val randomEnemyAI: RandomEnemyAI,
    private val allCharacters: AllCharacters,
    private val characterSelectedDataStore: CharacterSelectedDataStore,
    private val characterCombatImage: CharacterCombatImage,
    private val musicPlayer: MusicPlayer,
    private val vibrator: Vibrator,
) : ViewModel() {

    lateinit var characterPlayer : Character
    lateinit var characterEnemyAI : Character

    var imageEnemyCombat : Int = R.drawable.imagedflt

    private val handler = Handler(Looper.getMainLooper())

    //Variables mutables para regenerar vista Compose
    var imagePlayerCombat by mutableStateOf(R.drawable.imagedflt)
        private set
    var mutableShowSeqText by mutableStateOf(false)
        private set
    var mutableSeqtext by mutableStateOf("")
        private set
    var mutablePlayerLife by mutableStateOf(0f)
        private set
    var mutablePlayerName by mutableStateOf("")
        private set
    var mutablePlayerChargeSpecialAttack by mutableStateOf(0f)
        private set
    var mutablePlayerRemainingHealPotions by mutableStateOf(0)
        private set
    var mutableEnableBtnHeal by mutableStateOf(true)
        private set
    var mutableEnableBtnStrongAttack by mutableStateOf(true)
        private set
    var mutableEnableBtnVeryStrongAttack by mutableStateOf(true)
        private set
    var mutableEnableBtnSpecialAttack by mutableStateOf(false)
        private set
    var mutableEnemyAIName by mutableStateOf("")
        private set
    var mutableEnemyAILife by mutableStateOf(0f)
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

    var mutablePotionImage by mutableStateOf(R.drawable.c_potiongreencarta)
    var mutableStrongAttackImage by mutableStateOf(R.drawable.c_punonegrocartarojamas)
    var mutableVeryStrongAttackImage by mutableStateOf(R.drawable.c_punonegrocartarojamasmas)
    var mutableSpecialAttackImage by mutableStateOf(R.drawable.c_superataquecartabn)

    var mutableBackground by mutableStateOf(0)

    init{
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getEnemyAiCoroutine()
                getPlayerCharacterCoroutine()            }
        }

        musicPreferences.setMusicEnabledPrefs(musicPreferences.isMusicEnabled, false)
        getBackground()
    }

    //****************************************************************
    suspend fun getPlayerCharacterCoroutine(){
        withContext(Dispatchers.IO) {
            characterPlayer = allCharacters.getCharacterForName(characterSelectedDataStore.getCharacterName())
            mutablePlayerName = characterPlayer.name
            mutablePlayerLife = characterPlayer.life.toFloat()/100
            mutablePlayerChargeSpecialAttack = (characterPlayer.chargeForSpecialAttack.toFloat()/100)*2
            mutablePlayerRemainingHealPotions = characterPlayer.remainingHealPotions
            imagePlayerCombat = characterCombatImage.getImagePlayer(characterPlayer)
            Log.d("Player_image",characterPlayer.imageCombatPlayerResource)
            Log.d("ID_image",R.drawable.rebeldeh.toString())

        }
    }
    suspend fun getEnemyAiCoroutine(){
        withContext(Dispatchers.Main) {
            characterEnemyAI = randomEnemyAI.getRandomEnemyAI()
            imageEnemyCombat = characterCombatImage.getImageEnemy(characterEnemyAI)
            mutableEnemyAILife = characterEnemyAI.life.toFloat() / 100
            mutableEnemyAIName = characterEnemyAI.name
        }
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

        if (mutablePlayerRemainingHealPotions == 0) mutablePotionImage = R.drawable.c_potiongreencartablanconegro
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

        if (characterPlayer.remainingStrongAttacks == 0) mutableStrongAttackImage = R.drawable.c_punoblanconegrocartarojamas
        if (characterPlayer.remainingVeryStrongAttacks == 0) mutableVeryStrongAttackImage = R.drawable.c_punoblanconegrocartarojamasmas
        if (characterPlayer.chargeForSpecialAttack >= 50) mutableSpecialAttackImage = R.drawable.c_superataquecarta
        else mutableSpecialAttackImage = R.drawable.c_superataquecartabn
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

                potionLifeSound()

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
            characterEnemyAI.checkEspecialAttack() -> {
                attackOption = specialAttack
                superAttackSound()
            }
            else -> {
                //Realiza ataque aleatorio
                attackOption = getEnemyAiTypeAttack()
                attackEnemySound(attackOption)
            }
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
    //Music & Sounds

    fun stopCombatMusic(){
        musicPreferences.setMusicEnabledPrefs(musicPreferences.isMusicEnabled, true)
    }

    fun buttonSelectControlSound(){
        if (MusicPreferences.isMusicEnabledCompanion) {
            musicPlayer.playFX(FxButtons.FxButtonSelectPlayerControl)
        }
    }

    fun buttonEndGameSound(){
        if (MusicPreferences.isMusicEnabledCompanion) {
            musicPlayer.playFX(FxButtons.FxButtonEndGame)
        }
    }

    //**************
    //FX card Sounds
    fun potionLifeSound(){
        if (MusicPreferences.isMusicEnabledCompanion) {
            musicPlayer.playFX(FxPlayerCards.fxPotionLife)
        }
    }

    fun attack1Sound(){
        if (MusicPreferences.isMusicEnabledCompanion) {
            musicPlayer.playFX(FxPlayerCards.fxCardAttack1)
        }
    }

    fun attack2Sound(){
        if (MusicPreferences.isMusicEnabledCompanion) {
            musicPlayer.playFX(FxPlayerCards.fxCardAttack2)
        }
    }

    fun attack3Sound(){
        if (MusicPreferences.isMusicEnabledCompanion) {
            musicPlayer.playFX(FxPlayerCards.fxCardAttack3)
        }
    }

    fun superAttackSound(){
        if (MusicPreferences.isMusicEnabledCompanion) {
            musicPlayer.playFX(FxPlayerCards.fxCardSuperAttack)
        }
    }

    fun attackEnemySound(index: Int){
        if (MusicPreferences.isMusicEnabledCompanion) {
            musicPlayer.playFX(FxPlayerCards.fxList.get(index-1))
        }
    }

    fun vibrateEspecialAttack(){
        vibrator.start(1000)
    }

    fun getBackground(){
        mutableBackground = BackgroundCombat.getRandomBackground()
    }

    //****************************************************************
}