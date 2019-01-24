package demaciatanks.swinginterface;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import demaciatanks.scripteditor.ScriptEditor;
import thinktank.javabot.graphics.ImageLoader;
import thinktank.javabot.physics.SmartCursor;

/**
 * Contient FileBoxesScrollListPanel
 * et les boutons Creer, Supprimer.
 */
public class FilePanel extends JPanel {
    /**
     * Serial.
     */
    private static final long serialVersionUID = 7745904866479643142L;

    /**
     * Bouton Creer nouveau.
     */
    private NewButtonPanel newButtonPanel;

    /**
     * Le grand panel qui contient les panels de la liste des filebox,
     * la liste des instructions et la liste obstacles.
     */
    private FileBoxesScrollListPanel fileBoxesScrollListPanel;

    /**
     * Espace entre les FileBox.
     */
    private static final int LARGEUR = 10;
    /**
     * Constructor.
     * @param img imageLoader
     * @param cursor smartCursor
     * @param editor editor
     */
    public FilePanel(final ImageLoader img,
                     final SmartCursor cursor,
                     final ScriptEditor editor) {
        newButtonPanel = new NewButtonPanel();
        fileBoxesScrollListPanel = new FileBoxesScrollListPanel(
                img, cursor, editor, newButtonPanel);
        this.setMaximumSize(this.getMaximumSize());
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0, LARGEUR)));
        this.add(newButtonPanel);
        this.add(Box.createRigidArea(new Dimension(0, LARGEUR)));
        this.add(fileBoxesScrollListPanel);
    }

    /**
     * Getter NewButtonPanel.
     * @return newButtonPanel
     */
    public final NewButtonPanel getButtonPanel() {
        return newButtonPanel;
    }

    /**
     * Getter FileBoxesScrollListPanel.
     * @return fileBoxesScrollListPanel
     */
    public final FileBoxesScrollListPanel getListPanel() {
        return fileBoxesScrollListPanel;
    }
}
