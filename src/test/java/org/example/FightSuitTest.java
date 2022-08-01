package org.example;

import org.example.Models.Knight;
import org.example.Models.Warrior;
import org.example.Services.Battle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FightSuitTest {
    @Test
    void smokeTest() {
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();

        var res1= Battle.fight(chuck, bruce);
        Assertions.assertTrue(res1);

        var res2=Battle.fight(dave, carl);
        assertFalse(res2);

        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());
    }

    @Test
    @DisplayName("1. Fight: Warrior vs Knight. Knight should win")
    void whenWarriorFightsKnight_Expect_KnightWins() {
        //arrange
        var carl = new Warrior();
        var jim = new Knight();
        //act
        var answer = Battle.fight(carl, jim);
        //assert
        assertFalse(answer);
    }

    @Test
    @DisplayName("2. Fight: Knight vs Warrior. Knight should win")
    void whenKnightFightsWarrior_Expect_KnightWins() {
        //arrange
        var carl = new Knight();
        var jim = new Warrior();
        //act
        var answer = Battle.fight(carl, jim);
        //assert
        assertTrue(answer);
    }

    @Test
    @DisplayName("3. Fight: Warrior vs Warrior. First Warrior should be alive")
    void whenWarriorFightsWarrior_Expect_FirstWarriorIsAlive() {
        //arrange
        var carl = new Warrior();
        var jim = new Warrior();
        //act
        Battle.fight(carl, jim);
        var answer = carl.isAlive();
        //assert
        assertTrue(answer);
    }

    @Test
    @DisplayName("4. Fight: Knight vs Warrior. Knight should be alive")
    void whenKnightFightsWarrior_Expect_KnightIsAlive() {
        //arrange
        var carl = new Knight();
        var jim = new Warrior();
        //act
        Battle.fight(carl, jim);
        var answer = carl.isAlive();
        //assert
        assertTrue(answer);
    }

    @Test
    @DisplayName("5. Fight: Warrior vs Warrior. Second Warrior should be alive")
    void whenWarriorFightsWarrior_Expect_SecondWarriorIsAlive() {
        //arrange
        var carl = new Warrior();
        var jim = new Warrior();
        //act
        Battle.fight(carl, jim);
        var answer = jim.isAlive();
        //assert
        assertFalse(answer);
    }

    @Test
    @DisplayName("6. Fight: Warrior vs Knight. Knight should be alive")
    void whenWarriorFightsKnight_Expect_KnightIsAlive() {
        //arrange
        var carl = new Warrior();
        var jim = new Knight();
        //act
        Battle.fight(carl, jim);
        var answer = jim.isAlive();
        //assert
        assertTrue(answer);
    }

    @Test
    @DisplayName("7. Fight: Warrior vs Knight vs Warrior. Third Warrior should win")
    void whenWarriorFightsKnightAndKnightFightsWarrior_Expect_ThirdWarriorWins() {
        //arrange
        var carl = new Warrior();
        var jim = new Knight();
        var bob = new Warrior();
        //act
        Battle.fight(carl, jim);
        var answer = Battle.fight(jim, bob);
        //assert
        assertFalse(answer);
    }

    @Test
    @DisplayName("8. Knight Hits Warrior, warrior health is reduced by 7")
    void whenKnightHitsWarrior_Expect_WarriorHealthIsReducedBy7() {
        //arrange
        var knight =new Knight();
        var warrior =new Warrior();
        //act
        knight.hit(warrior);
        int actualHealth =warrior.getHealth();
        int expectedHealth = Warrior.INITIAL_HEALTH-Knight.ATTACK;
        //assert
        assertEquals(expectedHealth, actualHealth);
    }




}