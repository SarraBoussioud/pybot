package demaciatanks.swinginterface;

import thinktank.javabot.graphics.Sound;
import javax.swing.JButton;

/**
 * Bouton Start.
 */
public class StartButton extends JButton {
    /**
     * Serial.
     */
    private static final long serialVersionUID = -2934554054003374998L;
    /**
     * Boolean pour verifier etat de simulation.
     */
    private boolean underSimulation;

    /**
     * Constructor.
     */
    public StartButton() {
        setStartText();
    }

    /**
     * En cas de start.
     */
    public final void setStartText() {
        this.setText("Play");
        Sound.getInstance().stop();
        underSimulation = false;
    }

    /**
     * En cas de stop.
     */
    public final void setStopText() {
        this.setText("Pause");
        Sound.getInstance().play();
        underSimulation = true;
    }

    /**
     * Getter underSimulation.
     *
     * @return boolean etat de simulation
     */
    public final boolean isSimulationRunning() {
        return underSimulation;
    }
}
