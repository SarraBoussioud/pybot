package demaciatanks.swinginterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import thinktank.javabot.fileManagement.FileExplorer;
import demaciatanks.scripteditor.ScriptEditor;

/**
 * Bouton Sauvegarder.
 */
public class SaveButton extends JButton {
    /**
     * Serial.
     */
    private static final long serialVersionUID = -6935669375135143783L;

    /**
     * Editor.
     */
    private ScriptEditor editor;

    /**
     * Listener.
     */
    public class SaveButtonListener implements ActionListener {
        @Override
        public final void actionPerformed(final ActionEvent arg0) {
            FileExplorer.saveFile(
                    editor.getFile().getPath(), editor.getText());
        }
    }

    /**
     * Constructor.
     *
     * @param e editor
     */
    public SaveButton(final ScriptEditor e) {
        this.editor = e;
        this.setSaveText();
        this.addActionListener(new SaveButtonListener());
    }

    /**
     * Set text Sauvegarder.
     */
    public final void setSaveText() {
        this.setText("Sauvegarder");
    }
}

