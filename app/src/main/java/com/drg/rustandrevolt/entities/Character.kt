package com.drg.rustandrevolt.entities

private const val healPotions = 3
private const val totalStrongAttacks = 7
private const val totalVeryStrongAttacks = 5
private const val initialChargeForSpecialAttack = 0
private const val totalLife = 100

const val normalAttack = 1
const val strongAttack = 2
const val veryStrongAttack = 3
const val specialAttack = 4

abstract class Character(var name : String) {

    var remainingHealPotions : Int = healPotions
    var remainingStrongAttacks : Int = totalStrongAttacks
    var remainingVeryStrongAttacks : Int = totalVeryStrongAttacks
    var chargeForSpecialAttack : Int = initialChargeForSpecialAttack //Carga para ataque especial (cuando llegue a 50 realiza ataque especial)
    var damageAttacking : Int = initialChargeForSpecialAttack

    var life : Int = totalLife
        get() = field
        set (value){
            field = when {
                value > totalLife -> totalLife
                value < initialChargeForSpecialAttack -> initialChargeForSpecialAttack
                else -> value
            }
        }

    abstract fun attack (objective : Character, atackType : Int)

    fun heal(){
        if (remainingHealPotions > initialChargeForSpecialAttack){
            life += 15
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