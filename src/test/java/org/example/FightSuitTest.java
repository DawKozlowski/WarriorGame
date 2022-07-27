package org.example;

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

        var res1=Battle.fight(chuck, bruce);
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
    void test01() {
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
    void test02() {
        var carl = new Knight();
        var jim = new Warrior();

        var answer = Battle.fight(carl, jim);

        assertTrue(answer);
    }

    @Test
    @DisplayName("3. Fight: Warrior vs Warrior. First Warrior should be alive")
    void test03() {
        var carl = new Warrior();
        var jim = new Warrior();

        Battle.fight(carl, jim);
        var answer = carl.isAlive();

        assertTrue(answer);
    }

    @Test
    @DisplayName("4. Fight: Knight vs Warrior. Knight should be alive")
    void test04() {
        var carl = new Knight();
        var jim = new Warrior();

        Battle.fight(carl, jim);
        var answer = carl.isAlive();

        assertTrue(answer);
    }

    @Test
    @DisplayName("5. Fight: Warrior vs Warrior. First Warrior should be alive")
    void test05() {
        var carl = new Warrior();
        var jim = new Warrior();

        Battle.fight(carl, jim);
        var answer = jim.isAlive();

        assertFalse(answer);
    }

    @Test
    @DisplayName("6. Fight: Warrior vs Knight. Knight should be alive")
    void test06() {
        var carl = new Warrior();
        var jim = new Knight();

        Battle.fight(carl, jim);
        var answer = jim.isAlive();

        assertTrue(answer);
    }

    @Test
    @DisplayName("7. Fight: Warrior vs Knight vs Warrior. Third Warrior should win")
    void test07() {
        var carl = new Warrior();
        var jim = new Knight();
        var bob = new Warrior();

        Battle.fight(carl, jim);
        var answer = Battle.fight(jim, bob);

        assertFalse(answer);
    }

    @Test
    @DisplayName("8. Knight Hits Warrior, warrior health is reduced by 7")
    void test08() {
        var knight =new Knight();
        var warrior =new Warrior();

        knight.hit(warrior);
        int actualHealth =warrior.getHealth();

        int expectedHealth = Warrior.INITIAL_HEALTH-Knight.ATTACK;
        assertEquals(expectedHealth, actualHealth);
    }

}