package org.example;

import org.example.Models.Defender;
import org.example.Models.Knight;
import org.example.Models.Vampire;
import org.example.Models.Warrior;
import org.example.Services.Battle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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

    @ParameterizedTest(name = "[{index} {0} is fighting against {1} expecting {2}]")
    @MethodSource("provideWarriorPairs")
    void testFight(Warrior warrior1, Warrior warrior2, boolean expected) {
        var actual = Battle.fight(warrior1, warrior2);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideWarriorPairs() {
        return Stream.of(
                Arguments.of(new Warrior(), new Warrior(), true),
                Arguments.of(new Warrior(), new Knight(), false),
                Arguments.of(new Knight(), new Warrior(), true),
                Arguments.of(new Knight(), new Knight(), true)
        );
    }


    @ParameterizedTest(name = "[{index} {0} is fighting against {1} expecting {2}]")
    @MethodSource("provideWarriorPairsIsAlive")
    void testFirstIsAlive(Warrior warrior1, Warrior warrior2, boolean expected) {
        Battle.fight(warrior1, warrior2);
        var actual = warrior1.isAlive();
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideWarriorPairsIsAlive() {
        return Stream.of(
                Arguments.of(new Warrior(), new Warrior(), true),
                Arguments.of(new Warrior(), new Knight(), false),
                Arguments.of(new Knight(), new Warrior(), true),
                Arguments.of(new Knight(), new Knight(), true)
        );
    }

    @Test
    @DisplayName("1. Fight: Warrior vs Warrior. Second Warrior should be dead")
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
    @DisplayName("2. Fight: Warrior vs Knight vs Warrior. Third Warrior should win")
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
    @DisplayName("3. Knight Hits Warrior, warrior health is reduced by 7")
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

    @Test
    @DisplayName("4. Vampire Hits Warrior, vampire health is the same")
    void whenVampireHitsWarrior_Expect_VampireHealthIsTheSame() {
        //arrange
        var warrior =new Warrior();
        var vampire = new Vampire();
        //act
        warrior.hit(vampire);
        vampire.hit(warrior);
        int actualHealth =vampire.getHealth();
        int expectedHealth = Vampire.INITIAL_HEALTH-Warrior.ATTACK+Vampire.ATTACK*Vampire.VAMPIRISM/100;
        //assert
        assertEquals(expectedHealth, actualHealth);
    }


    @Test
    @DisplayName("5. Vampire Hits Warrior, vampire is healed up to max initial health")
    void whenVampireHitsWarrior_Expect_VampireHealedUpToMaxInitialHealth() {
        //arrange
        var warrior =new Warrior();
        var vampire = new Vampire();
        vampire.setHealth(39);
        //act
        vampire.hit(warrior);
        int actualHealth =vampire.getHealth();
        int expectedHealth = Vampire.INITIAL_HEALTH;
        //assert
        assertEquals(expectedHealth, actualHealth);
    }

    @Test
    @DisplayName("6. Vampire Hits Warrior, vampire is healed up")
    void whenVampireHitsWarrior_Expect_VampireHealedUp() {
        //arrange
        var warrior =new Warrior();
        var vampire = new Vampire();
        vampire.setHealth(37);
        warrior.setHealth(1);
        //act
        vampire.hit(warrior);
        int actualHealth =vampire.getHealth();
        int expectedHealth = 39;
        //assert
        assertEquals(expectedHealth, actualHealth);
    }

    @Test
    @DisplayName("7. Vampire Hits Defender, vampire should win")
    void whenVampireHitsWarrior_Expect_VampireWins() {
        //arrange
        var vampire = new Vampire();
        var defender =new Defender();
        //act
        var result= Battle.fight(vampire, defender);
        //assert
        assertEquals(false, result);
    }


}