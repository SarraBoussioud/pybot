package demaciatanks.swinginterface;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sarra Boussioud on 26/04/2017.
 */
public class ResetButtonTest {
    ResetButton resetButton = new ResetButton();
    @Test
    public void setResetText() throws Exception {
        resetButton.setResetText();
        assertTrue(resetButton.getText().equals("Reset"));
    }

}