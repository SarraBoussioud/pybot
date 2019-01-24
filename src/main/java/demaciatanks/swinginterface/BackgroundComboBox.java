package demaciatanks.swinginterface;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 * Created by Sarra Boussioud on 22/02/2017.
 */

/**
 * Combobox des backgrounds.
 */
public class BackgroundComboBox extends JComboBox {
    /**
     * Liste des backgrounds a choisir.
     */
    private String[] liste = new String[] {
        "Sand", "Water", "Wood", "Grass", "Ocean", "Rocks"
    };

    /**
     * Constructor.
     */
    public BackgroundComboBox() {
        setComboBox();
    }

    public final void setComboBox(){
        DefaultComboBoxModel ynModel = new DefaultComboBoxModel(liste);
        this.setModel(ynModel);
        this.setRenderer(new MyComboBoxRenderer("Changer Background"));
        this.setSelectedIndex(-1);
    }

    public String[] getListe() {
        return liste;
    }

    public void setListe(String[] liste) {
        this.liste = liste;
    }
}
