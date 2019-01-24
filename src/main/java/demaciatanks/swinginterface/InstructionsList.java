package demaciatanks.swinginterface;

import demaciatanks.scripteditor.ScriptEditor;
import thinktank.javabot.fileManagement.FileExplorer;

import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by MONTASSER on 23/01/2017.
 */

/**
 * La liste des instructions.
 */
public class InstructionsList extends JList {

    /**
     * La liste des actions possible.
     */
    private String[] actions = {
            "Avancer",
            "Reculer",
            "Tourner a droite",
            "Tourner a gauche",
            "Tirer"
    };

    /**
     * Longueur des champs dans JList.
     */
    private static final int LONGUEUR = 250;

    /**
     * Largeur des champs dans JList.
     */
    private static final int LARGEUR = 30;

    /**
     * La description des instructions.
     */
    private final JLabel descriptionIns = new JLabel();

    /**
     * Constructor.
     *
     * @param editor editor
     */
    public InstructionsList(final ScriptEditor editor) {
        this.setListData(actions);
        this.setFixedCellWidth(LONGUEUR);
        this.setFixedCellHeight(LARGEUR);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionIns.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                //editor.openFile(editor.getFile());
                if (e.getClickCount() == 1) {
                    switch (getSelectedValue().toString()) {
                        case "Avancer ":
                            descriptionIns.setText(
                                    "Fait avancer le tank d'un pas");
                            break;
                        case "Reculer":
                            descriptionIns.setText(
                                    "Fait reculer le tank d'un pas");
                            break;
                        case "Tourner a droite":
                            descriptionIns.setText(
                                    "Fait tourner le tank a droite.");
                            break;
                        case "Tourner a gauche":
                            descriptionIns.setText(
                                    "Fait tourner le tank a gauche.");
                            break;
                        case "Tirer":
                            descriptionIns.setText(
                                    "Le tank fait un tir.");
                            break;
                        default:
                            break;
                    }
                } else if (e.getClickCount() == 2) {
                    editor.getContentTabbedPane().setSelectedIndex(1);
                    switch (getSelectedValue().toString()) {
                        case "Avancer":
                            FileExplorer.saveFile(editor.getFile().getPath(),
                                    editor.getText()
                                            + "\n \ttank.moveForward();");
                            editor.openFile(editor.getFile());
                            break;
                        case "Reculer":
                            FileExplorer.saveFile(editor.getFile().getPath(),
                                    editor.getText()
                                            + "\n \ttank.moveBackward();");
                            editor.openFile(editor.getFile());
                            break;
                        case "Tourner a droite":
                            FileExplorer.saveFile(editor.getFile().getPath(),
                                    editor.getText()
                                            + "\n \ttank.turnClockwise();");
                            editor.openFile(editor.getFile());
                            break;
                        case "Tourner a gauche":
                            FileExplorer.saveFile(editor.getFile().getPath(),
                                    editor.getText()
                                            + "\n \ttank."
                                            + "turnCounterClockwise();");
                            editor.openFile(editor.getFile());
                            break;
                        case "Tirer":
                            FileExplorer.saveFile(editor.getFile().getPath(),
                                    editor.getText()
                                            + "\n \ttank.shoot();");
                            editor.openFile(editor.getFile());
                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public final void mousePressed(final MouseEvent e) {

            }

            @Override
            public final void mouseReleased(final MouseEvent e) {

            }

            @Override
            public final void mouseEntered(final MouseEvent e) {

            }

            @Override
            public final void mouseExited(final MouseEvent e) {

            }
        });
    }

    /**
     * Getter de la description.
     *
     * @return descriptionIns
     */
    public final JLabel getDescriptionIns() {
        return descriptionIns;
    }

}
