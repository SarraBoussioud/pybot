package demaciatanks.swinginterface;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import demaciatanks.scripteditor.ScriptEditor;

/**
 * Le Panel de l'editeur de script.
 */
public class ScriptEditorPanel extends JPanel {
    /**
     * Serial.
     */
    private static final long serialVersionUID = 1357837127265488294L;
    /**
     * Editor.
     */
    private ScriptEditor editor;
    /**
     * Script editor tools panel.
     */
    private ScriptEditorToolsPanel scriptEditorToolsPanel;

    /**
     * Espace entre des boutons.
     */
    private static final int SPACE = 5;

    /**
     * Constructor.
     *
     * @param e editor
     * @param contentTabbedPane contentTabbedPane
     */
    public ScriptEditorPanel(
            final ScriptEditor e, final ContentTabbedPane contentTabbedPane) {
        this.editor = e;
        editor.setContentTabbedPane(contentTabbedPane);
        scriptEditorToolsPanel = new ScriptEditorToolsPanel(editor);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(editor);
        this.add(Box.createRigidArea(new Dimension(0, SPACE)));
        this.add(scriptEditorToolsPanel);
        this.add(Box.createRigidArea(new Dimension(0, SPACE)));
    }

    /**
     * Getter editeur de script.
     *
     * @return editor
     */
    public final ScriptEditor getScriptEditor() {
        return editor;
    }

}
