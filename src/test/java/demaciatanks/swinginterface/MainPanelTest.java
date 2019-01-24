package demaciatanks.swinginterface;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by SoF on 27/04/2017.
 */
public class MainPanelTest {

    MainPanel mainPanel;

    @Before
    public void setUp() throws Exception {
        mainPanel = new MainPanel();
    }

    @Test
    public void getContentPanel() throws Exception {
        assertTrue(mainPanel.getContentPanel() instanceof ContentPanel);
    }

    @Test
    public void getFilePanel() throws Exception {
        assertTrue(mainPanel.getFilePanel() instanceof FilePanel);
    }

}