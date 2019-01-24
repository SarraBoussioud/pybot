package demaciatanks.swinginterface;

import javax.swing.JButton;

/**
 * Bouton Creer Nouveau.
 */
public class NewFileButton extends JButton {

    /**
     * Serial.
     */
    private static final long serialVersionUID = 7215067870653114732L;

    /**
     * Constructor.
     */
    public NewFileButton() {
        setNewFileText();
    }

    /**
     * Set text Creer nouveau.
     */
    public final void setNewFileText() {
        this.setText("Creer nouveau");
    }

}
