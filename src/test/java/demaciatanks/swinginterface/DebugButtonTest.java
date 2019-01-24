package demaciatanks.swinginterface;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sarra Boussioud on 26/04/2017.
 */
public class DebugButtonTest {
    DebugButton debugButton = new DebugButton();
    @Test
    public void setDebugText() throws Exception {
        debugButton.setDebugText();
        assertTrue(debugButton.getText().equals("Mode Debug"));
    }

}