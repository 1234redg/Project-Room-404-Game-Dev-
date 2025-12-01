package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class AudioPlayer {
    private static AudioPlayer instance;
    private Clip currentClip;

    private AudioPlayer() {
    }

    public static AudioPlayer getInstance() {
        if (instance == null) {
            instance = new AudioPlayer();
        }
        return instance;
    }

    public void playMusic(String filePath) {
        try {
            // Stop previous music if playing
            if (currentClip != null && currentClip.isRunning()) {
                currentClip.stop();
                currentClip.close();
            }

            InputStream audioStream = getClass().getResourceAsStream(filePath);
            if (audioStream == null) {
                System.err.println("Audio file not found: " + filePath);
                return;
            }

            BufferedInputStream bufferedStream = new BufferedInputStream(audioStream);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedStream);
            currentClip = AudioSystem.getClip();
            currentClip.open(audioInputStream);
            currentClip.loop(Clip.LOOP_CONTINUOUSLY);
            currentClip.start();
            System.out.println("Now playing: " + filePath);

        } catch (Exception e) {
            System.err.println("Error playing audio: " + filePath);
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
            currentClip.close();
        }
    }

    public void pauseMusic() {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
        }
    }

    public void resumeMusic() {
        if (currentClip != null && !currentClip.isRunning()) {
            currentClip.start();
        }
    }

    public void setVolume(float volume) {
        if (currentClip != null) {
            // Volume control implementation
        }
    }
}
