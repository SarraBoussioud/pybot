package demaciatanks.swinginterface;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sarra Boussioud on 26/04/2017.
 */
public class SaveAreneButtonTest {
    SaveAreneButton saveAreneButton = new SaveAreneButton();
    @Test
    public void setSaveText() throws Exception {
        saveAreneButton.setSaveText();
        assertTrue(saveAreneButton.getText().equals("Save Arene"));
    }

}