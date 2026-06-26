# TurnBasedCombatGame242Final

## Project Overview

This project is a Java turn-based combat game created for IST 242. The game is based around Professor Oakes' cats: Alpha, Explorer, and Yoda. The player chooses one cat hero and battles through multiple dog enemies in turn-based combat. The final goal is to defeat a large three-headed dog boss and save Professor Oakes.

The game uses object-oriented programming concepts such as inheritance, abstraction, enums, classes, methods, and game states. It also includes a simple enemy decision system, which connects to the course topics about game AI, finite state machines, and rule-based enemy behavior.

## Story

Professor Oakes has been captured by a giant three-headed dog boss. Alpha, Explorer, and Yoda must fight through waves of dog enemies to reach the final battle. Each enemy defeated makes the cat stronger by allowing the player to upgrade stats before the next fight.

The game continues until the player is defeated or the final boss is beaten.

## Main Characters

### Alpha

Alpha is a balanced cat hero. Alpha is a good choice for players who want equal attack, health, and defense.

### Explorer

Explorer is a quick cat hero. Explorer focuses more on dexterity, dodging, and landing attacks.

### Yoda

Yoda is a powerful and defensive cat hero. Yoda has stronger durability and can survive longer battles.

## Enemy Types

### Fighter Dog

The Fighter Dog is the balanced enemy type. It has average health, attack, defense, dexterity, and luck.

### Rogue Dog

The Rogue Dog is fast and harder to hit. This enemy specializes in evasion and quick attacks.

### Tank Dog

The Tank Dog has high health and defense. It takes longer to defeat and is meant to challenge the player.

### Three-Headed Dog Boss

The final boss is much larger and stronger than the regular enemies. It has more health, higher damage, and a stronger special attack. Defeating this boss completes the game and saves Professor Oakes.

## Player Actions

During combat, the player can choose from three actions:

### Attack

The player performs a normal attack. The game checks whether the attack hits based on dexterity. If the attack lands, damage is calculated using attack and defense stats.

### Block

The player blocks to reduce damage from the next incoming enemy attack.

### Special Move

The player uses a powerful special attack. The player's special move deals double damage and does not require a hit chance check. After being used, the special move must recharge for two turns before it can be used again.

## Enemy Actions

Enemies use similar actions:

- Attack
- Block
- Special Move

Each enemy type has its own special move.

### Fighter Dog Special

The Fighter Dog special works like the player's special. It deals double damage.

### Rogue Dog Special

The Rogue Dog special ignores block and deals full damage through the player's defense stance.

### Tank Dog Special

The Tank Dog special takes one turn to charge, then deals very high damage. It has a longer recharge time than the other enemy specials.

## Stats

Each character has the following stats:

### Max HP

The maximum health the character can have.

### Current HP

The current amount of health during battle.

### Attack

Controls how much damage the character deals.

### Defense

Reduces incoming damage.

### Dexterity

Controls the chance to hit attacks and avoid enemy attacks.

### Luck

Controls the chance to deal double damage with a critical hit.

## Level Up System

After defeating an enemy, the player moves to a level-up screen. The player chooses two stat upgrades before the next fight.

The player may choose the same stat more than once.

Upgrade options:

- HP: increases max HP by 3
- Attack: increases attack by 1
- Defense: increases defense by 1
- Dexterity: increases dexterity by 1
- Luck: increases luck by 1

After upgrades are selected, the player's health is restored and the next battle begins.

## Game Loop

The basic game loop is:

1. Choose a cat hero.
2. Fight an enemy.
3. Choose Attack, Block, or Special.
4. Defeat the enemy.
5. Choose two stat upgrades.
6. Restore health.
7. Fight a stronger enemy.
8. Repeat until the player loses or defeats the final boss.

## Game Over

If the player's HP reaches zero, the game ends. The game over screen displays:

- Number of enemies defeated
- Final max HP
- Final attack
- Final defense
- Final dexterity
- Final luck

## Win Condition

The player wins by defeating the final three-headed dog boss and saving Professor Oakes.

## Game AI

The enemies use a beginner-friendly rule-based system. This means the enemy chooses actions based on simple rules.

Example enemy logic:

- If health is low, block.
- If special move is ready, use special.
- Otherwise, use a normal attack.

This connects to the course topic of Rule-Based Systems, where AI uses conditional logic such as "if this happens, do that." It also connects to Finite State Machines because the game can move between states such as menu, combat, level up, game over, and victory.

## Game States

The game can be organized with a GameState enum:

- MENU
- CHARACTER_SELECT
- COMBAT
- LEVEL_UP
- GAME_OVER
- VICTORY

Using game states makes the project easier to organize because each screen has a clear purpose.

## Suggested Java Classes

### Character.java

The parent class for all fighters. Stores common stats such as HP, attack, defense, dexterity, and luck.

### Player.java

Extends Character. Handles player-specific actions, special move cooldown, and stat upgrades.

### Enemy.java

Extends Character. Handles enemy decision-making and shared enemy behavior.

### FighterDog.java

A balanced enemy class.

### RogueDog.java

A fast enemy class that specializes in dodging.

### TankDog.java

A defensive enemy class with high health.

### ThreeHeadedDogBoss.java

The final boss enemy.

### ActionType.java

An enum for combat actions:

- ATTACK
- BLOCK
- SPECIAL

### GameState.java

An enum for the current screen or phase of the game.

### TurnBasedCombatGame.java

The main game class. It starts the program and controls the game flow.

## Background Music

The game should include access for background music. A WAV file can be placed in the resources folder and played during gameplay. The background music should start when combat begins and stop or change when the game reaches the victory or game over screen.

## Course Concepts Used

This project includes:

- Java classes
- Inheritance
- Abstract methods
- Enums
- Constructors
- Getters and setters
- Turn-based logic
- Random enemy decisions
- Rule-based AI
- Finite state machines
- Game screens
- Sound files
- Object-oriented programming

## Future Improvements

Possible future improvements include:

- More enemy types
- Save/load system
- Boss animations
- A high-score screen
- More story scenes

## Author(s)

Alexander Matthew Hurtak, Martin Edwin Naugle, Arbi Xhepa, Avery Paige Timm

## Course

IST 242