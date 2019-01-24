package demaciatanks.swinginterface;

import demaciatanks.scripteditor.ScriptEditor;
import thinktank.javabot.graphics.ImageLoader;
import thinktank.javabot.physics.SmartCursor;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Component;

/**
 * Le grand panel qui contient les panels de la liste des filebox,
 * la liste des instructions et la liste obstacles.
 */
public class FileBoxesScrollListPanel extends JPanel {
    /**
     * Serial.
     */
    private static final long serialVersionUID = 219557202734968426L;

    /**
     * Le panel de la liste des filebox.
     */
    private FileBoxesScrollPane fileBoxesScrollPane;

    /**
     * Le panel de la liste des instructions.
     */
    private InstructionsList instructionsList;

    /**
     * Le panel de la liste des obstacles.
     */

    private ObstaclesList obstaclesList;
    /**
     * Le label pour les instructions.
     */

    private JLabel labelInstructions;
    /**
     * Le label pour les obstacless.
     */

    private JLabel labelObstacls;

    /**
     * Constructor.
     * @param img imageLoader
     * @param cursor smart cursor
     * @param editor scriptEditor
     * @param newButtonPanel button pour ajouter un tank
     */
    public FileBoxesScrollListPanel(final ImageLoader img,
                                    final SmartCursor cursor,
                                    final ScriptEditor editor,
                                    final NewButtonPanel newButtonPanel) {
        fileBoxesScrollPane = new FileBoxesScrollPane(img,
                cursor,
                editor,
                newButtonPanel);
        this.setLayout(new BorderLayout());
        this.add(fileBoxesScrollPane);
        instructionsList = new InstructionsList(editor);
        obstaclesList = new ObstaclesList(editor);
        /*
            Label des instructions
         */
        this.labelInstructions = new JLabel();
        labelInstructions.setText("Liste des instructions:");
        labelInstructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        /*
            Label des obstacles
         */
        this.labelObstacls = new JLabel();
        labelObstacls.setText("Liste des obstacles:");
        labelObstacls.setAlignmentX(Component.CENTER_ALIGNMENT);
        /*
            Panel des instructions
         */
        JPanel panelInstructions = new JPanel();
        panelInstructions.setLayout(
                new BoxLayout(panelInstructions, BoxLayout.PAGE_AXIS));
        panelInstructions.add(labelInstructions);
        panelInstructions.add(instructionsList);
        panelInstructions.add(instructionsList.getDescriptionIns());
        /*
            Panel des obstacles
         */
        JPanel panObstacl = new JPanel();
        panObstacl.setLayout(new BoxLayout(panObstacl, BoxLayout.PAGE_AXIS));
        panObstacl.add(labelObstacls);
        panObstacl.add(obstaclesList.getList());
        /*
            Split 1 contient fileBoxesScrollPane et le panel des instructions
         */
        JSplitPane split1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        split1.add(fileBoxesScrollPane);
        split1.add(panelInstructions);
        split1.setResizeWeight(.9d);
        /*
            Split 2 contient le premier split et la liste des obstacles
         */
        JSplitPane split2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        split2.add(split1);
        split2.add(panObstacl);
        split2.setResizeWeight(.7d);
        /*
            Ajouter les deux split
         */
        this.add(split2, BorderLayout.CENTER);
    }

    /**
     * Getter FileBoxesScrollPane.
     * @return fileBoxesScrollPane
     */
    public final FileBoxesScrollPane getScrollPane() {
        return fileBoxesScrollPane;
    }

}
