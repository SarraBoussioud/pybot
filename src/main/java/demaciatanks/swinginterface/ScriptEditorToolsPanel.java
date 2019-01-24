package demaciatanks.swinginterface;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import demaciatanks.scripteditor.ScriptEditor;

/**
 * Panel contient le script editor.
 */
public class ScriptEditorToolsPanel extends JPanel {
    /**
     * Serial.
     */
    private static final long serialVersionUID = 931506142278620479L;

    /**
     * Le bouton Save.
     */
    private SaveButton button;

    /**
     * Espace entre des boutons.
     */
    private static final int SPACE = 5;

    /**
     * Constructor.
     *
     * @param editor editor
     */
    public ScriptEditorToolsPanel(final ScriptEditor editor) {
        button = new SaveButton(editor);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(Box.createHorizontalGlue());
        this.add(button);
        this.add(Box.createRigidArea(new Dimension(SPACE, 0)));
    }
}

