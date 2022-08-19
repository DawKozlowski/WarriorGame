package org.example;

import org.example.models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GranualTest {

    class Rookie extends Warrior {
        @Override
        public int getAttack() {
            return 1;
        }
    }

    @Test
    @DisplayName("1. Rookie hits Defender. Defenders Health should not increase")
    void whenRookieHitsDefender_Expect_DefenderHealthNotIncreased() {
        //arrange
        var rookie = new Rookie();
        var defender = new Defender();
        var defenderInitialHealth = defender.INITIAL_HEALTH;
        //act
        rookie.hit(defender);
        //assert
        var expectedHealthAfterRookieHit = defender.getHealth();
        assertEquals(expectedHealthAfterRookieHit, defenderInitialHealth);
    }

    @Test
    @DisplayName("2. Defender hits Warrior. Warriors Health should be decreased by 3")
    void whenDefenderHitsWarrior_Expect_WarriorsHealthDecreasedBy3() {
        //arrange
        var defender = new Defender();
        var warrior = new Warrior();
        var warriorInitialHealth = warrior.INITIAL_HEALTH;
        //act
        defender.hit(warrior);
        //assert
        var actualDifference = warriorInitialHealth - warrior.getHealth();
        var expectedDifference = 3;
        assertEquals(expectedDifference, actualDifference);
    }

    @Test
    @DisplayName("3. Vampire hits Defender. Vampire Health should increased to 38")
    void whenVampireHitsDefender_Expect_VampireHealthIncreasedTo38() {
        //arrange
        var vampire = new Vampire();
        vampire.setHealth(37);
        var defender = new Defender();
        //act
        vampire.hit(defender);
        //assert
        int expectedHealth = 38;
        int actualHealth = vampire.getHealth();
        assertEquals(expectedHealth, actualHealth);
    }

    @Test
    @DisplayName("4. Healer heals Warrior. Warrior Health should be healed up to 50")
    void whenHealerHealsWarrior_Expect_WarriorHealthHealedUpTo50() {
        //arrange
        var warrior = new Warrior();
        var healer = new Healer();
        var expectedHealthAfterHeal = 50;
        var expectedHealthBeforeHeal = 49;
        warrior.setHealth(expectedHealthBeforeHeal);
        //act
        healer.heal(warrior);
        var warriorHealthAfterHeal = warrior.getHealth();
        //assert
        assertEquals(expectedHealthAfterHeal, warriorHealthAfterHeal);
    }
}
