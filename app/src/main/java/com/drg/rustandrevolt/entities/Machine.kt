package com.drg.rustandrevolt.entities

import javax.inject.Inject
import kotlin.random.Random

class Machine (name : String) : Character (name){
    override fun attack(objective: Character, attackType: Int) {
        super.damageAttacking  = 0

        if (objective is Engineer){
            super.damageAttacking += 2
        }

        when (attackType){
            normalAttack -> { // normal
                super.damageAttacking += Random.nextInt(3,7)
            }
            strongAttack -> { // fuerte
                super.damageAttacking += Random.nextInt(7,13)
                super.remainingStrongAttacks --
            }
            veryStrongAttack -> { // +fuerte
                super.damageAttacking += Random.nextInt(13,25)
                super.remainingVeryStrongAttacks --
            }
            specialAttack -> { // especial
                if (objective is Rebel){
                    super.damageAttacking += 5
                }
                super.damageAttacking += Random.nextInt(30,35)
                super.chargeForSpecialAttack = 0
            }
        }

        objective.life -= super.damageAttacking

        if (attackType != specialAttack){ // Si no es el ataque especial
            super.chargeForSpecialAttack += super.damageAttacking // El daño de ataque carga el ataque especial
        }
    }
}