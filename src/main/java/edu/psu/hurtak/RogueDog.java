package edu.psu.hurtak;

import java.util.Random;

/**
 * Project: TurnBasedCombat242Final
 * Purpose Details: Turn-based cat combat game where Alpha, Explorer, or Yoda fight dog enemies and a three-headed dog boss.
 * Course: IST 242
 * Author(s): Alexander Matthew Hurtak
 * Date Developed: 06/23/2026
 * Last Date Changed: 06/25/2026
 * Rev: 2
 */

public class RogueDog extends Enemy {

    public RogueDog(int level, Random rand) {
        super("Rogue Dog", 14 + level * 2, 5 + level, 1 + level, 9 + level, 5 + level);
    }

    @Override
    public String special(Character defender) {
        if (specialCooldown > 0) {
            return getName() + " tries to use a special, but it is recharging.";
        }

        int damage = calculateUnblockedDamage(defender, attack + 3, false);
        defender.takeDamage(damage);
        specialCooldown = 2;

        return getName() + " uses Sneaky Bite and hits through block for " + damage + " damage!";
    }
}
