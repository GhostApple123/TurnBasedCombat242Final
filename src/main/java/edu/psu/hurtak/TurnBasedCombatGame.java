package edu.psu.hurtak;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Project: TurnBasedCombat242Final
 * Purpose Details: Turn-based cat combat game where Alpha, Explorer, or Yoda fight dog enemies and a three-headed dog boss.
 * Course: IST 242
 * Author(s): Alexander Matthew Hurtak
 * Date Developed: 06/23/2026
 * Last Date Changed: 06/25/2026
 * Rev: 19
 */

public class TurnBasedCombatGame extends JFrame {

    private GameState gameState;
    private Player player;
    private Enemy enemy;
    private Random random;
    private SoundManager soundManager;

    private JTextArea gameText;
    private JLabel playerLabel;
    private JLabel enemyLabel;
    private JLabel statsLabel;

    private JButton attackButton;
    private JButton blockButton;
    private JButton specialButton;

    private JButton alphaButton;
    private JButton explorerButton;
    private JButton yodaButton;

    private JButton easyButton;
    private JButton normalButton;
    private JButton hardButton;

    private JButton replayButton;

    private int enemyLevel;
    private int upgradeChoicesLeft;

    private String difficultyName;
    private int difficultyBonus;

    private BattlePanel battlePanel;

    private BufferedImage alphaSprite;
    private BufferedImage explorerSprite;
    private BufferedImage yodaSprite;
    private BufferedImage playerSprite;

    private BufferedImage fighterDogSprite;
    private BufferedImage rogueDogSprite;
    private BufferedImage tankDogSprite;
    private BufferedImage bossSprite;

    private BufferedImage backgroundSprite;
    private BufferedImage backgroundTwoSprite;
    private BufferedImage backgroundThreeSprite;
    private BufferedImage bossBackgroundSprite;

    public TurnBasedCombatGame() {
        setTitle("TurnBasedCombat242Final");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        random = new Random();
        soundManager = new SoundManager();

        enemyLevel = 1;
        difficultyName = "Normal";
        difficultyBonus = 0;

        loadSprites();
        setupScreen();
        showDifficultySelect();

        soundManager.playBackgroundMusic("/assets/background.wav");
    }

