package demaciatanks.swinginterface;

import javax.swing.JScrollPane;

import demaciatanks.scripteditor.ScriptEditor;
import thinktank.javabot.fileManagement.FileBoxManager;
import thinktank.javabot.graphics.ImageLoader;
import thinktank.javabot.physics.SmartCursor;

/**
 * Contient la liste des filebox.
 */
public class FileBoxesScrollPane extends JScrollPane {
    /**
     * Serial.
     */
    private static final long serialVersionUID = -5032055368274982092L;

    /**
     * Filebox Manager.
     */
    private FileBoxManager fileBoxManager;

    /**
     * Constructor.
     * @param img imageLoader
     * @param cursor smart cursor
     * @param editor editor
     * @param newButtonPanel new bouton
     */
    public FileBoxesScrollPane(final ImageLoader img,
                               final SmartCursor cursor,
                               final ScriptEditor editor,
                               final NewButtonPanel newButtonPanel) {
        fileBoxManager = new FileBoxManager(
                img, cursor, editor, newButtonPanel, this);
        getViewport().add(fileBoxManager);
    }

    /**
     * Getter FileBoxManager.
     * @return fileBoxManager
     */
    public final FileBoxManager getBoxManager() {
        return fileBoxManager;
    }
}
