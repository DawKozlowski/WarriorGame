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

class StraightBattleSuitTest {
    static Logger log = LoggerFactory.getLogger(BattleSuitTest.class);

    @Test
    void smokeTest() {

        var army1 = new Army();
        army1.addUnits(Warrior::new, 10);

        var army2 = new Army();
        army2.addUnits(Warrior::new, 6);
        army2.addUnits(Lancer::new, 5);

        var army3 = new Army();
        army3.addUnits(Warrior::new, 2);
        army3.addUnits(Knight::new, 1);

        var army4 = new Army();
        army4.addUnits(Knight::new, 1);
        army4.addUnits(Healer::new, 1);
        army4.addUnits(Knight::new, 1);


        assertFalse(Battle.straightFight(army1, army2));
        assertTrue(Battle.straightFight(army3, army4));
    }

    @ParameterizedTest(name = "[{index} {0} is fighting against {1} expecting {2}]")
    @MethodSource("provideArmyPairs")
    void testArmyBattle(Army army1, Army army2, boolean expected) {
        if(log.isTraceEnabled()) {
            log.trace("Army 1 {} fights Army 2 {}", army1.toString(), army2.toString());
        }
        var actual = Battle.straightFight(army1, army2);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideArmyPairs() {
        return Stream.of(
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
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(Lancer::new, 4)
                                .addUnits(Warrior::new, 3)
                                .addUnits(Healer::new, 1)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Knight::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Vampire::new, 2)
                                .addUnits(Lancer::new, 4),
                        true));
    }
}