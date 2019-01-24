package demaciatanks.swinginterface;

import demaciatanks.scripteditor.ScriptEditor;
import org.junit.Before;
import org.junit.Test;
import thinktank.javabot.physics.SmartCursor;

import static org.junit.Assert.*;

/**
 * Created by Sarra BOUSSIOUD on 27/04/2017.
 */
public class ScriptEditorPanelTest {
    ScriptEditorPanel scriptEditorPanel;
    @Before
    public void setUp() throws Exception {
        SmartCursor smartCursor = new SmartCursor();
        ScriptEditor scriptEditor = new ScriptEditor(smartCursor);
        scriptEditorPanel = new ScriptEditorPanel(scriptEditor,new ContentTabbedPane(new MainPanel(), smartCursor,
                scriptEditor));
    }

    @Test
    public void getScriptEditor() throws Exception {
        assertNotNull(scriptEditorPanel);
        assertTrue(scriptEditorPanel.getScriptEditor() instanceof ScriptEditor);
    }

}