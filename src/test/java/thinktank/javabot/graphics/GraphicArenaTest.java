package thinktank.javabot.graphics;

import demaciatanks.scripteditor.ScriptEditor;
import demaciatanks.swinginterface.*;
import org.junit.Before;
import org.junit.Test;
import thinktank.javabot.physics.*;

import static org.junit.Assert.*;

/**
 * Created Sarra Boussioud on 27/04/2017.
 */
public class GraphicArenaTest {
    GraphicArena graphicArena;
    @Before
    public void setUp() throws Exception {
        graphicArena = new GraphicArena(new MainPanel(), new SmartCursor());
    }

    @Test
    public void getPhysics() throws Exception {
        assertTrue(graphicArena.getPhysics() instanceof Physique);
    }

    @Test
    public void getImgLoader() throws Exception {
        assertTrue(graphicArena.getImgLoader() instanceof ImageLoader);
    }

    @Test
    public void getSmartCursor() throws Exception {
        assertTrue(graphicArena.getSmartCursor() instanceof SmartCursor);
    }

    @Test
    public void setPhysics() throws Exception {
        Physique physique = new Physique();
        graphicArena.setPhysics(physique);
        assertTrue(graphicArena.getPhysics().equals(physique));
    }

    @Test
    public void setResetButton() throws Exception {
        ResetButton resetButton = new ResetButton();
        resetButton.setText("test");
        graphicArena.setResetButton(resetButton);
        assertTrue(resetButton.getText().equals("test"));
    }

    @Test
    public void setSaveAreneButton() throws Exception {
        SaveAreneButton saveAreneButton = new SaveAreneButton();
        saveAreneButton.setText("test");
        graphicArena.setSaveAreneButton(saveAreneButton);
        assertTrue(saveAreneButton.getText().equals("test"));
    }

    @Test
    public void setDeleteObsButton() throws Exception {
        DeleteObstacleButton deleteObstacleButton = new DeleteObstacleButton();
        deleteObstacleButton.setText("test");
        graphicArena.setDeleteObsButton(deleteObstacleButton);
        assertTrue(deleteObstacleButton.getText().equals("test"));
    }

    @Test
    public void generateFileName() throws Exception {
        assertTrue(graphicArena.generateFileName().equals("arene_0"));
        assertFalse(graphicArena.generateFileName().equals("arene_1"));
    }

    @Test
    public void setSupprimerAreneButton() throws Exception {
        DeleteAreneButton deleteAreneButton = new DeleteAreneButton();
        deleteAreneButton.setText("test");
        graphicArena.setSupprimerAreneButton(deleteAreneButton);
        assertTrue(deleteAreneButton.getText().equals("test"));
    }

    @Test
    public void setSoundButton() throws Exception {
        SoundButton soundButton = new SoundButton();
        soundButton.setText("test");
        graphicArena.setSoundButton(soundButton);
        assertTrue(soundButton.getText().equals("test"));
    }

    @Test
    public void setDebugButton() throws Exception {
        DebugButton debugButton = new DebugButton();
        debugButton.setText("test");
        graphicArena.setDebugButton(debugButton);
        assertTrue(debugButton.getText().equals("test"));
    }

    @Test
    public void setResetObstaclesButton() throws Exception {

    }

    @Test
    public void setBackground() throws Exception {

    }

    @Test
    public void getMainPanel() throws Exception {
        assertTrue(graphicArena.getMainPanel() instanceof MainPanel);
    }

    @Test
    public void resetAll() throws Exception {
        assertTrue(graphicArena.getPhysics().getMap().getListAllTank().isEmpty());
        assertTrue(graphicArena.getPhysics().getMap().getListeObstacles().isEmpty());
        assertTrue(graphicArena.getPhysics().getMap().getProjectiles().isEmpty());
    }

    @Test
    public void setStartButton() throws Exception {

    }

    @Test
    public void addTankSelectionListener() throws Exception {

    }

    @Test
    public void triggerTankSelectionListeners() throws Exception {

    }

    @Test
    public void addSimulationListener() throws Exception {

    }

    @Test
    public void onSimulationStart() throws Exception {
        graphicArena.onSimulationStart();
        assertFalse(graphicArena.isPause());
    }

    @Test
    public void onSimulationStop() throws Exception {
        graphicArena.onSimulationStop();
        graphicArena.setPause(true);
        assertTrue(graphicArena.isPause());
    }

    @Test
    public void getPhysicPoint() throws Exception {

    }

    @Test
    public void getTankAt() throws Exception {
        Physique physique = new Physique();
        physique.addTank();
        assertTrue(!physique.getTanks().isEmpty());
        assertTrue(physique.getTanks().get(0).getCoordX() != 0);
        assertTrue(physique.getTanks().get(0).getCoordY() != 0);
    }
}