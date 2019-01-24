package demaciatanks.swinginterface;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sarra Boussioud on 26/04/2017.
 */
public class NewFileButtonTest {
    NewFileButton newFileButton = new NewFileButton();
    @Test
    public void setNewFileText() throws Exception {
        newFileButton.setNewFileText();
        assertTrue(newFileButton.getText().equals("Creer nouveau"));
    }

}