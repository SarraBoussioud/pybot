package thinktank.javabot.physics;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by SoF on 27/04/2017.
 */
public class ArmeTest {

    Arme arme;
    int temp;

    @Before
    public void setUp() throws Exception {
        arme = new Arme();
        temp = arme.getTempsRestant();
    }

    @Test
    public void getTempsDecharge() throws Exception {
        assertTrue((Object)arme.getTempsDecharge() instanceof Integer);
    }

    @Test
    public void getTempsRestant() throws Exception {
        assertTrue((Object)arme.getTempsRestant() instanceof Integer);
    }

    @Test
    public void initTempsRestant() throws Exception {
        arme.initTempsRestant();
        assertTrue(arme.getTempsDecharge() == arme.getTempsRestant());
        assertFalse(arme.getTempsDecharge() != arme.getTempsRestant());
    }

    @Test
    public void reduireTempsRestant() throws Exception {
        arme.reduireTempsRestant();
        if(arme.getTempsRestant() > 0){
            assertFalse(arme.getTempsRestant()==temp);
        }
    }

}