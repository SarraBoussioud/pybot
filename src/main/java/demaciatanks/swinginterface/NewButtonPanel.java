package demaciatanks.swinginterface;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Le panel contient le bouton Creer Nouveau et Supprimer.
 */
public class NewButtonPanel extends JPanel {
    /**
     * Serial.
     */
    private static final long serialVersionUID = -2862434094133768587L;

    /**
     * Bouton Creer Nouveau.
     */
    private NewFileButton newFileButton;

    /**
     * Bouton Supprimer.
     */
    private RemoveButton rmButton;

    /**
     * Espace entre les boutons.
     */
    private static final int SPACE = 10;

    /**
     * Constructor.
     */
    public NewButtonPanel() {
        newFileButton = new NewFileButton();
        rmButton = new RemoveButton();
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(Box.createHorizontalGlue());
        this.add(Box.createRigidArea(new Dimension(SPACE, 0)));
        this.add(newFileButton);
        this.add(Box.createRigidArea(new Dimension(SPACE, 0)));
        this.add(rmButton);
        this.add(Box.createRigidArea(new Dimension(SPACE, 0)));
    }

    /**
     * Getter bouton Creer.
     *
     * @return newFileButton
     */
    public final NewFileButton getNewFileButton() {
        return newFileButton;
    }

    /**
     * Getter bouton Supprimer.
     *
     * @return rmButton
     */
    public final RemoveButton getRmButton() {
        return rmButton;
    }

}
