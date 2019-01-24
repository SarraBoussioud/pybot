package demaciatanks.swinginterface;

import javax.swing.JButton;

/**
 * Created by SoF on 17/03/2017.
 */

/**
 * Bouton reset des obstacles.
 */
public class ResetObstaclesButton extends JButton {
    /**
     * Constructor.
     */
    public ResetObstaclesButton() {
        setResetObstaclesText();
    }

    /**
     * Set text Reset obstacles.
     */
    public final void setResetObstaclesText() {
        this.setText("Reset obstacles");
    }
}
