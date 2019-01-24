package thinktank.javabot.physics;

import org.junit.Before;
import org.junit.Test;
import thinktank.javabot.graphics.ImageLoader;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * Created by Sarra Boussioud on 26/04/2017.
 */
public class VideTest {
    Vide vide;
    @Before
    public void setUp() throws Exception {
        vide = new Vide(new ImageLoader());
    }

    @Test
    public void getType() throws Exception {
        assertTrue(vide.getType().equals(Physique.TypeP.vide));
    }

}