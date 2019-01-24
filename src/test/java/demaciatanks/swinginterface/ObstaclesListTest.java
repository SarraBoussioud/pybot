package demaciatanks.swinginterface;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by SoF on 27/04/2017.
 */
public class ObstaclesListTest {

    ObstaclesList obstaclesList;
    DefaultListModel model;

    @Before
    public void setUp() throws Exception {
        obstaclesList  = new ObstaclesList();
    }

    @Test
    public void getList() throws Exception {
    }

    @Test
    public void getObs() throws Exception {
        assertTrue(obstaclesList.getObs() instanceof String[]);
        assertTrue(obstaclesList.getObs().length==3);
    }

}