package com.drg.rustandrevolt.domain

import com.drg.rustandrevolt.data.sources.room.RebelEntity
import kotlin.random.Random

data class Rebel (
    override var name : String,
    override var imageCardResource: String,
    override var imageCombatPlayerResource: String,
    override var imageCombatEnemyResource: String
) : Character (
    name,
    imageCardResource,
    imageCombatPlayerResource,
    imageCombatEnemyResource
){
    override fun attack(objective: Character, attackType: Int) {
        super.damageAttacking  = 0

        if (objective is Machine){
            super.damageAttacking += EXTRA_ATTACK_DAMAGE
        }

        when (attackType){
            normalAttack -> { // normal
                super.damageAttacking += Random.nextInt(
                    NORMAL_ATTACK_DAMAGE_MIN,
                    NORMAL_ATTACK_DAMAGE_MAX
                )
            }
            strongAttack -> { // fuerte
                super.damageAttacking += Random.nextInt(
                    STRONG_ATTACK_DAMAGE_MIN,
                    STRONG_ATTACK_DAMAGE_MAX
                )
                super.remainingStrongAttacks --
            }
            veryStrongAttack -> { // +fuerte
                super.damageAttacking += Random.nextInt(
                    VERYSTRONG_ATTACK_DAMAGE_MIN,
                    VERYSTRONG_ATTACK_DAMAGE_MAX
                )
                super.remainingVeryStrongAttacks --
            }
            specialAttack -> { // especial
                if (objective is Engineer){
                    super.damageAttacking += EXTRA_SPECIAL_DAMAGE
                }
                super.damageAttacking += Random.nextInt(
                    SPECIAL_ATTACK_DAMAGE_MIN,
                    SPECIAL_ATTACK_DAMAGE_MAX
                )
                super.chargeForSpecialAttack = 0
            }
        }

        objective.life -= super.damageAttacking

        if (attackType != specialAttack){ // Si no es el ataque especial
            super.chargeForSpecialAttack += super.damageAttacking // El daño de ataque carga el ataque especial
        }
    }
}
fun Rebel.toEntity() = RebelEntity(
    name = name,
    imageCardResource = imageCardResource,
    imageCombatPlayerResource = imageCombatPlayerResource,
    imageCombatEnemyResource = imageCombatEnemyResource
)