package thinktank.javabot.intelligences;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by SoF on 27/04/2017.
 */
public class IntelligencesTest {

    Intelligences intelligences = new Intelligences();
    int a = intelligences.getRunningIntelligences();

    @Test
    public void addRunningIntelligence() throws Exception {
        assertTrue(intelligences.getRunningIntelligences()==a);
        intelligences.addRunningIntelligence();
        assertFalse(intelligences.getRunningIntelligences()==a);
        assertTrue(intelligences.getRunningIntelligences()==a+1);
    }

    @Test
    public void removeRunningIntelligence() throws Exception {
        assertTrue(intelligences.getRunningIntelligences()==a);
        intelligences.removeRunningIntelligence();
        assertFalse(intelligences.getRunningIntelligences()==a);
        assertTrue(intelligences.getRunningIntelligences()==a-1);
    }

    @Test
    public void runningIntelligences() throws Exception {
        int x = intelligences.runningIntelligences();
        assertTrue((Object)x instanceof Integer);
    }

    @Test
    public void newIntelligence() throws Exception {
        assertTrue(intelligences.newIntelligence("c:/") instanceof Intelligence);
    }

    @Test
    public void getRunningIntelligences() throws Exception {
        assertTrue((Object)intelligences.getRunningIntelligences() instanceof Integer);
    }

    @Test
    public void getIntelligences() throws Exception {
        assertTrue(intelligences.getIntelligences() instanceof ArrayList);
    }

}