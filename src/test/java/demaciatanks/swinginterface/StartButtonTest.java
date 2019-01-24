package demaciatanks.swinginterface;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sarra Boussioud on 26/04/2017.
 */
public class StartButtonTest {
    StartButton startButton = new StartButton();
    @Test
    public void setStartText() throws Exception {
        startButton.setStartText();
        assertTrue(startButton.getText().equals("Play"));
    }

    @Test
    public void setStopText() throws Exception {
        startButton.setStopText();
        assertTrue(startButton.getText().equals("Pause"));
    }

    @Test
    public void isSimulationRunning() throws Exception {
        assertFalse(startButton.isSimulationRunning());
    }

}