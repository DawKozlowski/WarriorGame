package org.example;

import org.example.models.*;
import org.example.models.weapons.Weapon;
import org.example.services.Battle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BomberTest {

    static Logger log = LoggerFactory.getLogger(BomberTest.class);

    @Test
    void smokeTest() {
        var chuck = new Bomber();
        var bruce = new Bomber();
        var adam = new Bomber();
        var carl = new Knight();

        assertFalse(Battle.fight(chuck, bruce));
        assertFalse(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertFalse(Battle.fight(carl, adam));
        assertFalse(carl.isAlive());
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
                                .addUnits(Bomber::new, 1)
                                .addUnits(Defender::new, 2)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4),
                        new Army()
                                .addUnits(Vampire::new, 2)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 3),
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(Bomber::new, 1)
                                .addUnits(Lancer::new, 7)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Healer::new, 1)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Defender::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 2)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 4),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Bomber::new, 1)
                                .addUnits(Lancer::new, 1)
                                .addUnits(Warrior::new, 3)
                                .addUnits(Healer::new, 1)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Knight::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 2)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 4),
                        false)
        );
    }


    @Test
    @DisplayName("7. Bomber Fights Warrior, Bomber should win")
    void whenBomberFightsWarrior_Expect_BomberWins() {
        //arrange
        var bomber = new Bomber();
        var warrior =new Warrior();
        //act
        var actual=Battle.fight(bomber, warrior);
        //assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("5. Bomber fights 3 Vampires. Third Vampire should have 20 health")
    void whenBomberFights3Vampires_Expect_ThirdVampireHealthIs20() {
        //arrange
        var army1= new Army().addUnits(Bomber::new, 1);
        var army2= new Army().addUnits(Vampire::new, 3);
        var expected=20;
        //act
        Battle.fight(army1,army2);
        var actual= army2.getWarrior(2).getHealth();
        //assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("5. 2 Bombers fight 4 Lancers. Last Lancer should have 0 health")
    void when2BombersFight4Lancers_Expect_LastLancerHave0Health() {
        //arrange
        var army1= new Army().addUnits(Bomber::new, 2);
        var army2= new Army().addUnits(Lancer::new, 4);
        var expected=0;
        //act
        Battle.fight(army1,army2);
        var actual= army2.getWarrior(3).getHealth();
        //assert
        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("6. 2 Bombers fight 5 Lancers. Last Lancer should have 50 health")
    void when2BombersFight5Lancers_Expect_LastLancerHave50Health() {
        //arrange
        var army1= new Army().addUnits(Bomber::new, 2);
        var army2= new Army().addUnits(Lancer::new, 5);
        var expected=Lancer.INITIAL_HEALTH;
        //act
        Battle.fight(army1,army2);
        var actual= army2.getWarrior(4).getHealth();
        //assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("7. Bomber fights 1 Lancer with 3 Shields. Lancer should have 10 health")
    void whenBomberFights1LancersWith3Shields_Expect_LancerHave10Health() {
        //arrange
        var bomber =new Bomber();
        var lancer = new Lancer().equipWeapon(Weapon.newShield()).equipWeapon(Weapon.newShield()).equipWeapon(Weapon.newShield());
        var expected=10;
        //act
        Battle.fight(bomber,lancer);
        var actual= lancer.getHealth();
        //assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("7. Bomber with 3 Shields fights 4 Lancers. Last Lancer should have 0 health")
    void whenBomberWith3ShieldsFights3Lancers_Expect_LastLancerHave10Health() {
        //arrange
        var army1= new Army().addUnits(Bomber::new, 1).equipWarriorAtPosition(0, Weapon.newShield()).equipWarriorAtPosition(0, Weapon.newShield()).equipWarriorAtPosition(0, Weapon.newShield());
        var army2= new Army().addUnits(Lancer::new, 4);
        var expected=0;
        //act
        Battle.fight(army1,army2);
        var actual= army2.getWarrior(3).getHealth();
        //assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("8. Bomber fights 2 Defenders. Last Defender should have 22 health")
    void whenBomberFights2LDefenders_Expect_LastDefenderHave22Health() {
        //arrange
        var army1 = new Army().addUnits(Bomber::new, 1);
        var army2 = new Army().addUnits(Defender::new, 2);
        var expected = 22;
        //act
        Battle.fight(army1, army2);
        var actual = army2.getWarrior(1).getHealth();
        //assert
        assertEquals(expected, actual);
    }
}
