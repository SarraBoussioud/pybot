package demaciatanks.swinginterface;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by Sarra Boussioud on 26/04/2017.
 */
public class ColorTankFrameTest {
    ColorTankFrame colorTankFrame = new ColorTankFrame();
    @Test
    public void coloreTank() throws Exception {
        colorTankFrame.coloreTank();
        assertTrue(colorTankFrame.getPreferredSize().equals(new Dimension(500, 100)));
        assertTrue(colorTankFrame.getTitle().equals("Choisir une couleur"));
    }

}