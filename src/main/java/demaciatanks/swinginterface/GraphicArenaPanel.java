package demaciatanks.swinginterface;

import thinktank.javabot.graphics.GraphicArena;
import thinktank.javabot.physics.SmartCursor;

import javax.swing.*;
import java.awt.*;

/**
 * Le panel contient l'arene.
 */
public class GraphicArenaPanel extends JPanel {
    /**
     * Serial.
     */
    private static final long serialVersionUID = -2388067953901947017L;

    /**
     * L'arene.
     */
    private GraphicArena arena;

    /**
     * La liste des tanks sur l'arene.
     */
    private GraphicArenaListTank tankList;

    /**
     * Le panel contient les boutons pour modifier l'arene.
     */
    private GraphicArenaToolsPanel graphicArenaToolsPanel;

    /**
     * Espace entre les boutons.
     */
    private static final int SPACE = 5;

    /**
     * Constructor.
     *
     * @param panel  main panel
     * @param cursor smart cursor
     */
    public GraphicArenaPanel(final MainPanel panel, final SmartCursor cursor) {
        arena = new GraphicArena(panel, cursor);
        tankList = new GraphicArenaListTank(arena);
        graphicArenaToolsPanel = new GraphicArenaToolsPanel(arena);
        arena.setStartButton(graphicArenaToolsPanel.getStartButton());
        arena.setResetButton(graphicArenaToolsPanel.getResetButton());
        arena.setSoundButton(graphicArenaToolsPanel.getSoundButton());
        arena.setDebugButton(graphicArenaToolsPanel.getDebugButton());
        arena.setBackground(graphicArenaToolsPanel.getBackgroundComboBox());
        arena.setSaveAreneButton(graphicArenaToolsPanel.getSave());
        arena.setSupprimerAreneButton(graphicArenaToolsPanel.getSupprimer());
        arena.setResetObstaclesButton(
                graphicArenaToolsPanel.getResetObstaclesButton());
        arena.setDeleteObsButton(graphicArenaToolsPanel.getDeleteObstacleButton());

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(tankList);
        this.add(Box.createRigidArea(new Dimension(0, SPACE)));
        this.add(arena);
        this.add(Box.createRigidArea(new Dimension(0, SPACE)));
        this.add(graphicArenaToolsPanel);
        this.add(Box.createRigidArea(new Dimension(0, SPACE)));
    }

    /**
     * Getter l'arene.
     *
     * @return arene
     */
    public final GraphicArena getGraphicArena() {
        return arena;
    }

    /**
     * Getter graphicArenaToolsPanel.
     *
     * @return graphicArenaToolsPanel
     */
    public final GraphicArenaToolsPanel getGraphicArenaToolsPanel() {
        return graphicArenaToolsPanel;
    }

    /**
     * Getter liste des tanks sur l'arene.
     *
     * @return liste des tanks
     */
    public final GraphicArenaListTank getGraphicArenaListTank() {
        return tankList;
    }
}
