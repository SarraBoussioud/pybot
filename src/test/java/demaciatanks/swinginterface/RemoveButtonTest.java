package demaciatanks.swinginterface;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sarra Boussioud on 26/04/2017.
 */
public class RemoveButtonTest {
    RemoveButton removeButton = new RemoveButton();
    @Test
    public void setRemoveText() throws Exception {
        removeButton.setRemoveText();
        assertTrue(removeButton.getText().equals("Supprimer"));
    }

}