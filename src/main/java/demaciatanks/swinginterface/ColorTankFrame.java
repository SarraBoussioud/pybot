package demaciatanks.swinginterface;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.FlowLayout;


/**
 * La fenetre pour que l'utilisateur choisisse la couleur du tank.
 */
public class ColorTankFrame extends JFrame {
    /**
     * La longueur de la fenetre.
     */
    private static final int LONGUEUR = 500;
    /**
     * La largeur de la fenetre.
     */
    private static final int LARGEUR = 100;
    /**
     * Constructor.
     */
    public ColorTankFrame() {
        super("Choisir une couleur");
        coloreTank();
    }

    public final void coloreTank(){
        this.setPreferredSize(new Dimension(LONGUEUR, LARGEUR));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setLayout(new FlowLayout());
    }
}
