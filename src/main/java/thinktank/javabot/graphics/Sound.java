package thinktank.javabot.graphics;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.IOException;

/**
 * Created by MONTASSER on 30/01/2017.
 */

/**
 * Le son de l'arene.
 */
public final class Sound {
    /**
     * Le son a lance.
     */
    private Clip clip;

    /**
     * Constructor.
     */
    private Sound() {
        File son = new File("ressources/son/Backsound2.wav");
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(son);
            clip = AudioSystem.getClip();
            clip.open(audio);
            FloatControl gainControl = (FloatControl) clip.
                    getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0f);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lancer le son.
     */
    public void play() {
        clip.loop(1000);
    }

    /**
     * Arreter le son.
     */
    public void stop() {
        clip.stop();
    }

    /**
     * Getter clip.
     *
     * @return clip
     */
    public Clip getClip() {
        return clip;
    }

    /**
     * Instance unique pré-initialisée.
     */
    private static Sound instance = new Sound();

    /**
     * Point d'accès pour l'instance unique du singleton.
     * @return sound
     */
    public static Sound getInstance() {
        return instance;
    }
}
