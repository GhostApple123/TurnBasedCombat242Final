package edu.psu.hurtak;

import javax.sound.sampled.*;
import java.io.InputStream;

/**
 * Project: TurnBasedCombat242Final
 * Purpose Details: Handles background music and sound effects for the turn-based cat combat game.
 * Course: IST 242
 * Author(s): Alexander Matthew Hurtak
 * Date Developed: 06/23/2026
 * Last Date Changed: 06/25/2026
 * Rev: 5
 */

public class SoundManager {

    /**
     * Stores the current looping background music.
     */
    private Clip backgroundClip;

    /**
     * Plays background music on a loop.
     * If another background song is already playing, it stops it first.
     *
     * @param path file path inside the resources/assets folder
     */
    public void playBackgroundMusic(String path) {
        try {
            stopBackgroundMusic();

            InputStream inputStream = getClass().getResourceAsStream(path);

            if (inputStream == null) {
                System.out.println("Background music file not found: " + path);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);

            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioStream);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundClip.start();

        } catch (Exception e) {
            System.out.println("Background music could not play. Add a WAV file to assets to use music.");
        }
    }

    /**
     * Plays a short sound effect one time.
     *
     * @param path file path inside the resources/assets folder
     */
    public void playSound(String path) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(path);

            if (inputStream == null) {
                System.out.println("Sound effect file not found: " + path);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (Exception e) {
            System.out.println("Sound effect could not play: " + path);
        }
    }

    /**
     * Stops the currently playing background music.
     */
    public void stopBackgroundMusic() {
        if (backgroundClip != null) {
            if (backgroundClip.isRunning()) {
                backgroundClip.stop();
            }

            backgroundClip.close();
            backgroundClip = null;
        }
    }

    /**
     * Changes the background music to a new WAV file.
     *
     * @param path new background music path
     */
    public void changeBackgroundMusic(String path) {
        stopBackgroundMusic();
        playBackgroundMusic(path);
    }
}
