package demaciatanks.swinginterface;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Sarra Boussioud on 26/04/2017.
 */
public class BackgroundComboBoxTest {
    BackgroundComboBox backgroundComboBox = new BackgroundComboBox();
    @Test
    public void setComboBox() throws Exception {
        String test [] = new String []{"Sand", "Water", "Wood", "Grass", "Ocean", "Rocks"};
        backgroundComboBox.setComboBox();
        assertTrue(Arrays.equals(backgroundComboBox.getListe(), test));
    }

    @Test
    public void getListe() throws Exception {
        assertTrue(backgroundComboBox.getListe().length>0);
    }

    @Test
    public void setListe() throws Exception {
        String test [] = {"sarra", "boussioud"};
        backgroundComboBox.setListe(test);
        assertTrue(backgroundComboBox.getListe()[0].equals("sarra"));
    }

}