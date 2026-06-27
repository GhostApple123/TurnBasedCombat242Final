package edu.psu.hurtak;

import java.util.Random;

/**
 * Project: TurnBasedCombat242Final
 * Purpose Details: Turn-based cat combat game where Alpha, Explorer, or Yoda fight dog enemies and a three-headed dog boss.
 * Course: IST 242
 * Author(s): Alexander Matthew Hurtak, Martin Edwin Naugle, Arbi Xepha
 * Date Developed: 06/23/2026
 * Last Date Changed: 06/25/2026
 * Rev: 1
 */

public class FighterDog extends Enemy {

    public FighterDog(int level, Random rand) {
        super("Fighter Dog", 18 + level * 3, 6 + level, 2 + level, 5 + level, 3 + level);
    }

    @Override
    public String special(Character defender) {
        if (specialCooldown > 0) {
            return getName() + " tries to use a special, but it is recharging.";
        }

        int damage = calculateDamage(defender, attack * 2, false);
        defender.takeDamage(damage);
        specialCooldown = 2;

        return getName() + " uses Power Bite for " + damage + " damage!";
    }
}
