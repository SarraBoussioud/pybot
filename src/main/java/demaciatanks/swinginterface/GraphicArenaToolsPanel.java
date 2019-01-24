package demaciatanks.swinginterface;

import thinktank.javabot.graphics.GraphicArena;

import javax.swing.*;
import java.awt.*;

/**
 * Le panel contient les boutons pour modifier l'arene.
 */
public class GraphicArenaToolsPanel extends JPanel {
    /**
     * Serial.
     */
    private static final long serialVersionUID = -7552492479405461674L;

    /**
     * Bouton Lancer.
     */
    private StartButton button;

    /**
     * Bouton Reset.
     */
    private ResetButton reset;

    /**
     * Bouton de son.
     */
    private SoundButton soundButton;

    /**
     * Bouton debug.
     */
    private DebugButton debugButton;

    /**
     * Combobox pour choisir les backgrounds.
     */
    private BackgroundComboBox backgroundComboBox;

    /**
     * Liste des obstacles.
     */
    private DeleteObstacleButton deleteObstacleButton;

    /**
     * Bouton reset de obstacles.
     */
    private ResetObstaclesButton resetObstaclesButton;

    /**
     * Information du tank choisi.
     */
    private TankInformation information;

    /**
     * Espace entre des boutons.
     */
    private static final int SPACE = 5;

    /**
     * Longueur du combobox.
     */
    private static final int LONGUEUR = 4000;

    /**
     * Largeur du combobox.
     */
    private static final int LARGEUR = 50;

    /**
     * Bouton sauvegarder arene
     */
    private SaveAreneButton save;

    /**
     * Bouton supprimer arene
     */
    private DeleteAreneButton supprimer;

    /**
     * Constructor.
     *
     * @param arena arene
     */
    public GraphicArenaToolsPanel(final GraphicArena arena) {
        button = new StartButton();
        reset = new ResetButton();
        debugButton = new DebugButton();
        soundButton = new SoundButton();
        backgroundComboBox = new BackgroundComboBox();
        information = new TankInformation(arena);
        resetObstaclesButton = new ResetObstaclesButton();
        deleteObstacleButton = new DeleteObstacleButton();
        button.setEnabled(false);
        soundButton.setEnabled(false);
        soundButton.setMaximumSize(new Dimension(1,30));
        backgroundComboBox.setMaximumSize(new Dimension(LONGUEUR, LARGEUR));
        backgroundComboBox.setPreferredSize(new Dimension(150, 30));
        save = new SaveAreneButton();
        supprimer = new DeleteAreneButton();

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(Box.createRigidArea(new Dimension(SPACE, 0)));
        this.add(information);
        this.add(Box.createRigidArea(new Dimension(SPACE, 0)));
        this.add(Box.createHorizontalGlue());
        this.add(supprimer);
        this.add(Box.createRigidArea(new Dimension(SPACE, 0)));
        this.add(save);
        this.add(Box.createHorizontalGlue());
        this.add(Box.createRigidArea(new Dimension(SPACE, 0)));
        this.add(deleteObstacleButton);
        this.add(Box.createRigidArea(new Dimension(SPACE, 0)));
        this.add(resetObstaclesButton);
        this.add(Box.createRigidArea(new Dimension(SPACE, 0)));
        this.add(backgroundComboBox);
        this.add(Box.createRigidArea(new Dimension(SPACE, 0)));
        this.add(soundButton);
        this.add(Box.createRigidArea(new Dimension(SPACE, 0)));
        this.add(debugButton);
        this.add(Box.createRigidArea(new Dimension(SPACE, 0)));
        this.add(reset);
        this.add(Box.createRigidArea(new Dimension(SPACE, 0)));
        this.add(button);
        this.add(Box.createRigidArea(new Dimension(SPACE, 0)));
    }

    /**
     * Getter startButton.
     *
     * @return le bouton start
     */
    public final StartButton getStartButton() {
        return button;
    }

    /**
     * Getter resetButton.
     *
     * @return le bouton reset
     */
    public final ResetButton getResetButton() {
        return reset;
    }

    /**
     * Getter soundButton.
     *
     * @return le bouton sound
     */
    public final SoundButton getSoundButton() {
        return soundButton;
    }

    /**
     * Getter debugButton.
     *
     * @return le bouton debug
     */
    public final DebugButton getDebugButton() {
        return debugButton;
    }

    /**
     * Getter backgroundComboBox.
     *
     * @return le combobox des backgrounds
     */
    public final BackgroundComboBox getBackgroundComboBox() {
        return backgroundComboBox;
    }

    /**
     * Getter tankInformation.
     *
     * @return les infos des tanks choisis
     */
    public final TankInformation getInformation() {
        return information;
    }

    /**
     * Getter resetObstacleButton.
     * @return resetObstaclesButton
     */
    public final ResetObstaclesButton getResetObstaclesButton() {
        return resetObstaclesButton;
    }

    /**
     * Getter saveAreneButton.
     * @return saveAreneButton
     */
    public SaveAreneButton getSave() {
        return save;
    }

    /**
     * Getter deleteAreneButton.
     * @return deleteAreneButton
     */
    public DeleteAreneButton getSupprimer() {
        return supprimer;
    }

    /**
     * Getter deleteObstacleButton.
     * @return deleteObstacleButton
     */
    public DeleteObstacleButton getDeleteObstacleButton() {
        return deleteObstacleButton;
    }
}
