package demaciatanks.swinginterface;

import demaciatanks.scripteditor.ScriptEditor;
import thinktank.javabot.physics.Obstacle;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.Random;

/**
 * Created by MONTASSER on 01/03/2017.
 */

/**
 * La liste d'ajout des obstacles.
 */
public class ObstaclesList extends JList {
    /**
     * Les obstacles disponibles.
     */
    private String[] obs = {
            "Obstacle 1", "Obstacle 2", "Obstacle 3"
    };

    /**
     * Modele.
     */
    private DefaultListModel model;

    /**
     * Liste des obstacles.
     */
    private JList list;

    /**
     * Longueur des champs dans JList.
     */
    private static final int LONGUEUR = 250;

    /**
     * Largeur des champs dans JList.
     */
    private static final int LARGEUR = 30;

    /**
     * Valeur Min.
     */
    private static final int MIN = 2;

    /**
     * Valeur Max x.
     */
    private static final int MAX_X = 38;

    /**
     * Valeur Max y.
     */
    private static final int MAX_Y = 20;

    /**
     * Constructor empty.
     */
    public ObstaclesList() {
    }

    /**
     * Constructor.
     *
     * @param editor editor
     */
    public ObstaclesList(final ScriptEditor editor) {
        model = new DefaultListModel();
        model.addElement(new ImageIcon("ressources/images/mur3.png"));
        model.addElement(new ImageIcon("ressources/images/mur4.png"));
        model.addElement(new ImageIcon("ressources/images/mur5.png"));
        list = new JList(model);
        list.setFixedCellWidth(LONGUEUR);
        list.setFixedCellHeight(LARGEUR);
        list.setAlignmentX(Component.CENTER_ALIGNMENT);
        list.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(final MouseEvent e) {
                super.mouseDragged(e);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                editor.getContentTabbedPane().getGraphicArenaPanel().
                        getGraphicArena().repaint();
            }
        });
        list.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(final MouseEvent e) {
            }

            @Override
            public void mousePressed(final MouseEvent e) {
                switch (list.getSelectedIndex()) {
                    case 0:
                        Obstacle obstacle1 = new Obstacle(1,
                                editor.getContentTabbedPane().getGraphicArenaPanel().
                                        getGraphicArena().getPhysics().getMap());
                        editor.getContentTabbedPane().getGraphicArenaPanel().
                                getGraphicArena().getPhysics().getMap().
                                getListeObstacles().add(obstacle1);
                        break;
                    case 1:
                        int x = aleatoire(1, editor.getContentTabbedPane().
                                getGraphicArenaPanel().getGraphicArena().
                                getPhysics().getMap().tailleX() - 1);
                        int y = aleatoire(1, editor.getContentTabbedPane().
                                getGraphicArenaPanel().getGraphicArena().
                                getPhysics().getMap().tailleY() - 1);
                        Obstacle obstacle2 = new Obstacle(2, editor.getContentTabbedPane().
                                getGraphicArenaPanel().getGraphicArena().
                                getPhysics().getMap());
                        editor.getContentTabbedPane().getGraphicArenaPanel().
                                getGraphicArena().getPhysics().getMap().
                                getListeObstacles().add(obstacle2);
                        break;
                    case 2:
                        Obstacle obstacle3 = new Obstacle(3, editor.getContentTabbedPane().
                                getGraphicArenaPanel().getGraphicArena().
                                getPhysics().getMap());
                        editor.getContentTabbedPane().getGraphicArenaPanel().
                                getGraphicArena().getPhysics().getMap().
                                getListeObstacles().add(obstacle3);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void mouseReleased(final MouseEvent e) {
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
            }

            @Override
            public void mouseExited(final MouseEvent e) {
            }
        });
    }

    /**
     * Donner un nombre aleatoire entre x et y.
     *
     * @param x valeur min
     * @param y valeur max
     * @return r integer
     */
    private int aleatoire(final int x, final int y) {
        Random r = new Random();
        return x + r.nextInt(y - x);
    }

    /**
     * Getter liste.
     *
     * @return list
     */
    public final JList getList() {
        return this.list;
    }

    /**
     * Getter obs.
     *
     * @return obs
     */
    public final String[] getObs() {
        return obs;
    }
}
