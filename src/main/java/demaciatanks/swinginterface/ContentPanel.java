package demaciatanks.swinginterface;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import thinktank.javabot.physics.SmartCursor;
import demaciatanks.scripteditor.ScriptEditor;

/**
 * Le tableau de contenu.
 */
public class ContentPanel extends JPanel {
    /**
     * Serial.
     */
    private static final long serialVersionUID = -3145738724425444248L;

    /**
     * ContentTabbedPane.
     */
    private ContentTabbedPane contentTabbedPane;

    /**
     * Constructor empty
     */
    public ContentPanel(){

    }

    /**
     * Constructor.
     *
     * @param panel  main panel
     * @param cursor smart cursor
     * @param editor script editor
     */
    public ContentPanel(final MainPanel panel,
                        final SmartCursor cursor,
                        final ScriptEditor editor) {
        contentTabbedPane = new ContentTabbedPane(panel, cursor, editor);
        this.setLayout(new BorderLayout());
        this.add(contentTabbedPane, BorderLayout.CENTER);
    }

    /**
     * Getter ContentTabbedPane.
     *
     * @return ContentTabbedPane
     */
    public final ContentTabbedPane getTabbedPane() {
        return contentTabbedPane;
    }

}
