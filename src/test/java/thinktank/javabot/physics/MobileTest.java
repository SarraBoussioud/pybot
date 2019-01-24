package thinktank.javabot.physics;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by SoF on 27/04/2017.
 */
public class MobileTest {

    Mobile mobile;

    @Before
    public void setUp() throws Exception {
        mobile = new Mobile() {
            @Override
            protected void tuer() {

            }

            @Override
            public Physique.TypeP getType() {
                return null;
            }

            @Override
            public void paint(Graphics g, int x, int y) {

            }
        };
    }

    @Test
    public void getMort() throws Exception {
        assertTrue((Object)mobile.getMort() instanceof Boolean);
    }

    @Test
    public void vivre() throws Exception {
        mobile.vivre();
        assertTrue(mobile.getMort() == false);
    }

    @Test
    public void meurt() throws Exception {
        mobile.meurt();
        assertTrue(mobile.getMort() == true);
    }

    @Test
    public void getLatence() throws Exception {
        mobile.setLatence(10);
        assertTrue(mobile.getLatence() == 10);
    }

    @Test
    public void setLatence() throws Exception {
        mobile.setLatence(10);
        assertTrue(mobile.getLatence() == 10);
    }

    @Test
    public void newId() throws Exception {
        int a= mobile.getIdMob();
        mobile.newId();
        assertTrue(mobile.getIdMob() != a);
    }

    @Test
    public void getId() throws Exception {
        mobile.setId(11);
        assertTrue(mobile.getId() == 11);
    }

    @Test
    public void setId() throws Exception {
        mobile.setId(11);
        assertTrue(mobile.getId() == 11);
    }

    @Test
    public void getCoordX() throws Exception {
        mobile.setCoordX(11);
        assertTrue(mobile.getCoordX() == 11);
    }

    @Test
    public void setCoordX() throws Exception {
        mobile.setCoordX(11);
        assertTrue(mobile.getCoordX() == 11);
    }

    @Test
    public void getCoordY() throws Exception {
        mobile.setCoordY(11);
        assertTrue(mobile.getCoordY() == 11);
    }

    @Test
    public void setCoordY() throws Exception {
        mobile.setCoordY(11);
        assertTrue(mobile.getCoordY() == 11);
    }

    @Test
    public void getDirection() throws Exception {
        Direction d = new Direction(12,-2);
        mobile.setDirection(d);
        assertTrue(mobile.getDirection() == d);
    }

    @Test
    public void setDirection() throws Exception {
        Direction d = new Direction(12,-2);
        mobile.setDirection(d);
        assertTrue(mobile.getDirection() == d);
    }

    @Test
    public void getMap() throws Exception {
        Terrain t = new Terrain(15,15);
        mobile.setMap(t);
        assertTrue(mobile.getMap() == t);
    }

    @Test
    public void setMap() throws Exception {
        Terrain t = new Terrain(15,15);
        mobile.setMap(t);
        assertTrue(mobile.getMap() == t);
    }

}