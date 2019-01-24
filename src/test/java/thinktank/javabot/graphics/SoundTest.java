package thinktank.javabot.graphics;

import org.junit.Before;
import org.junit.Test;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by SoF on 27/04/2017.
 */
public class SoundTest {

    Sound sound;
    Clip clip;

    @Before
    public void setUp() throws Exception {
        sound = Sound.getInstance();
        File son = new File("ressources/son/Backsound2.wav");
        AudioInputStream audio = AudioSystem.getAudioInputStream(son);
        clip = AudioSystem.getClip();
        clip.open(audio);
    }

    @Test
    public void play() throws Exception {
        assertEquals(clip.isActive(), false);
    }

    @Test
    public void stop() throws Exception {
        assertEquals(clip.isActive(), false);
    }

    @Test
    public void getClip() throws Exception {
        assertTrue(sound.getClip() instanceof Clip);
    }

    @Test
    public void getInstance() throws Exception {
        assertTrue(sound instanceof Sound);
    }

}