package thinktank.javabot.physics;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sarra Boussioud on 26/04/2017.
 */
public class TankTest {
    Tank tank;
    @Before
    public void setUp() throws Exception {
        tank = new Tank(new Terrain(100, 100));
    }

    @Test
    public void isTimerRunning() throws Exception {
        assertFalse(tank.isTimerRunning());
    }

    @Test
    public void setTimerRunning() throws Exception {
        tank.setTimerRunning(true);
        assertTrue(tank.isTimerRunning());
    }

    @Test
    public void setPointsDeVie() throws Exception {
        tank.setPointsDeVie(50);
        assertTrue(tank.getPointsDeVie() == 50);
    }

    @Test
    public void getNbVie() throws Exception {
        assertTrue(tank.getNbVie() == 5);
    }

    @Test
    public void setNbVie() throws Exception {
        tank.setNbVie(3);
        assertTrue(tank.getNbVie() == 3);
    }

    @Test
    public void diminuerNbVie() throws Exception {
        tank.diminuerNbVie();
        assertTrue(tank.getNbVie() == 4);
    }

    @Test
    public void getPointsDeVie() throws Exception {
        assertTrue(tank.getPointsDeVie() == 100);
    }

    @Test
    public void subit() throws Exception {
        tank.subit(20);
        assertTrue(tank.getPointsDeVie()==80);
    }

    @Test
    public void getType() throws Exception {
        assertTrue(tank.getType().equals(Physique.TypeP.tank));
    }

    @Test
    public void tuer() throws Exception {
        assertTrue(tank.getNbVie() == 5);
        tank.setNbVie(2);
        tank.setPointsDeVie(20);
        tank.subit(20);
        tank.tuer();
        assertTrue(tank.getNbVie() == 5 && tank.getPointsDeVie() == 0);
    }
}