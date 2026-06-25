package edu.psu.hurtak;

/**
 * Project: TurnBasedCombat242Final
 * Purpose Details: Turn-based cat combat game where Alpha, Explorer, or Yoda fight dog enemies and a three-headed dog boss.
 * Course: IST 242
 * Author: Alexander Matthew Hurtak
 * Date Developed: 06/23/2026
 * Last Date Changed: 06/25/2026
 * Rev: 2
 */

public class ThreeHeadedDogBoss extends Enemy {

    public ThreeHeadedDogBoss(int level) {
        super("Three-Headed Dog Boss", 60 + level * 6, 10 + level, 6 + level, 6 + level, 5 + level);
    }

    @Override
    public String special(Character defender) {
        if (specialCooldown > 0) {
            return name + " growls while its special recharges.";
        }

        int damage = attack * 3;
        defender.takeDamage(damage);
        specialCooldown = 3;

        return name + " uses Triple Bite for " + damage + " damage!";
    }
}
