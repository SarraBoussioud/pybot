package demaciatanks.swinginterface;

import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.JList;
import java.awt.Component;

/**
 * Created by Sarra Boussioud on 22/02/2017.
 */

/**
 * ComboBox Renderer.
 */
public class MyComboBoxRenderer extends JLabel implements ListCellRenderer {
    /**
     * Le titre.
     */
    private String title;

    /**
     * Constructor.
     *
     * @param t titre
     */
    public MyComboBoxRenderer(final String t) {
        this.title = t;
    }

    @Override
    public final Component getListCellRendererComponent(
            final JList list, final Object value, final int index,
            final boolean isSelected, final boolean cellHasFocus) {
        if (index == -1 && value == null) {
            setText(title);
        } else {
            setText(value.toString());
        }
        return this;
    }
}

