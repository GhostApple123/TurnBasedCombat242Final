package edu.psu.hurtak;

import java.util.Random;

/**
 * Project: TurnBasedCombat242Final
 * Purpose Details: Turn-based cat combat game where Alpha, Explorer, or Yoda fight dog enemies and a three-headed dog boss.
 * Course: IST 242
 * Author: Alexander Matthew Hurtak
 * Date Developed: 06/23/2026
 * Last Date Changed: 06/25/2026
 * Rev: 1
 */

public abstract class Character {

    protected String name;
    protected int maxHP;
    protected int currentHP;
    protected int attack;
    protected int defense;
    protected int dexterity;
    protected int luck;
    protected boolean blocking;
    protected int specialCooldown;
    protected Random random;

    public Character(String name, int maxHP, int attack, int defense, int dexterity, int luck) {
        this.name = name;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.attack = attack;
        this.defense = defense;
        this.dexterity = dexterity;
        this.luck = luck;
        this.blocking = false;
        this.specialCooldown = 0;
        this.random = new Random();
    }

    public String normalAttack(Character defender) {
        boolean hit = checkHit(defender);

        if (!hit) {
            return name + " attacks, but " + defender.getName() + " dodges!";
        }

        int damage = calculateDamage(defender, attack, true);
        defender.takeDamage(damage);

        return name + " attacks " + defender.getName() + " for " + damage + " damage!";
    }

    public void block() {
        blocking = true;
    }

    public abstract String special(Character defender);

    protected boolean checkHit(Character defender) {
        int hitChance = 85 + (dexterity - defender.dexterity) * 5;

        if (hitChance < 20) {
            hitChance = 20;
        }

        return random.nextInt(100) < hitChance;
    }

    protected boolean checkCritical(Character defender) {
        int critChance = 10 + (luck - defender.luck) * 3;

        if (critChance < 5) {
            critChance = 5;
        }

        if (critChance > 50) {
            critChance = 50;
        }

        return random.nextInt(100) < critChance;
    }

    protected int calculateDamage(Character defender, int baseAttack, boolean allowCritical) {
        int damage = baseAttack - defender.defense;

        if (defender.blocking) {
            damage = damage * 3 / 4;
            defender.blocking = false;
        }

        if (damage < 1) {
            damage = 1;
        }

        if (allowCritical && checkCritical(defender)) {
            damage = damage * 2;
        }

        return damage;
    }

    protected int calculateUnblockedDamage(Character defender, int baseAttack, boolean allowCritical) {
        int damage = baseAttack - defender.defense;

        if (damage < 1) {
            damage = 1;
        }

        defender.blocking = false;

        if (allowCritical && checkCritical(defender)) {
            damage = damage * 2;
        }

        return damage;
    }

    public void takeDamage(int damage) {
        currentHP = currentHP - damage;

        if (currentHP < 0) {
            currentHP = 0;
        }
    }

    public void lowerCooldown() {
        if (specialCooldown > 0) {
            specialCooldown--;
        }
    }

    public boolean isAlive() {
        return currentHP > 0;
    }

    public void restoreHealth() {
        currentHP = maxHP;
    }

    public String getName() {
        return name;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getLuck() {
        return luck;
    }

    public int getSpecialCooldown() {
        return specialCooldown;
    }

    public boolean isBlocking() {
        return blocking;
    }
}
