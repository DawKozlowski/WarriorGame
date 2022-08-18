package org.example.models.weapons;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Weapon implements IWeapon {
   private int health;
   private int attack;
   private int defense;
   private int vampirism;
   private int healPower;
   private int bombAttack;


   public static Weapon newSword() {
      return Weapon.builder()
              .attack(2)
              .health(5)
              .build();
   }

   public static Weapon newShield() {
      return Weapon.builder()
              .attack(-1)
              .defense(2)
              .health(20)
              .build();
   }

   public static Weapon newGreatAxe() {
      return Weapon.builder()
              .attack(5)
              .health(-15)
              .defense(-2)
              .vampirism(20)
              .build();
   }

   public static Weapon newKatana() {
      return Weapon.builder()
              .attack(6)
              .health(-20)
              .defense(-5)
              .vampirism(50)
              .build();
   }

   public static Weapon newMagicWand() {
      return Weapon.builder()
              .attack(3)
              .health(30)
              .healPower(3)
              .build();
   }

   public static Weapon newBigBomb() {
      return Weapon.builder()
              .bombAttack(100)
              .build();
   }

   public static Weapon.WeaponBuilder newCustomWeapon() {
      return Weapon.builder();
   }

}