    private void loadSprites() {
        alphaSprite = loadImage("/assets/alpha.png");
        explorerSprite = loadImage("/assets/explorer.png");
        yodaSprite = loadImage("/assets/yoda.png");

        fighterDogSprite = loadImage("/assets/fighterDog.png");
        rogueDogSprite = loadImage("/assets/rogueDog.png");
        tankDogSprite = loadImage("/assets/tankDog.png");
        bossSprite = loadImage("/assets/boss.png");

        backgroundSprite = loadImage("/assets/background.png");
        backgroundTwoSprite = loadImage("/assets/background2.png");
        backgroundThreeSprite = loadImage("/assets/background3.png");
        bossBackgroundSprite = loadImage("/assets/bossBackground.png");
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(getClass().getResource(path));
        } catch (Exception e) {
            System.out.println("Could not load sprite: " + path);
            return null;
        }
    }

    private void setupScreen() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(3, 1));
        playerLabel = new JLabel("Player: ");
        enemyLabel = new JLabel("Enemy: ");
        statsLabel = new JLabel("Stats: ");

        topPanel.add(playerLabel);
        topPanel.add(enemyLabel);
        topPanel.add(statsLabel);
        add(topPanel, BorderLayout.NORTH);

        battlePanel = new BattlePanel();
        add(battlePanel, BorderLayout.CENTER);

        gameText = new JTextArea();
        gameText.setEditable(false);
        gameText.setFont(new Font("Arial", Font.PLAIN, 14));
        gameText.setLineWrap(true);
        gameText.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(gameText);
        scrollPane.setPreferredSize(new Dimension(280, 500));
        add(scrollPane, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel();

        easyButton = new JButton("Easy");
        normalButton = new JButton("Normal");
        hardButton = new JButton("Hard");

        attackButton = new JButton("Attack");
        blockButton = new JButton("Block");
        specialButton = new JButton("Special");

        alphaButton = new JButton("Choose Alpha");
        explorerButton = new JButton("Choose Explorer");
        yodaButton = new JButton("Choose Yoda");

        replayButton = new JButton("Play Again");
        replayButton.setVisible(false);

        easyButton.addActionListener(e -> chooseDifficulty("Easy"));
        normalButton.addActionListener(e -> chooseDifficulty("Normal"));
        hardButton.addActionListener(e -> chooseDifficulty("Hard"));

        attackButton.addActionListener(e -> playerTurn(ActionType.ATTACK));
        blockButton.addActionListener(e -> playerTurn(ActionType.BLOCK));
        specialButton.addActionListener(e -> playerTurn(ActionType.SPECIAL));

        alphaButton.addActionListener(e -> choosePlayer("Alpha"));
        explorerButton.addActionListener(e -> choosePlayer("Explorer"));
        yodaButton.addActionListener(e -> choosePlayer("Yoda"));

        replayButton.addActionListener(e -> restartGame());

        buttonPanel.add(easyButton);
        buttonPanel.add(normalButton);
        buttonPanel.add(hardButton);

        buttonPanel.add(alphaButton);
        buttonPanel.add(explorerButton);
        buttonPanel.add(yodaButton);

        buttonPanel.add(attackButton);
        buttonPanel.add(blockButton);
        buttonPanel.add(specialButton);

        buttonPanel.add(replayButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void showDifficultySelect() {
        gameText.setText("");
        gameText.append("Welcome to TurnBasedCombat242Final!\n\n");
        gameText.append("Choose a difficulty before selecting your cat hero.\n\n");
        gameText.append("Easy: Dogs are weaker.\n");
        gameText.append("Normal: Balanced difficulty.\n");
        gameText.append("Hard: Dogs are stronger.\n\n");
        gameText.append("No enemy can defeat your cat in one hit from full health.\n");

        easyButton.setVisible(true);
        normalButton.setVisible(true);
        hardButton.setVisible(true);

        alphaButton.setVisible(false);
        explorerButton.setVisible(false);
        yodaButton.setVisible(false);

        attackButton.setVisible(false);
        blockButton.setVisible(false);
        specialButton.setVisible(false);
        replayButton.setVisible(false);

        playerLabel.setText("Player: Choose Difficulty");
        enemyLabel.setText("Enemy: None");
        statsLabel.setText("Stats: None yet");

        battlePanel.repaint();
    }

    private void chooseDifficulty(String choice) {
        difficultyName = choice;

        if (choice.equals("Easy")) {
            difficultyBonus = -1;
        } else if (choice.equals("Hard")) {
            difficultyBonus = 2;
        } else {
            difficultyBonus = 0;
        }

        showCharacterSelect();
    }

    private void showCharacterSelect() {
        gameState = GameState.CHARACTER_SELECT;

        gameText.setText("");
        gameText.append("Difficulty Selected: " + difficultyName + "\n\n");
        gameText.append("Professor Oakes has been captured by the Three-Headed Dog Boss!\n");
        gameText.append("Choose one cat hero to begin the rescue mission.\n\n");
        gameText.append("Alpha: Balanced cat hero.\n");
        gameText.append("Explorer: Fast cat hero with better dexterity.\n");
        gameText.append("Yoda: Defensive cat hero with more health.\n");

        easyButton.setVisible(false);
        normalButton.setVisible(false);
        hardButton.setVisible(false);

        alphaButton.setVisible(true);
        explorerButton.setVisible(true);
        yodaButton.setVisible(true);

        attackButton.setVisible(false);
        blockButton.setVisible(false);
        specialButton.setVisible(false);
        replayButton.setVisible(false);

        updateLabels();
        battlePanel.repaint();
    }

    private void choosePlayer(String choice) {
        if (choice.equals("Alpha")) {
            player = new Player("Alpha", 28, 7, 5, 6, 4);
            playerSprite = alphaSprite;
        } else if (choice.equals("Explorer")) {
            player = new Player("Explorer", 25, 6, 4, 9, 5);
            playerSprite = explorerSprite;
        } else {
            player = new Player("Yoda", 34, 6, 7, 4, 4);
            playerSprite = yodaSprite;
        }

        alphaButton.setVisible(false);
        explorerButton.setVisible(false);
        yodaButton.setVisible(false);
        replayButton.setVisible(false);

        attackButton.setVisible(true);
        blockButton.setVisible(true);
        specialButton.setVisible(true);

        startBattle();
    }

    private void startBattle() {
        gameState = GameState.COMBAT;

        if (player.getEnemiesDefeated() >= 6) {
            enemy = new ThreeHeadedDogBoss(enemyLevel + difficultyBonus, random);
            gameText.setText("FINAL BATTLE! The Three-Headed Dog Boss appears!\n");
        } else {
            enemy = createRandomEnemy();
            gameText.setText("A new enemy appears: " + enemy.getName() + "!\n");
        }

        updateLabels();
        battlePanel.repaint();
    }

    private Enemy createRandomEnemy() {
        int adjustedLevel = enemyLevel + difficultyBonus;

        if (adjustedLevel < 1) {
            adjustedLevel = 1;
        }

        int choice = random.nextInt(3);

        if (choice == 0) {
            return new FighterDog(adjustedLevel, random);
        } else if (choice == 1) {
            return new RogueDog(adjustedLevel, random);
        } else {
            return new TankDog(adjustedLevel, random);
        }
    }

    private void playerTurn(ActionType action) {
        if (gameState != GameState.COMBAT) {
            return;
        }

        String result = "";
        boolean enemyTurn = true;

        if (action == ActionType.ATTACK) {
            result = player.normalAttack(enemy);
            soundManager.playSound("/assets/attack.wav");
        } else if (action == ActionType.BLOCK) {
            player.block();
            result = player.getName() + " blocks and prepares for the next attack.";
            soundManager.playSound("/assets/block.wav");
        } else if (action == ActionType.SPECIAL && player.specialCooldown == 0) {
            result = player.special(enemy);
            soundManager.playSound("/assets/special.wav");
        } else {
            result = player.special(enemy);
            enemyTurn = false;
        }

        gameText.append("\n" + result + "\n");

        if (!enemy.isAlive()) {
            handleEnemyDefeated();
            return;
        }

        if (enemyTurn) {
            enemyTurn();
            player.lowerCooldown();
            enemy.lowerCooldown();
        }

        checkPlayerDefeated();
        updateLabels();
        battlePanel.repaint();
    }

    private void enemyTurn() {
        int playerHealthBeforeAttack = player.getCurrentHP();

        ActionType enemyAction = enemy.chooseAction(player);
        String result = "";

        if (enemyAction == ActionType.ATTACK) {
            result = enemy.normalAttack(player);
        } else if (enemyAction == ActionType.BLOCK) {
            enemy.block();
            result = enemy.getName() + " blocks.";
        } else {
            result = enemy.special(player);
        }

        stopOneShotFromFullHealth(playerHealthBeforeAttack);

        gameText.append(result + "\n");
    }

    private void stopOneShotFromFullHealth(int playerHealthBeforeAttack) {
        if (playerHealthBeforeAttack == player.getMaxHP() && player.getCurrentHP() <= 0) {
            player.takeDamage(-1);
            gameText.append("Your cat barely survives the massive hit with 1 HP!\n");
        }
    }

    private void handleEnemyDefeated() {
        gameText.append(enemy.getName() + " was defeated!\n");
        player.addDefeatedEnemy();
        enemyLevel++;

        if (enemy instanceof ThreeHeadedDogBoss) {
            showVictory();
        } else {
            showLevelUp();
        }

        battlePanel.repaint();
    }

    private void showLevelUp() {
        gameState = GameState.LEVEL_UP;
        upgradeChoicesLeft = 2;

        attackButton.setVisible(false);
        blockButton.setVisible(false);
        specialButton.setVisible(false);
        replayButton.setVisible(false);

        gameText.append("\nLevel Up! Choose 2 upgrades.\n");

        showUpgradeOptions();
    }

    private void showUpgradeOptions() {
        String[] options = {"HP + 3", "Attack + 1", "Defense + 1", "Dexterity + 1", "Luck + 1"};

        while (upgradeChoicesLeft > 0) {
            String choice = (String) JOptionPane.showInputDialog(
                    this,
                    "Choose upgrade " + (3 - upgradeChoicesLeft) + " of 2:",
                    "Level Up",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice == null) {
                choice = "HP + 3";
            }

            applyUpgrade(choice);
            upgradeChoicesLeft--;
        }

        player.restoreHealth();

        attackButton.setVisible(true);
        blockButton.setVisible(true);
        specialButton.setVisible(true);
        replayButton.setVisible(false);

        gameText.append("Health restored. Next battle begins!\n");
        startBattle();
    }

    private void applyUpgrade(String choice) {
        if (choice.equals("HP + 3")) {
            player.upgradeHP();
        } else if (choice.equals("Attack + 1")) {
            player.upgradeAttack();
        } else if (choice.equals("Defense + 1")) {
            player.upgradeDefense();
        } else if (choice.equals("Dexterity + 1")) {
            player.upgradeDexterity();
        } else if (choice.equals("Luck + 1")) {
            player.upgradeLuck();
        }

        gameText.append("Upgrade selected: " + choice + "\n");
    }

    private void checkPlayerDefeated() {
        if (!player.isAlive()) {
            gameState = GameState.GAME_OVER;

            attackButton.setVisible(false);
            blockButton.setVisible(false);
            specialButton.setVisible(false);
            replayButton.setVisible(true);

            gameText.append("\nGAME OVER!\n");
            gameText.append("Enemies defeated: " + player.getEnemiesDefeated() + "\n");
            gameText.append("Final HP: " + player.getMaxHP() + "\n");
            gameText.append("Final Attack: " + player.getAttack() + "\n");
            gameText.append("Final Defense: " + player.getDefense() + "\n");
            gameText.append("Final Dexterity: " + player.getDexterity() + "\n");
            gameText.append("Final Luck: " + player.getLuck() + "\n");

            soundManager.stopBackgroundMusic();
            soundManager.playSound("/assets/gameover.wav");

            battlePanel.repaint();
        }
    }

    private void showVictory() {
        gameState = GameState.VICTORY;

        attackButton.setVisible(false);
        blockButton.setVisible(false);
        specialButton.setVisible(false);
        replayButton.setVisible(true);

        gameText.append("\nVICTORY!\n");
        gameText.append(player.getName() + " defeated the Three-Headed Dog Boss and saved Professor Oakes!\n");
        gameText.append("Enemies defeated: " + player.getEnemiesDefeated() + "\n");

        soundManager.stopBackgroundMusic();
        soundManager.playSound("/assets/victory.wav");

        updateLabels();
        battlePanel.repaint();
    }

    private void restartGame() {
        player = null;
        enemy = null;
        playerSprite = null;

        enemyLevel = 1;
        upgradeChoicesLeft = 2;
        difficultyName = "Normal";
        difficultyBonus = 0;

        soundManager.stopBackgroundMusic();
        soundManager.playBackgroundMusic("/assets/background.wav");

        showDifficultySelect();
    }

    private void updateLabels() {
        if (player != null) {
            playerLabel.setText("Player: " + player.getName()
                    + " HP " + player.getCurrentHP() + "/" + player.getMaxHP()
                    + " Special Cooldown: " + player.getSpecialCooldown());

            statsLabel.setText("Stats: ATK " + player.getAttack()
                    + " DEF " + player.getDefense()
                    + " DEX " + player.getDexterity()
                    + " LUCK " + player.getLuck()
                    + " Defeated " + player.getEnemiesDefeated()
                    + " Difficulty: " + difficultyName);
        } else {
            playerLabel.setText("Player: Choose Difficulty and Cat Hero");
            statsLabel.setText("Stats: None yet");
        }

        if (enemy != null && gameState == GameState.COMBAT) {
            enemyLabel.setText("Enemy: " + enemy.getName()
                    + " HP " + enemy.getCurrentHP() + "/" + enemy.getMaxHP()
                    + " Special Cooldown: " + enemy.getSpecialCooldown());
        } else {
            enemyLabel.setText("Enemy: None");
        }
    }

    private BufferedImage getEnemySprite() {
        if (enemy instanceof FighterDog) {
            return fighterDogSprite;
        } else if (enemy instanceof RogueDog) {
            return rogueDogSprite;
        } else if (enemy instanceof TankDog) {
            return tankDogSprite;
        } else if (enemy instanceof ThreeHeadedDogBoss) {
            return bossSprite;
        }

        return null;
    }

    private BufferedImage getCurrentBackground() {
        if (enemy instanceof ThreeHeadedDogBoss && bossBackgroundSprite != null) {
            return bossBackgroundSprite;
        }

        if (player != null && player.getEnemiesDefeated() >= 4 && backgroundThreeSprite != null) {
            return backgroundThreeSprite;
        }

        if (player != null && player.getEnemiesDefeated() >= 2 && backgroundTwoSprite != null) {
            return backgroundTwoSprite;
        }

        return backgroundSprite;
    }

    private class BattlePanel extends JPanel {

        public BattlePanel() {
            setBackground(Color.BLACK);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            drawBackground(g);
            drawPlayer(g);
            drawEnemy(g);
            drawGameStateText(g);
        }

        private void drawBackground(Graphics g) {
            BufferedImage currentBackground = getCurrentBackground();

            if (currentBackground != null) {
                g.drawImage(currentBackground, 0, 0, getWidth(), getHeight(), null);
            } else {
                if (enemy instanceof ThreeHeadedDogBoss) {
                    g.setColor(new Color(60, 0, 70));
                } else if (player != null && player.getEnemiesDefeated() >= 4) {
                    g.setColor(new Color(20, 60, 70));
                } else if (player != null && player.getEnemiesDefeated() >= 2) {
                    g.setColor(new Color(50, 50, 30));
                } else {
                    g.setColor(new Color(30, 30, 40));
                }

                g.fillRect(0, 0, getWidth(), getHeight());

                g.setColor(Color.WHITE);
                g.drawString("Battle Arena", 20, 25);
            }
        }

        private void drawPlayer(Graphics g) {
            if (player == null) {
                return;
            }

            int x = 90;
            int y = 260;
            int size = 110;

            if (playerSprite != null) {
                g.drawImage(playerSprite, x, y, size, size, null);
            } else {
                g.setColor(Color.ORANGE);
                g.fillOval(x, y, size, size);
                g.setColor(Color.BLACK);
                g.drawString(player.getName(), x + 30, y + 60);
            }

            drawHealthBar(g, x, y - 25, player.getCurrentHP(), player.getMaxHP());

            g.setColor(Color.WHITE);
            g.drawString(player.getName(), x + 35, y + size + 20);
        }

        private void drawEnemy(Graphics g) {
            if (enemy == null || gameState == GameState.CHARACTER_SELECT) {
                return;
            }

            BufferedImage enemySprite = getEnemySprite();

            int x = 360;
            int y = 250;
            int size = 120;

            if (enemy instanceof ThreeHeadedDogBoss) {
                x = 320;
                y = 170;
                size = 220;
            }

            if (enemySprite != null) {
                g.drawImage(enemySprite, x, y, size, size, null);
            } else {
                g.setColor(Color.RED);
                g.fillOval(x, y, size, size);
                g.setColor(Color.WHITE);
                g.drawString(enemy.getName(), x + 15, y + size / 2);
            }

            drawHealthBar(g, x, y - 25, enemy.getCurrentHP(), enemy.getMaxHP());

            g.setColor(Color.WHITE);
            g.drawString(enemy.getName(), x + 20, y + size + 20);
        }

        private void drawHealthBar(Graphics g, int x, int y, int currentHP, int maxHP) {
            int barWidth = 120;
            int barHeight = 15;

            double percent = (double) currentHP / maxHP;

            if (percent < 0) {
                percent = 0;
            }

            g.setColor(Color.WHITE);
            g.drawRect(x, y, barWidth, barHeight);

            g.setColor(Color.GREEN);
            g.fillRect(x, y, (int) (barWidth * percent), barHeight);

            g.setColor(Color.WHITE);
            g.drawString(currentHP + "/" + maxHP, x + 40, y + 12);
        }

        private void drawGameStateText(Graphics g) {
            g.setFont(new Font("Arial", Font.BOLD, 24));

            if (player == null) {
                g.setColor(Color.CYAN);
                g.drawString("Choose Difficulty, Then Choose Your Cat", 80, 130);
            } else if (gameState == GameState.CHARACTER_SELECT) {
                g.setColor(Color.CYAN);
                g.drawString("Choose Your Cat Hero", 170, 130);
            } else if (gameState == GameState.GAME_OVER) {
                g.setColor(Color.RED);
                g.drawString("GAME OVER", 210, 130);
            } else if (gameState == GameState.VICTORY) {
                g.setColor(Color.GREEN);
                g.drawString("VICTORY! Professor Oakes is Saved!", 70, 130);
            } else if (enemy instanceof ThreeHeadedDogBoss) {
                g.setColor(Color.MAGENTA);
                g.drawString("FINAL BOSS", 210, 130);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TurnBasedCombatGame().setVisible(true);
            }
        });
    }
}