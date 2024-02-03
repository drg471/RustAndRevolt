package com.drg.rustandrevolt.character

import com.drg.rustandrevolt.domain.Rebel
import org.junit.Assert.assertEquals
import org.junit.Test

class RebelTest {

    @Test
    fun `given a normal attack`() {
        val rebel = Rebel("Rebel1")
        val objective = Rebel("Objetivo")

        rebel.attack(objective, 1)

        // Verifica que el daño esté en el rango esperado (1 a 4)
        assert(rebel.damageAttacking in 1..4)

        // Verifica que el objetivo haya perdido la cantidad correcta de vida
        assertEquals(100 - rebel.damageAttacking, objective.life)

        // Verifica que las demás propiedades hayan sido actualizadas correctamente
        assertEquals(rebel.damageAttacking, rebel.chargeForSpecialAttack)
        assertEquals(3, rebel.remainingHealPotions)
        assertEquals(7, rebel.remainingStrongAttacks)
        assertEquals(5, rebel.remainingVeryStrongAttacks)
    }

    @Test
    fun `given a strong attack`() {
        val rebel = Rebel("Rebel1")
        val objective = Rebel("Objetivo")

        rebel.attack(objective, 2)

        assert(rebel.damageAttacking in 5..9)

        assertEquals(100 - rebel.damageAttacking, objective.life)

        assertEquals(rebel.damageAttacking, rebel.chargeForSpecialAttack)
        assertEquals(3, rebel.remainingHealPotions)
        assertEquals(6, rebel.remainingStrongAttacks)
        assertEquals(5, rebel.remainingVeryStrongAttacks)
    }

    @Test
    fun `given a very strong attack`() {
        val rebel = Rebel("Rebel1")
        val objective = Rebel("Objetivo")

        rebel.attack(objective, 3)

        assert(rebel.damageAttacking in 15..19)

        assertEquals(100 - rebel.damageAttacking, objective.life)

        assertEquals(rebel.damageAttacking, rebel.chargeForSpecialAttack)
        assertEquals(3, rebel.remainingHealPotions)
        assertEquals(7, rebel.remainingStrongAttacks)
        assertEquals(4, rebel.remainingVeryStrongAttacks)
    }

    @Test
    fun `given a special attack`() {
        val rebel = Rebel("Rebel1")
        val objective = Rebel("Objetivo")

        rebel.attack(objective, 4)

        assert(rebel.damageAttacking in 25..29)

        assertEquals(100 - rebel.damageAttacking, objective.life)

        assertEquals(0, rebel.chargeForSpecialAttack)
        assertEquals(3, rebel.remainingHealPotions)
        assertEquals(7, rebel.remainingStrongAttacks)
        assertEquals(5, rebel.remainingVeryStrongAttacks)
    }
}
