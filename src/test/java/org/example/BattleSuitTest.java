package org.example;

import org.example.Models.*;
import org.example.Services.Battle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;
public class BattleSuitTest {

    static Logger log = LoggerFactory.getLogger(BattleSuitTest.class);
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
    void when1WarriorArmyAttacks2WarriorsArmy_Expect_SecondArmyWins() {
        //arrange
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(() -> new Knight(), 1);
        army2.addUnits(() -> new Warrior(), 2);
        if(log.isTraceEnabled()) {
            log.trace("Army 1 {} fights Army 2 {}", army1.toString(), army2.toString());
        }
        //act
        var answer = Battle.fight(army1, army2);
        //assert
        assertFalse(answer);
    }

    @Test
    @DisplayName("2. Battle: 2 Warriors vs 3 Warriors. Second Army should win")
    void when2WarriorsArmyAttacks3WarriorsArmy_Expect_SecondArmyWins() {
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
    void when5WarriorsArmyAttacks7WarriorsArmy_Expect_SecondArmyWins() {
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
    void when20WarriorsArmyAttacks21WarriorsArmy_Expect_FirstArmyWins() {
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
    void when10WarriorsArmyAttacks11WarriorsArmy_Expect_FirstArmyWins() {
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
    void when11WarriorsArmyAttacks7WarriorsArmy_Expect_FirstArmyWins() {
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
    void when11WarriorsArmyAttacks7WarriorsAnd1KnightArmy_Expect_FirstArmyWins() {
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
    void when7WarriorsArmyAttacks1KnightArmy_Expect_FirstArmyWins() {
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
    @DisplayName("9. Battle: 8 Warriors vs 1 Knight. First Army should win")
    void when8WarriorsArmyAttacks1KnightArmy_Expect_FirstArmyWins() {
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

    @Test
    @DisplayName("10. Battle: 5 Defenders and 20 Warriors vs 21 Warriors and 4 Defenders. First Army shloud win")
    void when5DefendersAnd20WarriorsArmyAttacks21WarriorsAnd4DefendersArmy_Expect_FirstArmyWins() {
        //arrange
        var army1 = new Army();
        var army2=  new Army();
        army1.addUnits(() -> new Defender(), 5);
        army1.addUnits(() -> new Warrior(), 20);
        army2.addUnits(() -> new Defender(), 4);
        army2.addUnits(() -> new Warrior(), 21);
        //act
        var answer = Battle.fight(army1, army2);
        //assert
        assertTrue(answer);
    }

    @Test
    @DisplayName("11. Battle: 5 Defenders and 10 Warriors vs 5 Warriors and 10 Defenders. Second Army shloud win")
    void when5DefendersAnd10WarriorsArmyAttacks5WarriorsAnd10DefendersArmy_Expect_SecondArmyWins() {
        //arrange
        var army1 = new Army();
        var army2=  new Army();
        army1.addUnits(() -> new Defender(), 5);
        army1.addUnits(() -> new Warrior(), 10);
        army2.addUnits(() -> new Defender(), 10);
        army2.addUnits(() -> new Warrior(), 5);
        //act
        var answer = Battle.fight(army1, army2);
        //assert
        assertFalse(answer);
    }

    @Test
    @DisplayName("12. Battle: 5 Warriors vs 1 Warriors and 3 Defenders. Second Army shloud win")
    void when5WarriorsArmyAttacks1WarriorAnd3DefendersArmy_Expect_SecondArmyWins() {
        //arrange
        var army1 = new Army();
        var army2=  new Army();
        army1.addUnits(() -> new Defender(), 2);
        army1.addUnits(() -> new Warrior(), 1);
        army1.addUnits(() -> new Defender(), 1);
        army2.addUnits(() -> new Warrior(), 5);
        //act
        var answer = Battle.fight(army1, army2);
        //assert
        assertFalse(answer);
    }

}
