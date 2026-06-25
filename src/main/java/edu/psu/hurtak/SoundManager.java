package edu.psu.hurtak;

import javax.sound.sampled.*;
import java.io.InputStream;

/**
 * Project: TurnBasedCombat242Final
 * Purpose Details: Turn-based cat combat game where Alpha, Explorer, or Yoda fight dog enemies and a three-headed dog boss.
 * Course: IST 242
 * Author: Alexander Matthew Hurtak
 * Date Developed: 06/23/2026
 * Last Date Changed: 06/25/2026
 * Rev: 3
 */

public class SoundManager {

    private Clip backgroundClip;

    public void playBackgroundMusic(String path) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(path);

            if (inputStream == null) {
                System.out.println("Background music file not found: " + path);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioStream);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println("Background music could not play. Add a WAV file to assets to use music.");
        }
    }

    public void playSound(String path) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(path);

            if (inputStream == null) {
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Sound effect could not play.");
        }
    }

    public void stopBackgroundMusic() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
        }
    }
}
