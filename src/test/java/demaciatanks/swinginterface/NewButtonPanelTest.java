package demaciatanks.swinginterface;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by SoF on 27/04/2017.
 */
public class NewButtonPanelTest {

    NewButtonPanel newButtonPanel = new NewButtonPanel();

    @Test
    public void getNewFileButton() throws Exception {
        assertTrue(newButtonPanel.getNewFileButton() instanceof NewFileButton);
    }

    @Test
    public void getRmButton() throws Exception {
        assertTrue(newButtonPanel.getRmButton() instanceof RemoveButton);
    }

}