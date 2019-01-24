package thinktank.javabot.physics;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by SoF on 27/04/2017.
 */
public class DirectionTest {

    Direction direction;
    int x,y;

    @Before
    public void setUp() throws Exception {
        direction = new Direction(0,1);
        x = direction.getDx();
        y = direction.getDy();
    }

    @Test
    public void getDx() throws Exception {
        assertTrue((Object)direction.getDx() instanceof Integer);
    }

    @Test
    public void getDy() throws Exception {
        assertTrue((Object)direction.getDy() instanceof Integer);
    }

    @Test
    public void tournerDroite() throws Exception {
        direction.tournerDroite();
        assertFalse(direction.getDx() == x);
        assertFalse(direction.getDy() == y);
        assertTrue(direction.getDx() == x*0 + y * -1);
        assertTrue(direction.getDy() == x * 1 + y * 0);
    }

    @Test
    public void tournerGauche() throws Exception {
        direction.tournerGauche();
        assertFalse(direction.getDx() == x);
        assertFalse(direction.getDy() == y);
        assertTrue(direction.getDx() == x*0 + y * 1);
        assertTrue(direction.getDy() == x * -1 + y * 0);
    }

}