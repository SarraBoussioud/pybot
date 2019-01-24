package demaciatanks.swinginterface;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sarra Boussioud on 26/04/2017.
 */
public class ImporteAreneButtonTest {
    ImporteAreneButton importeAreneButton = new ImporteAreneButton();
    @Test
    public void setImporteAreneText() throws Exception {
        importeAreneButton.setImporteAreneText();
        assertTrue(importeAreneButton.getText().equals("Importer"));
    }

}