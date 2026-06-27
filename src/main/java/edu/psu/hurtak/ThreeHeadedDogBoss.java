package edu.psu.hurtak;

import java.util.Random;

/**
 * Project: TurnBasedCombat242Final
 * Purpose Details: Final boss enemy for the turn-based cat combat game.
 * Course: IST 242
 * Author(s): Alexander Matthew Hurtak, Martin Edwin Naugle, Arbi Xepha, Avery Paige Timm
 * Rev: 3
 */
public class ThreeHeadedDogBoss extends Enemy {

    public ThreeHeadedDogBoss(int level, Random rand) {
        super("Three-Headed Dog Boss",
                45 + level * 5,
                6 + level,
                3 + level,
                5 + level,
                3 + level);
    }

    @Override
    public String special(Character defender) {
        if (specialCooldown > 0) {
            return getName() + " growls while its special recharges.";
        }

        int damage = calculateDamage(defender, attack * 2, false);
        defender.takeDamage(damage);
        specialCooldown = 3;

        return getName() + " uses Triple Bite for " + damage + " damage!";
    }
}