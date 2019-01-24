package demaciatanks.scripteditor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import demaciatanks.swinginterface.ContentTabbedPane;
import thinktank.javabot.fileManagement.FileExplorer;
import thinktank.javabot.physics.SmartCursor;

/**
 * L'editeur de script.
 */
public class ScriptEditor extends RSyntaxTextArea {
    /**
     * Serial.
     */
    private static final long serialVersionUID = -3906130901413802324L;

    /**
     * File a editer.
     */
    private File fileEditing;
    /**
     * ContentTabbedPane.
     */
    private ContentTabbedPane contentTabbedPane;
    /**
     * SmartCursor.
     */
    private SmartCursor cursor;

    /**
     * Defaut constructeur.
     *
     * @param cs cursor a affecter
     */
    public ScriptEditor(final SmartCursor cs) {
        setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
        setCodeFoldingEnabled(true);
        this.cursor = cs;
        fileEditing = null;
        this.addMouseListener(new ScriptEditorMouseListener());
        this.addMouseMotionListener(new ScriptEditorMouseListener());
    }

    /**
     * Le listener de l'editeur de script.
     */
    public class ScriptEditorMouseListener
            implements MouseListener, MouseMotionListener {

        @Override
        public void mouseClicked(final MouseEvent arg0) {
        }

        @Override
        public final void mouseEntered(final MouseEvent arg0) {
            if (!cursor.isActive()) {
                return;
            }
            openFile(cursor.getTank().getScriptPath());
            cursor.desactivate();
        }

        @Override
        public void mouseExited(final MouseEvent arg0) {
        }

        @Override
        public void mousePressed(final MouseEvent arg0) {
        }

        @Override
        public void mouseReleased(final MouseEvent arg0) {
        }

        @Override
        public void mouseDragged(final MouseEvent arg0) {
        }

        @Override
        public void mouseMoved(final MouseEvent arg0) {
        }
    }

    /**
     * Ouvrir un fichier a partir son chemin.
     *
     * @param path chemin du fichier
     */
    public final void openFile(final String path) {
        openFile(new File(path));
    }

    /**
     * Ouvrir un fichier specifique.
     *
     * @param file le fichier a ouvrir
     */
    public final void openFile(final File file) {
        fileEditing = file;
        String content = FileExplorer.readFile(fileEditing.getPath());
        setText(content);
        validate();
        repaint();
    }

    /**
     * Getter file.
     *
     * @return file a editer
     */
    public final File getFile() {
        return fileEditing;
    }

    /**
     * Setter contentTabbedPane.
     *
     * @param pane le contentTabbedPane a affecter
     */
    public final void setContentTabbedPane(final ContentTabbedPane pane) {
        contentTabbedPane = pane;
    }

    /**
     * Getter contentTabbedPane.
     *
     * @return contentTabbedPane lie au editeur
     */

    public final ContentTabbedPane getContentTabbedPane() {
        return contentTabbedPane;
    }
}
