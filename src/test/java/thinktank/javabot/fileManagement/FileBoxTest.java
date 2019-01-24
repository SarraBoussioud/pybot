package thinktank.javabot.fileManagement;

import org.junit.Before;
import org.junit.Test;
import thinktank.javabot.physics.Tank;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by Sarra Boussioud on 27/04/2017.
 */
public class FileBoxTest {
    FileBox fileBox;
    @Before
    public void setUp() throws Exception {
        fileBox = new FileBox();
    }

    @Test
    public void getFile() throws Exception {
        fileBox.setFile(new File("test"));
        assertTrue(fileBox.getFile() instanceof File);
    }

    @Test
    public void setFile() throws Exception {
        File file = new File("test");
        fileBox.setFile(file);
        assertTrue(fileBox.getFile().equals(file));
    }


}