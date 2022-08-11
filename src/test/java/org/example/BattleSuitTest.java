package org.example;

import org.example.models.*;
import org.example.services.Battle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
public class BattleSuitTest {
    static Logger log = LoggerFactory.getLogger(BattleSuitTest.class);
    @Test
    void smokeTest() {
        var myArmy = new Army();
        myArmy.addUnits(Defender::new, 2);
        myArmy.addUnits(Healer::new, 1);
        myArmy.addUnits(Vampire::new, 2);
        myArmy.addUnits(Lancer::new, 2);
        myArmy.addUnits(Healer::new, 1);
        myArmy.addUnits(Warrior::new, 1);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Warrior::new, 2);
        enemyArmy.addUnits(Lancer::new, 4);
        enemyArmy.addUnits(Healer::new, 1);
        enemyArmy.addUnits(Defender::new, 2);
        enemyArmy.addUnits(Vampire::new, 3);
        enemyArmy.addUnits(Healer::new, 1);

        var army3 = new Army();
        army3.addUnits(Warrior::new, 1);
        army3.addUnits(Lancer::new, 1);
        army3.addUnits(Healer::new, 1);
        army3.addUnits(Defender::new, 2);

        var army4 = new Army();
        army4.addUnits(Vampire::new, 3);
        army4.addUnits(Warrior::new, 1);
        army4.addUnits(Healer::new, 1);
        army4.addUnits(Lancer::new, 2);

        assertFalse(Battle.fight(myArmy, enemyArmy));
        assertTrue(Battle.fight(army3, army4));
    }

    @ParameterizedTest(name = "[{index} {0} is fighting against {1} expecting {2}]")
    @MethodSource("provideArmyPairs")
    void testArmyBattle(Army army1, Army army2, boolean expected) {
        if(log.isTraceEnabled()) {
            log.trace("Army 1 {} fights Army 2 {}", army1.toString(), army2.toString());
        }
        var actual = Battle.fight(army1, army2);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideArmyPairs() {
        return Stream.of(
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 1),
                        new Army()
                                .addUnits(Warrior::new, 2),
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 3),
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 5),
                        new Army()
                                .addUnits(Warrior::new, 7),
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 20),
                        new Army()
                                .addUnits(Warrior::new, 21),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 10),
                        new Army()
                                .addUnits(Warrior::new, 11),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 11),
                        new Army()
                                .addUnits(Warrior::new, 7),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 11),
                        new Army()
                                .addUnits(Warrior::new, 7)
                                .addUnits(Knight::new, 1),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 7),
                        new Army()
                                .addUnits(Knight::new, 1),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 8),
                        new Army()
                                .addUnits(Knight::new, 1),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Defender::new, 5)
                                .addUnits(Warrior::new, 20),
                        new Army()
                                .addUnits(Warrior::new, 21)
                                .addUnits(Defender::new, 4),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Defender::new, 5)
                                .addUnits(Warrior::new, 10),
                        new Army()
                                .addUnits(Warrior::new, 5)
                                .addUnits(Defender::new, 10),
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 5),
                        new Army()
                                .addUnits(Warrior::new, 1)
                                .addUnits(Defender::new, 3),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Defender::new, 2)
                                .addUnits(Warrior::new, 1),
                        new Army()
                                .addUnits(Defender::new, 1)
                                .addUnits(Warrior::new, 5),
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(Defender::new, 5)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Warrior::new, 7),
                        new Army()
                                .addUnits(Warrior::new, 6)
                                .addUnits(Defender::new, 6)
                                .addUnits(Vampire::new, 6),
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(Defender::new, 2)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 3),
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(Defender::new, 11)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 13),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Defender::new, 9)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 8),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 13),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Lancer::new, 5)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 5),
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(Lancer::new, 7)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 4),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Lancer::new, 1),
                        new Army()
                                .addUnits(Lancer::new, 1)
                                .addUnits(Knight::new, 1),
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(Lancer::new, 7)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Healer::new, 1)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Defender::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 4),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Lancer::new, 1)
                                .addUnits(Warrior::new, 3)
                                .addUnits(Healer::new, 1)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Knight::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 4),
                        false)
        );
    }



}
