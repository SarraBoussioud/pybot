package thinktank.javabot.fileManagement;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JButton;

import demaciatanks.scripteditor.ScriptEditor;
import thinktank.javabot.graphics.ImageLoader;
import thinktank.javabot.physics.DTank;
import thinktank.javabot.physics.SmartCursor;

/**
 * Les fichiers de script.
 */
public class FileBox extends JButton {
    /**
     * Serial.
     */
    private static final long serialVersionUID = -971009625896928830L;

    /**
     * Fichier.
     */
    private File file;

    /**
     * Image loader.
     */
    private ImageLoader imageLoader;

    /**
     * Smart smartCursor.
     */
    private SmartCursor smartCursor;

    /**
     * Script editor.
     */
    private ScriptEditor editor;

    /**
     * Les DTank.
     */
    private DTank tank;

    /**
     * Constructor.
     *
     * @param f   file
     * @param img    imageLoder
     * @param cursor smartcursor
     * @param e editor
     * @param c      color
     */
    public FileBox(final File f,
                   final ImageLoader img,
                   final SmartCursor cursor,
                   final ScriptEditor e,
                   final Color c) {
        this.setFile(f);
        this.setText(f.getName());
        this.imageLoader = img;
        this.smartCursor = cursor;
        this.editor = e;
        this.addMouseListener(new FileBoxMouseListener());
        tank = new DTank(null, f.getPath(), c, f.getPath(), img);
    }

    /**
     * Constructor.
     */
    public FileBox() {

    }

    /**
     * Mouse listener quand on clique sur les filebox.
     */
    public class FileBoxMouseListener implements MouseListener {
        /**
         * Boolean verifie si on est sur script editor.
         */
        private boolean inFrame;

        @Override
        public final void mouseClicked(final MouseEvent e) {
        }

        @Override
        public final void mouseEntered(final MouseEvent e) {
            inFrame = true;
        }

        @Override
        public final void mouseExited(final MouseEvent e) {
            inFrame = false;
        }

        @Override
        public final void mousePressed(final MouseEvent e) {
            smartCursor.setTank(tank);
        }

        @Override
        public final void mouseReleased(final MouseEvent e) {
            if (inFrame) {
                editor.getContentTabbedPane().setSelectedIndex(1);
                editor.openFile(file);
            }
            smartCursor.desactivate();
        }
    }

    /**
     * Getter file.
     *
     * @return file
     */
    public final File getFile() {
        return file;
    }

    /**
     * Setter file.
     *
     * @param f file
     */
    public final void setFile(final File f) {
        this.file = f;
    }

    /**
     * Getter tank.
     *
     * @return tank
     */
    public final DTank getTank() {
        return tank;
    }

}
