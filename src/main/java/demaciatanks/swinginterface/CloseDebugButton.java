package demaciatanks.swinginterface;

import javax.swing.JButton;

/**
 * Created by Sarra Boussioud on 26/01/2017.
 */

/**
 * Button pour fermer la fenetre Debug.
 */
public class CloseDebugButton extends JButton {
    /**
     * Constructor.
     */
    public CloseDebugButton() {
        setCloseText();
    }

    /**
     * Set text close.
     */
    public final void setCloseText() {
        this.setText("Close");
    }
}
