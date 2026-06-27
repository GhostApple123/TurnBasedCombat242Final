package edu.psu.hurtak;

/**
 * Project: TurnBasedCombat242Final
 * Purpose Details: Turn-based cat combat game where Alpha, Explorer, or Yoda fight dog enemies and a three-headed dog boss.
 * Course: IST 242
 * Author(s): Alexander Matthew Hurtak, Martin Edwin Naugle, Arbi Xepha
 * Date Developed: 06/23/2026
 * Last Date Changed: 06/25/2026
 * Rev: 6
 */

public class Player extends Character {

    private int enemiesDefeated;

    public Player(String name, int maxHP, int attack, int defense, int dexterity, int luck) {
        super(name, maxHP, attack, defense, dexterity, luck);
        enemiesDefeated = 0;
    }

    @Override
    public String special(Character defender) {
        if (specialCooldown > 0) {
            return getName() + "'s special move is still recharging for " + specialCooldown + " turn(s).";
        }

        int damage = attack * 2;
        defender.takeDamage(damage);
        specialCooldown = 2;

        return getName() + " uses a powerful cat special move for " + damage + " damage!";
    }

    public void upgradeHP() {
        maxHP += 3;
    }

    public void upgradeAttack() {
        attack += 1;
    }

    public void upgradeDefense() {
        defense += 1;
    }

    public void upgradeDexterity() {
        dexterity += 1;
    }

    public void upgradeLuck() {
        luck += 1;
    }

    public void addDefeatedEnemy() {
        enemiesDefeated++;
    }

    public int getEnemiesDefeated() {
        return enemiesDefeated;
    }
}
