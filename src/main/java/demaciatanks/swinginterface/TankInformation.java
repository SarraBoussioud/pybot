package demaciatanks.swinginterface;

import javax.swing.JLabel;
import thinktank.javabot.graphics.GraphicArena;
import thinktank.javabot.physics.DTank;

/**
 * Les informations du tank clique.
 */
public class TankInformation extends JLabel {
    /**
     * Serial.
     */
    private static final long serialVersionUID = -7002817635769721666L;

    /**
     * Constructor.
     * @param arena arene
     */
    public TankInformation(final GraphicArena arena) {
        stopSimulationText();
        arena.addTankSelectionListener(new TankInformationFeeder());
        arena.addSimulationListener(new SimulationLaunchListener());
    }

    /**
     * Listener quand on clique sur un tank dans l'arene.
     */
    public class TankInformationFeeder
            implements GraphicArena.TankSelectionListener {
        @Override
        public final void onSelection(final DTank tank) {
            printInformation(tank);
        }
    }

    /**
     * Listener quand on clique sur le bouton Start.
     */
    public class SimulationLaunchListener
            implements GraphicArena.SimulationListener {
        @Override
        public final void onStart() {
            setSimulationText();
        }

        @Override
        public final void onStop() {
            stopSimulationText();
        }
    }

    /**
     * Afficher les infos.
     * @param tank tank qu'on veut afficher ses infos
     */
    public final void printInformation(final DTank tank) {
        this.setText("Script du tank : " + tank.getScriptPath());
    }

    /**
     * Changer le label si le jeu est en cours.
     */
    public final void setSimulationText() {
        this.setText("Simulation en cours...");
    }

    /**
     * Changer le label si le jeu s'arrete.
     */
    public final void stopSimulationText() {
        this.setText("Double-cliquez sur un tank...");
    }

}
