package demaciatanks.swinginterface;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sarra Boussioud on 26/04/2017.
 */
public class CloseDebugButtonTest {
    CloseDebugButton closeDebugButton = new CloseDebugButton();
    @Test
    public void setCloseText() throws Exception {
        closeDebugButton.setCloseText();
        assertTrue(closeDebugButton.getText().equals("Close"));
    }

}