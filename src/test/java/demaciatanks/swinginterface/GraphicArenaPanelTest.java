package demaciatanks.swinginterface;

import org.junit.Before;
import org.junit.Test;
import thinktank.javabot.graphics.GraphicArena;
import thinktank.javabot.physics.SmartCursor;

import static org.junit.Assert.*;

/**
 * Created by Sarra Boussioud on 27/04/2017.
 */
public class GraphicArenaPanelTest {
    GraphicArenaPanel graphicArenaPanel;
    @Before
    public void setUp() throws Exception {
        graphicArenaPanel = new GraphicArenaPanel(new MainPanel(), new SmartCursor());
    }

    @Test
    public void getGraphicArena() throws Exception {
        assertNotNull(graphicArenaPanel);
        assertTrue(graphicArenaPanel.getGraphicArena() instanceof GraphicArena);
    }

    @Test
    public void getGraphicArenaToolsPanel() throws Exception {
        assertTrue(graphicArenaPanel.getGraphicArenaToolsPanel() instanceof GraphicArenaToolsPanel);
    }

    @Test
    public void getGraphicArenaListTank() throws Exception {
        assertTrue(graphicArenaPanel.getGraphicArenaListTank() instanceof GraphicArenaListTank);
    }

}