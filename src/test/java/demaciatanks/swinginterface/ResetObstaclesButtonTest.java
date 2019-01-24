package demaciatanks.swinginterface;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sarra Boussioud on 26/04/2017.
 */
public class ResetObstaclesButtonTest {
    ResetObstaclesButton resetObstaclesButton = new ResetObstaclesButton();
    @Test
    public void setResetObstaclesText() throws Exception {
        resetObstaclesButton.setResetObstaclesText();
        assertTrue(resetObstaclesButton.getText().equals("Reset obstacles"));
    }

}