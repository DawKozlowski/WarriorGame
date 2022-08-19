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

class Rookie extends Warrior {
    public Rookie() {
    }

    @Override
    public int getAttack() {
        return 1;
    }
}

class WeaponSuitTest {


    static Logger log = LoggerFactory.getLogger(WeaponSuitTest.class);

    @Test
    void smokeTest() {
        var ogre = new Warrior();
        var lancelot = new Knight();
        var richard = new Defender();
        var eric = new Vampire();
        var freelancer = new Lancer();
        var priest = new Healer();


        var sword = Weapon.newSword();
        var shield = Weapon.newShield();
        var axe = Weapon.newGreatAxe();
        var katana = Weapon.newKatana();
        var wand = Weapon.newMagicWand();

        var superWeapon = Weapon.newCustomWeapon().health(50).attack(10).defense(5).vampirism(150).healPower(8).build();

        ogre.equipWeapon(sword);
        ogre.equipWeapon(shield);
        ogre.equipWeapon(superWeapon);
        lancelot.equipWeapon(superWeapon);
        richard.equipWeapon(shield);
        eric.equipWeapon(superWeapon);
        freelancer.equipWeapon(axe);
        freelancer.equipWeapon(katana);
        priest.equipWeapon(wand);
        priest.equipWeapon(shield);

        assertEquals(125,  ogre.getHealth());
        assertEquals(17, lancelot.getAttack());
        assertEquals(4, richard.getDefense());
        assertEquals(200, eric.getVampirism());
        assertEquals(15, freelancer.getHealth());
        assertEquals(5, priest.getHealPower());

        assertFalse(Battle.fight(ogre, eric));
        assertFalse(Battle.fight(priest, richard));
        assertTrue( Battle.fight(lancelot, freelancer));


        var myArmy = new Army();
        myArmy.addUnits(Knight::new, 1);
        myArmy.addUnits(Lancer::new, 1);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Vampire::new, 1);
        enemyArmy.addUnits(Healer::new, 1);

        myArmy.equipWarriorAtPosition(0, axe);
        myArmy.equipWarriorAtPosition(1, superWeapon);

        enemyArmy.equipWarriorAtPosition(0, katana);
        enemyArmy.equipWarriorAtPosition(1, wand);

