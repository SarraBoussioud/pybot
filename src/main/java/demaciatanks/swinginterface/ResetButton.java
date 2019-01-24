package demaciatanks.swinginterface;

import javax.swing.JButton;

/**
 * Created by Dell Inspiron on 24/01/2017.
 */

/**
 * Bouton Reset.
 */
public class ResetButton extends JButton {
    /**
     * Constructor.
     */
    public ResetButton() {
        setResetText();
    }

    /**
     * Set text Reset.
     */
    public final void setResetText() {
        this.setText("Reset");
    }
}
