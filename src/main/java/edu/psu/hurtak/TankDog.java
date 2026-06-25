package edu.psu.hurtak;

/**
 * Project: TurnBasedCombat242Final
 * Purpose Details: Turn-based cat combat game where Alpha, Explorer, or Yoda fight dog enemies and a three-headed dog boss.
 * Course: IST 242
 * Author: Alexander Matthew Hurtak
 * Date Developed: 06/23/2026
 * Last Date Changed: 06/25/2026
 * Rev: 1
 */

public class TankDog extends Enemy {

    private boolean chargingSpecial;

    public TankDog(int level) {
        super("Tank Dog", 28 + level * 5, 7 + level, 5 + level, 2 + level, 2 + level);
        chargingSpecial = false;
    }

    @Override
    public String special(Character defender) {
        if (specialCooldown > 0) {
            return name + " tries to use a special, but it is recharging.";
        }

        if (!chargingSpecial) {
            chargingSpecial = true;
            return name + " starts charging Mega Chomp!";
        }

        int damage = attack * 4;
        defender.takeDamage(damage);
        chargingSpecial = false;
        specialCooldown = 3;

        return name + " unleashes Mega Chomp for " + damage + " damage!";
    }
}