        assertTrue(Battle.fight(myArmy, enemyArmy));
    }

    @Test
    @DisplayName("1. Warrior Fights Vampire, Warrior should win")
    void whenWarriorHitsVampire_Expect_WarriorWins() {
        //arrange
        var warrior =new Warrior();
        var vampire = new Vampire();
        var weapon1 = Weapon.newCustomWeapon().health(-10).attack(5).defense(0).vampirism(40).healPower(0).build();
        var weapon2 = Weapon.newSword();
        warrior.equipWeapon(weapon1);
        vampire.equipWeapon(weapon2);
        //act
        var actual= Battle.fight(warrior, vampire);
        //assert
        assertTrue(actual);
    }

    @Test
    @DisplayName("2. Defender Fights Lancer, Lancer should win")
    void whenDefenderFightsLancer_Expect_LancerWins() {
        //arrange
        var defender =new Defender();
        var lancer = new Lancer();
        var weapon1 = Weapon.newShield();
        var weapon2 = Weapon.newGreatAxe();
        defender.equipWeapon(weapon1);
        lancer.equipWeapon(weapon2);
        //act
        var actual= Battle.fight(defender, lancer);
        //assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("3. Healer Fights Knight, Knight should win")
    void whenHealerFightsKnight_Expect_KnightWins() {
        //arrange
        var healer =new Healer();
        var knight = new Knight();
        var weapon1 = Weapon.newMagicWand();
        var weapon2 = Weapon.newKatana();
        healer.equipWeapon(weapon1);
        knight.equipWeapon(weapon2);
        //act
        var actual= Battle.fight(healer, knight);
        //assert
        assertFalse(actual);
    }

    @Test
    @DisplayName("4. Defender Fights Vampire, Vampire should win")
    void whenDefenderFightsVampire_Expect_VampireWins() {
        //arrange
        var defender =new Defender();
        var vampire = new Vampire();
        var weapon1 = Weapon.newShield();
        var weapon2 = Weapon.newMagicWand();
        var weapon3 = Weapon.newShield();
        var weapon4 = Weapon.newKatana();
        defender.equipWeapon(weapon1);
        defender.equipWeapon(weapon2);
        vampire.equipWeapon(weapon3);
        vampire.equipWeapon(weapon4);
        //act
        var actual= Battle.fight(defender, vampire);
        //assert
        assertFalse(actual);
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
                                .addUnits(Knight::new, 1)
                                .addUnits(Lancer::new, 1)
                                .equipWarriorAtPosition(0, Weapon.newMagicWand())
                                .equipWarriorAtPosition(1, Weapon.newGreatAxe()),
                        new Army()
                                .addUnits(Vampire::new, 1)
                                .addUnits(Healer::new, 1)
                                .equipWarriorAtPosition(0, Weapon.newMagicWand())
                                .equipWarriorAtPosition(1, Weapon.newGreatAxe()),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Defender::new, 1)
                                .addUnits(Warrior::new, 1)
                                .equipWarriorAtPosition(0, Weapon.newGreatAxe())
                                .equipWarriorAtPosition(1, Weapon.newGreatAxe()),
                        new Army()
                                .addUnits(Knight::new, 1)
                                .addUnits(Healer::new, 1)
                                .equipWarriorAtPosition(0, Weapon.newSword())
                                .equipWarriorAtPosition(1, Weapon.newSword()),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Defender::new, 2)
                                .equipWarriorAtPosition(0, Weapon.newKatana())
                                .equipWarriorAtPosition(1, Weapon.newKatana()),
                        new Army()
                                .addUnits(Knight::new, 1)
                                .addUnits(Vampire::new, 1)
                                .equipWarriorAtPosition(0, Weapon.newKatana())
                                .equipWarriorAtPosition(1, Weapon.newKatana()),
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(Knight::new, 3)
                                .equipWarriorAtPosition(0, Weapon.newCustomWeapon()
                                        .health(-20).attack(6).defense(1).vampirism(40).healPower(-2).build())
                                .equipWarriorAtPosition(1, Weapon.newCustomWeapon()
                                        .health(-20).attack(6).defense(1).vampirism(40).healPower(-2).build())
                                .equipWarriorAtPosition(2, Weapon.newCustomWeapon()
                                        .health(20).attack(-2).defense(2).vampirism(-55).healPower(3).build()),
                        new Army()
                                .addUnits(Warrior::new, 1)
                                .addUnits(Defender::new, 2)
                                .equipWarriorAtPosition(0, Weapon.newCustomWeapon()
                                        .health(-20).attack(6).defense(1).vampirism(40).healPower(-2).build())
                                .equipWarriorAtPosition(1, Weapon.newCustomWeapon()
                                        .health(20).attack(-2).defense(2).vampirism(-55).healPower(3).build())
                                .equipWarriorAtPosition(2, Weapon.newCustomWeapon()
                                        .health(20).attack(-2).defense(2).vampirism(-55).healPower(3).build()),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Vampire::new, 3)
                                .equipWarriorAtPosition(0, Weapon.newCustomWeapon()
                                        .health(-20).attack(1).defense(1).vampirism(40).healPower(-2).build())
                                .equipWarriorAtPosition(1, Weapon.newCustomWeapon()
                                        .health(-20).attack(1).defense(1).vampirism(40).healPower(-2).build())
                                .equipWarriorAtPosition(2, Weapon.newCustomWeapon()
                                        .health(20).attack(2).defense(2).vampirism(-55).healPower(3).build()),
                        new Army()
                                .addUnits(Warrior::new, 1)
                                .addUnits(Defender::new, 2)
                                .equipWarriorAtPosition(0, Weapon.newCustomWeapon()
                                        .health(-20).attack(1).defense(1).vampirism(40).healPower(-2).build())
                                .equipWarriorAtPosition(1, Weapon.newCustomWeapon()
                                        .health(20).attack(2).defense(2).vampirism(-55).healPower(3).build())
                                .equipWarriorAtPosition(2, Weapon.newCustomWeapon()
                                        .health(20).attack(2).defense(2).vampirism(-55).healPower(3).build()),
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(Vampire::new, 2)
                                .addUnits(Rookie::new, 2)
                                .equipWarriorAtPosition(0, Weapon.newKatana())
                                .equipWarriorAtPosition(1, Weapon.newKatana())
                                .equipWarriorAtPosition(2, Weapon.newShield()),
                        new Army()
                                .addUnits(Warrior::new, 1)
                                .addUnits(Defender::new, 2)
                                .equipWarriorAtPosition(0, Weapon.newKatana())
                                .equipWarriorAtPosition(1, Weapon.newShield())
                                .equipWarriorAtPosition(2, Weapon.newShield()),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Vampire::new, 3)
                                .equipWarriorAtPosition(0, Weapon.newGreatAxe())
                                .equipWarriorAtPosition(1, Weapon.newGreatAxe())
                                .equipWarriorAtPosition(2, Weapon.newGreatAxe()),
                        new Army()
                                .addUnits(Warrior::new, 1)
                                .addUnits(Defender::new, 1)
                                .equipWarriorAtPosition(0, Weapon.newSword())
                                .equipWarriorAtPosition(1, Weapon.newSword()),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Rookie::new, 3)
                                .equipWarriorAtPosition(0, Weapon.newKatana())
                                .equipWarriorAtPosition(1, Weapon.newKatana())
                                .equipWarriorAtPosition(2, Weapon.newKatana()),
                        new Army()
                                .addUnits(Defender::new, 1)
                                .addUnits(Healer::new, 1)
                                .equipWarriorAtPosition(0, Weapon.newMagicWand())
                                .equipWarriorAtPosition(1, Weapon.newMagicWand()),
                        false));


    }
}
