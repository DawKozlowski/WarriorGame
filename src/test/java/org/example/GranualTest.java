package org.example;

import org.example.models.*;
import org.example.models.weapons.Weapon;
import org.example.services.Battle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GranualTest {

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
