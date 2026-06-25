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

public abstract class Enemy extends Character {

    public Enemy(String name, int maxHP, int attack, int defense, int dexterity, int luck) {
        super(name, maxHP, attack, defense, dexterity, luck);
    }

    public ActionType chooseAction(Player player) {
        if (currentHP <= maxHP / 4 && random.nextInt(100) < 45) {
            return ActionType.BLOCK;
        }

        if (specialCooldown == 0 && random.nextInt(100) < 40) {
            return ActionType.SPECIAL;
        }

        return ActionType.ATTACK;
    }
}
