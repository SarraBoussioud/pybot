package demaciatanks.swinginterface;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import demaciatanks.scripteditor.ScriptEditor;
import thinktank.javabot.graphics.ImageLoader;
import thinktank.javabot.physics.SmartCursor;

/**
 * Panel principal.
 */
public class MainPanel extends JPanel {
    /**
     * Serial.
     */
    private static final long serialVersionUID = 5131738883703122146L;

    /**
     * File panel.
     */
    private FilePanel filePanel;

    /**
     * Content Panel.
     */
    private ContentPanel contentPanel;

    /**
     * Image Loader.
     */
    private ImageLoader imageLoader;

    /**
     * Smart Cursor.
     */
    private SmartCursor cursor;

    /**
     * Script Editor.
     */
    private ScriptEditor editor;

    /**
     * Constructor.
     */
    public MainPanel() {
        imageLoader = new ImageLoader();
        cursor = new SmartCursor();
        editor = new ScriptEditor(cursor);
        filePanel = new FilePanel(imageLoader, cursor, editor);
        contentPanel = new ContentPanel(this, cursor, editor);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(filePanel);
        this.add(contentPanel);
    }

    /**
     * Getter Content Panel.
     *
     * @return contentPanel
     */
    public final ContentPanel getContentPanel() {
        return contentPanel;
    }

    /**
     * Getter File Panel.
     *
     * @return filePanel
     */
    public final FilePanel getFilePanel() {
        return filePanel;
    }

}

