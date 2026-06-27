package edu.psu.hurtak;

import java.util.Random;

/**
 * Project: TurnBasedCombat242Final
 * Purpose Details: Turn-based cat combat game where Alpha, Explorer, or Yoda fight dog enemies and a three-headed dog boss.
 * Course: IST 242
 * Author(s):  Alexander Matthew Hurtak, Martin Edwin Naugle, Arbi Xepha, Avery Paige Timm
 * Date Developed: 06/23/2026
 * Last Date Changed: 06/25/2026
 * Rev: 1
 */

public class TankDog extends Enemy {

    private boolean chargingSpecial;

    public TankDog(int level, Random rand) {
        super("Tank Dog", 28 + level * 5, 7 + level, 5 + level, 2 + level, 2 + level);
        chargingSpecial = false;
    }

    @Override
    public ActionType chooseAction(Player player) {
        if ((specialCooldown == 0 && random.nextInt(100) < 40) || chargingSpecial) {
            return ActionType.SPECIAL;
        }

        if (currentHP <= maxHP / 4 && random.nextInt(100) < 45) {
            return ActionType.BLOCK;
        }

        return ActionType.ATTACK;
    }

    @Override
    public String special(Character defender) {
        if (specialCooldown > 0) {
            return getName() + " tries to use a special, but it is recharging.";
        }

        if (!chargingSpecial) {
            chargingSpecial = true;
            return getName() + " starts charging Mega Chomp!";
        }

        int damage = calculateDamage(defender, attack * 3, false);
        defender.takeDamage(damage);
        chargingSpecial = false;
        specialCooldown = 3;

        return getName() + " unleashes Mega Chomp for " + damage + " damage!";
    }
}
