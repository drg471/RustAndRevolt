package com.drg.rustandrevolt.entities

import javax.inject.Inject
import kotlin.random.Random

class Engineer (name : String) : Character (name){
    override fun attack(objective: Character, attackType: Int) {
        super.damageAttacking  = 0

        when (attackType){
            normalAttack -> { // normal
                super.damageAttacking = Random.nextInt(1,5)
            }
            strongAttack -> { // fuerte
                super.damageAttacking = Random.nextInt(5,10)
                super.remainingStrongAttacks --
            }
            veryStrongAttack -> { // +fuerte
                super.damageAttacking = Random.nextInt(15,20)
                super.remainingVeryStrongAttacks --
            }
            specialAttack -> { // especial
                super.damageAttacking = Random.nextInt(25,30)
                super.chargeForSpecialAttack = 0
            }
        }

        objective.life -= super.damageAttacking

        if (attackType != specialAttack){ // Si no es el ataque especial
            super.chargeForSpecialAttack += super.damageAttacking // El daño de ataque carga el ataque especial
        }
    }
}