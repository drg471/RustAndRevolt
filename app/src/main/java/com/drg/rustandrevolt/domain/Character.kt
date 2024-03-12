package com.drg.rustandrevolt.domain

private const val healPotions = 3
const val regenerateLifeWithPotions = 20
const val totalStrongAttacks = 6
const val totalVeryStrongAttacks = 4
private const val initialChargeForSpecialAttack = 0
private const val totalLife = 100
private const val totalChargeForSpecialAttack = 50

const val normalAttack = 1
const val strongAttack = 2
const val veryStrongAttack = 3
const val specialAttack = 4

const val NORMAL_ATTACK_DAMAGE_MIN = 3
const val NORMAL_ATTACK_DAMAGE_MAX = 7
const val STRONG_ATTACK_DAMAGE_MIN = 7
const val STRONG_ATTACK_DAMAGE_MAX = 13
const val VERYSTRONG_ATTACK_DAMAGE_MIN = 13
const val VERYSTRONG_ATTACK_DAMAGE_MAX = 25
const val SPECIAL_ATTACK_DAMAGE_MIN = 30
const val SPECIAL_ATTACK_DAMAGE_MAX = 35
const val EXTRA_ATTACK_DAMAGE = 2
const val EXTRA_SPECIAL_DAMAGE = 5

abstract class Character constructor (
    open var name : String,
    open var imageCardResource : String,
    open var imageCombatPlayerResource : String,
    open var imageCombatEnemyResource : String,
    ) {

    var remainingHealPotions : Int = healPotions
    var remainingStrongAttacks : Int = totalStrongAttacks
    var remainingVeryStrongAttacks : Int = totalVeryStrongAttacks
    var damageAttacking : Int = initialChargeForSpecialAttack

    var life : Int = totalLife
        get() = field
        set (value){
            field = when {
                value > totalLife -> totalLife
                value < 0 -> 0
                else -> value
            }
        }

    //Carga para ataque especial (cuando llegue a 50 realiza ataque especial)
    var chargeForSpecialAttack : Int = initialChargeForSpecialAttack
        get() = field
        set (value){
            field = when {
                value > totalChargeForSpecialAttack -> totalChargeForSpecialAttack
                value < 0 -> 0
                else -> value
            }
        }

    abstract fun attack (objective : Character, atackType : Int)

    fun heal(){
        if (remainingHealPotions > 0){
            life += regenerateLifeWithPotions
            remainingHealPotions --
        }
    }

    fun checkEspecialAttack () : Boolean{
        if (chargeForSpecialAttack >= 50){
            return true
        }
        return false
    }

    fun resetCharacterData () {
        life = totalLife
        remainingHealPotions = healPotions
        remainingStrongAttacks = totalStrongAttacks
        remainingVeryStrongAttacks = totalVeryStrongAttacks
    }
}