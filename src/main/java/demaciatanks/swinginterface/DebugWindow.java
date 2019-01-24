package demaciatanks.swinginterface;

import demaciatanks.scripteditor.ScriptEditor;
import thinktank.javabot.fileManagement.FileExplorer;
import thinktank.javabot.graphics.GraphicArena;
import thinktank.javabot.physics.SmartCursor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Sarra Boussioud on 25/01/2017.
 */

/**
 * Fenetre debug.
 */
public class DebugWindow extends JFrame {
    /**
     * Smart cursor.
     */
    private SmartCursor cursor;
    /**
     * La fenetre principale.
     */
    private JFrame debug;
    /**
     * panel principal de la fenetre.
     */
    private JPanel mainPanel;
    /**
     * Panel qui contient l'anène.
     */
    private JPanel panelArene;
    /**
     * Panel qui contient le ScriptEditor.
     */
    private JPanel panelEditor;
    /**
     * MainPanel pour recupérer le mainPanel
     * de l'interface principale sans le perdre.
     */
    private MainPanel mainPanelCopie;
    /**
     * ScriptEditorPanel pour avoir accès au ScriptEditor.
     */
    private ScriptEditorPanel scriptEditorPanel;
    /**
     * Bouton close debug.
     */
    private CloseDebugButton closeDebugButton;
    /**
     * Panel qui contient la liste des instruction.
     */
    private JPanel instructionPanel;
    /**
     * la liste des instructions.
     */
    private InstructionsList liste;
    /**
     * ScplitePane pour séparer le panel droit.
     */
    private JSplitPane splitPane;
    /**
     * Script editor.
     */
    private ScriptEditor editor;
    /**
     * Copie de Script editor pour ne pas perdre
     * celui sur la fenetre principale.
     */
    private ScriptEditor editor2;

    /**
     * Constructor.
     *
     * @param graphicArena GraphicArena
     */
    public DebugWindow(final GraphicArena graphicArena) {
        cursor = new SmartCursor();
        panelEditor = new JPanel();
        instructionPanel = new JPanel();
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setResizeWeight(.9d);
        instructionPanel.setLayout(new BorderLayout());
        closeDebugButton = new CloseDebugButton();
        final GraphicArena graphicArena1 = new GraphicArena(
                graphicArena.getMainPanel(), cursor);
        scriptEditorPanel = graphicArena.getMainPanel().getContentPanel().
                getTabbedPane().getScriptEditorPanel();
        liste = new InstructionsList(scriptEditorPanel.getScriptEditor());
        GraphicArenaPanel gp = new GraphicArenaPanel(
                graphicArena1.getMainPanel(), cursor);
        gp.getGraphicArenaToolsPanel().getDebugButton().setVisible(false);
        gp.getGraphicArenaToolsPanel().getSave().setVisible(false);
        gp.getGraphicArenaToolsPanel().getResetObstaclesButton().setVisible(false);
        gp.getGraphicArenaToolsPanel().getSupprimer().setVisible(false);
        gp.getGraphicArenaToolsPanel().add(closeDebugButton);
        if (!graphicArena.getPhysics().getMap().getTanks().isEmpty()) {
            gp.getGraphicArenaToolsPanel().getBackgroundComboBox().
                    setEnabled(false);
            gp.getGraphicArenaToolsPanel().getStartButton().setEnabled(true);
            gp.getGraphicArenaToolsPanel().getSoundButton().setEnabled(true);
        }
        debug = new JFrame();
        panelArene = new JPanel();
        editor = scriptEditorPanel.getScriptEditor();
        editor2 = editor;
        closeDebugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                close(debug, graphicArena1, scriptEditorPanel);
            }
        });
        liste.addMouseListener(new MouseListener() {
            @Override
            public final void mouseClicked(final MouseEvent e) {
                editor.openFile(editor.getFile());
                if (e.getClickCount() == 3) {
                    switch (liste.getSelectedValue().toString()) {
                        case "Avancer":
                            FileExplorer.saveFile(editor.getFile().getPath(),
                                    editor.getText()
                                            + "\n \ttank.moveForward();");
                            break;
                        case "Reculer":
                            FileExplorer.saveFile(editor.getFile().getPath(),
                                    editor.getText()
                                            + "\n \ttank.moveBackward();");
                            break;
                        case "Tourner a droite":
                            FileExplorer.saveFile(editor.getFile().getPath(),
                                    editor.getText()
                                            + "\n \ttank.turnClockwise();");
                            break;
                        case "Tourner a gauche":
                            FileExplorer.saveFile(editor.getFile().getPath(),
                                    editor.getText()
                                    + "\n \ttank.turnCounterClockwise();");
                            break;
                        case "Tirer":
                            FileExplorer.saveFile(editor.getFile().getPath(),
                                    editor.getText()
                                            + "\n \ttank.shoot();");
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
        gp.getGraphicArena().setPhysics(graphicArena.getPhysics());
        mainPanelCopie = graphicArena.getMainPanel();
        panelArene.setLayout(new BorderLayout());
        panelArene.add(gp, BorderLayout.CENTER);
        debug.setTitle("MODE DEBUG");
        debug.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));
        mainPanel.add(panelArene);
        panelEditor.setLayout(new BorderLayout());
        panelEditor.add(splitPane);
        instructionPanel.add(liste, BorderLayout.CENTER);
        instructionPanel.add(liste.getDescriptionIns(), BorderLayout.SOUTH);
        splitPane.add(scriptEditorPanel);
        splitPane.add(instructionPanel);
        mainPanel.add(panelEditor);
        debug.add(mainPanel);
        debug.setVisible(true);
        gp.repaint();
        gp.revalidate();
    }

    /**
     * fermeture de la fenetre debug.
     *
     * @param debugWindow JFrame
     * @param g           GraphicArena
     * @param sp          ScriptEditor
     */
    public final void close(final JFrame debugWindow,
                            final GraphicArena g,
                            final ScriptEditorPanel sp) {
        g.getMainPanel().getContentPanel().getTabbedPane().
                addTab("Editeur", sp);
        debugWindow.dispose();
    }
}
