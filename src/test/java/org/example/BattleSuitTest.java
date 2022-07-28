package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class BattleSuitTest {

    @Test
    void smokeTest() {
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();
        var mark = new Warrior();

        assertTrue(Battle.fight(chuck, bruce));
        assertFalse(Battle.fight(dave, carl));
        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());
        assertFalse(Battle.fight(carl, mark));
        assertFalse(carl.isAlive());


        var myArmy = new Army();
        myArmy.addUnits(() -> new Knight(), 3);

        var enemyArmy = new Army();
        enemyArmy.addUnits(() -> new Warrior(), 3);

        var army3 = new Army();
        army3.addUnits(() -> new Warrior(), 20);
        army3.addUnits(() -> new Knight(), 5);

        var army4 = new Army();
        army4.addUnits(() -> new Warrior(), 30);

        assertTrue(Battle.fight(myArmy, enemyArmy));
        assertFalse(Battle.fight(army3, army4));
    }

    @Test
    @DisplayName("1. Battle: 1 Warrior vs 2 Warriors. Second Army should win")
    void test01() {
        //arrange
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(() -> new Warrior(), 1);
        army2.addUnits(() -> new Warrior(), 2);
        //act
        var answer = Battle.fight(army1, army2);
        //assert
        assertFalse(answer);
    }

    @Test
    @DisplayName("2. Battle: 2 Warriors vs 3 Warriors. Second Army should win")
    void test02() {
        //arrange
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(() -> new Warrior(), 1);
        army2.addUnits(() -> new Warrior(), 2);
        //act
        var answer = Battle.fight(army1, army2);
        //assert
        assertFalse(answer);
    }

    @Test
    @DisplayName("3. Battle: 5 Warriors vs 7 Warriors. Second Army should win")
    void test03() {
        //arrange
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(() -> new Warrior(), 1);
        army2.addUnits(() -> new Warrior(), 2);
        //act
        var answer = Battle.fight(army1, army2);
        //assert
        assertFalse(answer);
    }

    @Test
    @DisplayName("4. Battle: 20 Warriors vs 21 Warriors. First Army should win")
    void test04() {
        //arrange
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(() -> new Warrior(), 20);
        army2.addUnits(() -> new Warrior(), 21);
        //act
        var answer = Battle.fight(army1, army2);
        //assert
        assertTrue(answer);
    }

    @Test
    @DisplayName("5. Battle: 10 Warriors vs 11 Warriors. First Army should win")
    void test05() {
        //arrange
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(() -> new Warrior(), 10);
        army2.addUnits(() -> new Warrior(), 11);
        //act
        var answer = Battle.fight(army1, army2);
        //assert
        assertTrue(answer);
    }

    @Test
    @DisplayName("6. Battle: 11 Warriors vs 7 Warriors. First Army should win")
    void test06() {
        //arrange
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(() -> new Warrior(), 11);
        army2.addUnits(() -> new Warrior(), 7);
        //act
        var answer = Battle.fight(army1, army2);
        //assert
        assertTrue(answer);
    }

    @Test
    @DisplayName("7. Battle: 11 Warriors vs 7 Warriors and 1 Knight. First Army should win")
    void test07() {
        //arrange
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(() -> new Warrior(), 11);
        army2.addUnits(() -> new Warrior(), 7);
        army2.addUnits(() -> new Knight(), 1);
        //act
        var answer = Battle.fight(army1, army2);
        //assert
        assertTrue(answer);
    }

    @Test
    @DisplayName("8. Battle: 7 Warriors vs 1 Knight. First Army should win")
    void test08() {
        //arrange
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(Unit.UnitType.WARRIOR, 7);
        army2.addUnits(new Warrior(), 1);
        //act
        var answer = Battle.fight(army1, army2);
        //assert
        assertTrue(answer);
    }

    @Test
    @DisplayName("8. Battle: 8 Warriors vs 1 Knight. First Army should win")
    void test09() {
        //arrange
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(Unit.UnitType.WARRIOR, 7);
        army2.addUnits(Warrior.class, 1);
        //act
        var answer = Battle.fight(army1, army2);
        //assert
        assertTrue(answer);
    }

}
