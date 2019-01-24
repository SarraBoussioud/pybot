package thinktank.javabot.physics;

import demaciatanks.swinginterface.MainPanel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import thinktank.javabot.graphics.ImageLoader;

import static org.junit.Assert.*;

/**
 * Created by Sarra Boussioud on 27/04/2017.
 */
public class MurTest {
    Mur mur;
    @Before
    public void setUp() throws Exception {
        mur = new Mur(new ImageLoader());
    }

    @Test
    public void getMur() throws Exception {
        assertTrue(mur.getMur() instanceof Mur);
    }

    @Test
    public void getType() throws Exception {
        assertTrue(mur.getType().equals(Physique.TypeP.mur));
    }

}