package demaciatanks.swinginterface;

import thinktank.javabot.graphics.GraphicArena;
import thinktank.javabot.physics.DTank;
import thinktank.javabot.physics.Tank;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.border.LineBorder;
import java.awt.Dimension;
import java.awt.Color;


/**
 * Created by HOANG on 2/21/17.
 */

/**
 * La liste des tanks sur l'arene.
 */
public class GraphicArenaListTank extends JPanel {

    /**
     * L'arene.
     */
    private GraphicArena arena;

    /**
     * Espace entre les images des tanks.
     */
    private static final int TAILLE = 7;

    /**
     * Niveau de thickness.
     */
    private static final int THICK = 3;

    /**
     * Taille max.
     */
    private static final int MAX_TAILLE = 1000;

    /**
     * Longueur du panel de la liste des tanks.
     */
    private static final int LONGUEUR = 40;

    /**
     * Largeur du panel de la liste des tanks.
     */
    private static final int LARGEUR = 45;

    /**
     * Taille max bar.
     */
    private static final int MAX_TAILLEBAR = 1500;

    /**
     * Longueur da barre de la liste des tanks.
     */
    private static final int LONGUEURBAR = 1000;


    /**
     * Constructor.
     *
     * @param a arene
     */
    public GraphicArenaListTank(final GraphicArena a) {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setMaximumSize(new Dimension(MAX_TAILLEBAR, LONGUEURBAR));
        this.setPreferredSize(new Dimension(LONGUEUR, LARGEUR));
        this.setBorder(new LineBorder(Color.LIGHT_GRAY, THICK));
        this.arena = a;
        loadTank();
    }

    /**
     * Rafraichissement la liste des tanks.
     */
    public final void loadTank() {
        this.removeAll();
        this.arena.getMainPanel().getFilePanel().getListPanel()
                .getScrollPane().getBoxManager().loadFiles();
        /*
            Liste des tanks qui vivrent
         */
        for (Tank t : arena.getPhysics().getMap().getTanks()) {
            DTank dt = (DTank) t;
            /*
             * Ajouter le panel du tank sur la liste des tanks sur Arene
             */
            JPanel panelTank = new JPanel();
            panelTank.setLayout(new BoxLayout(panelTank, BoxLayout.Y_AXIS));
            panelTank.setAlignmentY(0);
            /*
             * Image du tank
             */
            ImageIcon image = new ImageIcon("ressources/images/TankB"
                    + dt.getColorName(dt.getColor()) + ".png");
            JLabel label1 = new JLabel("", image, JLabel.CENTER);
            int hp = t.getPointsDeVie();
            ImageIcon imgHP = null;
            if (hp == 0) {
                imgHP = new ImageIcon("ressources/images/hp0.png");
            } else if (hp == 20) {
                imgHP = new ImageIcon("ressources/images/hp1.png");
            } else if (hp == 40) {
                imgHP = new ImageIcon("ressources/images/hp2.png");
            } else if (hp == 60) {
                imgHP = new ImageIcon("ressources/images/hp3.png");
            } else if (hp == 80) {
                imgHP = new ImageIcon("ressources/images/hp4.png");
            } else {
                imgHP = new ImageIcon("ressources/images/hp5.png");
            }
            /*
             * Image de HP
             */
            JLabel labelHP = new JLabel("", imgHP, JLabel.CENTER);
            panelTank.add(labelHP);
            panelTank.add(label1);
            this.add(Box.createRigidArea(new Dimension(TAILLE, 0)));
            this.add(panelTank);
        }
        /*
            Liste des tanks qui sont morts
         */
        for (Tank t : arena.getPhysics().getMap().getTanksMorts()) {
            DTank dt = (DTank) t;
            /**
             * Ajouter le panel du tank sur la liste des tanks sur Arene
             */
            JPanel panelTank = new JPanel();
            panelTank.setLayout(new BoxLayout(panelTank, BoxLayout.Y_AXIS));
            panelTank.setAlignmentY(0);
            /**
             * Image de tank mort
             */
            ImageIcon image = new ImageIcon("ressources/images/Dead/TankB"
                    + dt.getColorName(dt.getColor()) + "DEAD.png");
            JLabel label1 = new JLabel("", image, JLabel.CENTER);
            /**
             * Image de HP
             */
            JLabel labelHP = new JLabel("", new ImageIcon(
                    "ressources/images/hp0.png"), JLabel.CENTER);
            panelTank.add(labelHP);
            panelTank.add(label1);
            this.add(Box.createRigidArea(new Dimension(TAILLE, 0)));
            this.add(panelTank);
        }
    }
}
