package demaciatanks.swinginterface;

import javax.swing.*;

/**
 * Created by SoF on 24/03/2017.
 */
public class DeleteObstacleButton extends JButton {

    public DeleteObstacleButton() {
        setDeleteObstacleText();
    }

    /**
     * Set text Delete Obstacle.
     */
    public final void setDeleteObstacleText() {
        this.setText("Delete Obstacle");
    }

    /**
     * Instance unique pré-initialisée.
     */
    private static DeleteObstacleButton instance = new DeleteObstacleButton();

    public static DeleteObstacleButton getInstance() {
        return instance;
    }
}
