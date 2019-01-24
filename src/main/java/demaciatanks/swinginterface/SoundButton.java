package demaciatanks.swinginterface;

import javax.swing.JButton;
import javax.swing.ImageIcon;

/**
 * Created by MONTASSER on 03/02/2017.
 */

/**
 * Bouton Sound.
 */
public class SoundButton extends JButton {
    /**
     * Constructor.
     */
    public SoundButton() {
        this.setSoundIcon();
    }

    /**
     * Set text Sauvegarder.
     */
    public final void setSoundIcon() {
        this.setIcon(new ImageIcon("ressources/images/soundOff.png"));
    }
}
