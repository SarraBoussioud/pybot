package demaciatanks.swinginterface;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sarra Boussioud on 26/04/2017.
 */
public class DeleteAreneButtonTest {
    DeleteAreneButton deleteAreneButton = new DeleteAreneButton();
    @Test
    public void setDeleteAreneText() throws Exception {
        deleteAreneButton.setDeleteAreneText();
        assertTrue(deleteAreneButton.getText().equals("Delete/Modify Arene"));
    }

}