package demaciatanks.swinginterface;

import demaciatanks.scripteditor.ScriptEditor;
import org.junit.Before;
import org.junit.Test;
import thinktank.javabot.physics.SmartCursor;

import static org.junit.Assert.*;

/**
 * Created by Sarra Boussioud on 27/04/2017.
 */
public class ContentPanelTest {
    ContentPanel contentPanel;
    SmartCursor smartCursor;
    @Before
    public void setUp() throws Exception {
        smartCursor = new SmartCursor();
        contentPanel = new ContentPanel(new MainPanel(), smartCursor, new ScriptEditor(new SmartCursor()));
    }

    @Test
    public void getTabbedPane() throws Exception {
        assertNotNull(contentPanel);
        assertTrue(contentPanel.getTabbedPane() instanceof  ContentTabbedPane);
    }

}