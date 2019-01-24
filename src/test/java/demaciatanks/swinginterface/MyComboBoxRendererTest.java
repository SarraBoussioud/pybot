package demaciatanks.swinginterface;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by SoF on 27/04/2017.
 */
public class MyComboBoxRendererTest {

    MyComboBoxRenderer myComboBoxRenderer;
    JList list;
    Object object;

    @Before
    public void setUp() throws Exception {
        myComboBoxRenderer = new MyComboBoxRenderer("test1");
        list = new JList();
        object = new Object();
    }

    @Test
    public void getListCellRendererComponent() throws Exception {
        assertTrue(myComboBoxRenderer.getListCellRendererComponent(list, object,1, false, true) instanceof Component);
    }

}