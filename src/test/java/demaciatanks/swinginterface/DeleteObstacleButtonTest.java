package demaciatanks.swinginterface;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sarra Boussioud on 26/04/2017.
 */
public class DeleteObstacleButtonTest {
    DeleteObstacleButton deleteObstacleButton = new DeleteObstacleButton();
    @Test
    public void setDeleteObstacleText() throws Exception {
        deleteObstacleButton.setDeleteObstacleText();
        assertTrue(deleteObstacleButton.getText().equals("Delete Obstacle"));
    }
}