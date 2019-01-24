package demaciatanks.swinginterface;

import javax.swing.JButton;

/**
 * Created by HOANG on 18-Jan-17.
 */

/**
 * Bouton Supprimer.
 */
public class RemoveButton extends JButton {
    /**
     * Constructor.
     */
    public RemoveButton() {
        setRemoveText();
    }

    /**
     * Set text Supprimer.
     */
    public final void setRemoveText() {
        this.setText("Supprimer");
    }
}
