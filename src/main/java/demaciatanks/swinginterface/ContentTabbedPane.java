package demaciatanks.swinginterface;

import javax.swing.JTabbedPane;

import thinktank.javabot.physics.SmartCursor;
import demaciatanks.scripteditor.ScriptEditor;

/**
 * Le panel qui contient 2 onglets Arene et Editeur.
 */
public class ContentTabbedPane extends JTabbedPane {
    /**
     * Serial.
     */
    private static final long serialVersionUID = 6180643756314548263L;

    /**
     * Le panel qui contient l'onglet Arene.
     */
    private GraphicArenaPanel graphicArenaPanel;
    /**
     * Le panel qui contient l'onglet editeur de script.
     */
    private ScriptEditorPanel scriptEditorPanel;

    /**
     * Constructor.
     *
     * @param panel  main panel
     * @param cursor smart cursor
     * @param editor script editor
     */
    public ContentTabbedPane(final MainPanel panel,
                             final SmartCursor cursor,
                             final ScriptEditor editor) {
        graphicArenaPanel = new GraphicArenaPanel(panel, cursor);
        scriptEditorPanel = new ScriptEditorPanel(editor, this);
        this.addTab("Arene", graphicArenaPanel);
        this.addTab("Editeur", scriptEditorPanel);
    }

    /**
     * Getter graphicArenaPanel.
     *
     * @return l'onglet Arene
     */
    public final GraphicArenaPanel getGraphicArenaPanel() {
        return graphicArenaPanel;
    }

    /**
     * Getter scriptEditorPanel.
     *
     * @return l'onglet Editeur
     */
    public final ScriptEditorPanel getScriptEditorPanel() {
        return scriptEditorPanel;
    }
}
