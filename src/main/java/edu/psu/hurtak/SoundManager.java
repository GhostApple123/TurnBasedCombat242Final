package edu.psu.hurtak;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * Project: TurnBasedCombat242Final
 * Purpose Details: Handles background music and sound effects.
 * Course: IST 242
 * Author(s): Alexander Matthew Hurtak, Martin Edwin Naugle, Arbi Xepha, Avery Paige Timm
 * Rev: 6
 */

public class SoundManager {

    private Clip backgroundClip;

    /**
     * Plays looping background music.
     */
    public void playBackgroundMusic(String path) {

        try {

            stopBackgroundMusic();

            InputStream stream = getClass().getResourceAsStream(path);

            if (stream == null) {
                System.out.println("Music file not found: " + path);
                return;
            }

            BufferedInputStream buffered = new BufferedInputStream(stream);

            AudioInputStream audio =
                    AudioSystem.getAudioInputStream(buffered);

            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audio);

            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundClip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays one sound effect.
     */
    public void playSound(String path) {

        new Thread(() -> {

            try {

                InputStream stream = getClass().getResourceAsStream(path);

                if (stream == null) {
                    System.out.println("Sound file not found: " + path);
                    return;
                }

                BufferedInputStream buffered =
                        new BufferedInputStream(stream);

                AudioInputStream audio =
                        AudioSystem.getAudioInputStream(buffered);

                Clip clip = AudioSystem.getClip();

                clip.open(audio);

                clip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                });

                clip.start();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
    }

    /**
     * Stops background music.
     */
    public void stopBackgroundMusic() {

        if (backgroundClip != null) {

            backgroundClip.stop();
            backgroundClip.close();

            backgroundClip = null;
        }
    }

    /**
     * Switches to another music track.
     */
    public void changeBackgroundMusic(String path) {

        stopBackgroundMusic();
        playBackgroundMusic(path);
    }
}