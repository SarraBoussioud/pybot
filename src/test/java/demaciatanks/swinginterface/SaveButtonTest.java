package demaciatanks.swinginterface;

import demaciatanks.scripteditor.ScriptEditor;
import org.junit.Test;
import thinktank.javabot.physics.SmartCursor;

import static org.junit.Assert.*;

/**
 * Created by Sarra Boussioud on 26/04/2017.
 */
public class SaveButtonTest {

    SaveButton saveButton = new SaveButton(new ScriptEditor(new SmartCursor()));
    @Test
    public void setSaveText() throws Exception {
        saveButton.setSaveText();
        assertTrue(saveButton.getText().equals("Sauvegarder"));
    }

}