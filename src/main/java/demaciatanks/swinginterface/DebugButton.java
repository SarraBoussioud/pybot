package demaciatanks.swinginterface;

import javax.swing.JButton;

/**
 * Created by Sarra Boussioud on 25/01/2017.
 */

/**
 * Button debug.
 */
public class DebugButton extends JButton {
    /**
     * Constructor.
     */
    public DebugButton() {
        setDebugText();
    }

    /**
     * Set text Mode debug.
     */
    public final void setDebugText() {
        this.setText("Mode Debug");
    }

}
