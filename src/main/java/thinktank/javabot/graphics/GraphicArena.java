package thinktank.javabot.graphics;

import demaciatanks.swinginterface.*;
import thinktank.javabot.fileManagement.FileBox;
import thinktank.javabot.fileManagement.FileExplorer;
import thinktank.javabot.physics.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

import static thinktank.javabot.graphics.ImageLoader.toBufferedImage;

/**
 * Graphic arena.
 */
public class GraphicArena extends JComponent {
    /**
     * Clicked Obstacle X.
     */
    int obsX = 0;

    /**
     * Clicked Obstacle Y.
     */
    int obsY = 0;

    /**
     * Serial.
     */
    private static final long serialVersionUID = 46483486L;

    /**
     * Size d'un pixel.
     */
    private static final int PIXEL_SIZE = 32;

    /**
     * Physique.
     */
    private Physique physics = new Physique();

    /**
     * Tank choisi.
     */
    private Tank highlightTank;

    /**
     * Smart cursor.
     */
    private SmartCursor cursor;

    /**
     * Taille d'un case.
     */
    private static int tailleCase = 32;

    /**
     * Image loader.
     */
    public static ImageLoader imgLoader = new ImageLoader();

    /**
     * Main panel.
     */
    private MainPanel panel;

    /**
     * Thread.
     */
    private SimulationThread thread;

    /**
     * Liste listener des tanks.
     */
    private List<TankSelectionListener> selectionListeners;

    /**
     * Liste listener de la simulation.
     */
    private List<SimulationListener> simulationListeners;

    /**
     * Le repertoire contient des scripts.
     */
    private static String directoryPath = "scripts";

    /**
     * Boolean si en pause.
     */
    private boolean pause = false;

    /**
     *
     * @return boolean
     */
    public boolean isPause() {
        return pause;
    }

    /**
     *
     * @param pause
     */
    public void setPause(boolean pause) {
        this.pause = pause;
    }

    /**
     * Listener tank selection.
     */
    public interface TankSelectionListener {
        void onSelection(DTank tank);
    }

    /**
     * Interface simulationListener.
     */
    public interface SimulationListener {
        void onStart();

        void onStop();
    }

    /**
     * Constructor.
     *
     * @param panel  main panel
     * @param cursor smart cursor
     */
    public GraphicArena(final MainPanel panel,
                        final SmartCursor cursor) {
        this.panel = panel;
        this.cursor = cursor;
        thread = null;
        selectionListeners = new LinkedList<>();
        simulationListeners = new LinkedList<>();
        GraphicArenaMouseListener list = new GraphicArenaMouseListener();
        this.addMouseListener(list);
        this.addMouseMotionListener(list);
    }

    /**
     * Getter physique.
     *
     * @return physique
     */
    public final Physique getPhysics() {
        return physics;
    }

    /**
     * Getter image loader.
     *
     * @return image loader
     */
    public final ImageLoader getImgLoader() {
        return imgLoader;
    }

    /**
     * Getter smart cursor.
     *
     * @return smart cursor
     */
    public final SmartCursor getSmartCursor() {
        return cursor;
    }

    /**
     * Setter physique.
     *
     * @param physics physique
     */
    public final void setPhysics(final Physique physics) {
        this.physics = physics;
    }

    /**
     * Set listener reset.
     * @param reset resetButton
     */
    public final void setResetButton(final ResetButton reset) {
        reset.addActionListener(new ResetButtonListener());
    }
    /**
     * Set Listener save
     * @param save SaveAreneButton
     */
    public final void setSaveAreneButton(final SaveAreneButton save) {
        save.addActionListener(new SaveAreneButtonListener());
    }

    /**
     * Set Listener delete obstacle
     * @param deleteObs DeleteObstacleButton
     */
    public final void setDeleteObsButton(final DeleteObstacleButton deleteObs){
        deleteObs.addActionListener(new DeleteObstacleButtonListener());
    }

    /**
     * Genere un nom de sauvegarde pour l'arene
     */
    public final String generateFileName() {
        String basePath = "arene_";
        int counter = 0;
        while (new File("ressources/arenes/"+basePath + counter).exists()) {
            counter++;
        }
        return basePath + counter;
    }

    /**
     * Listener save.
     */
    public class SaveAreneButtonListener implements ActionListener {
        @Override
        public final void actionPerformed(final ActionEvent e) {
            String sb = "TEST CONTENT";
            File fileArene = new File("ressources/arenes");
            if (!fileArene.exists())
                fileArene.mkdir();
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("ressources/arenes"));
            int retrival = chooser.showSaveDialog(null);
            String path = Paths.get(chooser.getSelectedFile().getAbsolutePath()).toString();
            if (retrival == JFileChooser.APPROVE_OPTION) {
                try {
                    File orig = new File(path);
                    File tmp = null;
                    PrintWriter writer = null;
                    /*
                        Si le fichier existe deja
                     */
                    if(orig.exists()) {
                        tmp = new File(orig + ".tmp");
                        try {
                            writer = new PrintWriter(new BufferedWriter(
                                    new FileWriter(
                                            tmp, false)));
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        if (!physics.getMap().getListeObstacles().isEmpty()) {
                            for (Obstacle obstacle : physics.getMap().getListeObstacles()) {
                                writer.print(obstacle.getCoordX() + " " + obstacle.getCoordY() + " " + obstacle.getTypeObs());
                                writer.println();
                            }
                        }
                        writer.close();
                        //Remplacer le fichier arene par le fichier tmp
                        if (orig.delete()) {
                            tmp.renameTo(orig);
                            JOptionPane.showMessageDialog(panel, "L'arene a bien été enregistée ");
                        } else {
                            JOptionPane.showMessageDialog(null, "L'arene ne peut pas etre enregistrée");
                        }
                    } else {
                        try {
                            try {
                                writer = new PrintWriter(new BufferedWriter(
                                        new FileWriter(
                                                path, true)));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            if(!physics.getMap().getListeObstacles().isEmpty()){
                                for (Obstacle obstacle: physics.getMap().getListeObstacles()) {
                                    writer.print(obstacle.getCoordX()+ " "+obstacle.getCoordY() + " " +obstacle.getTypeObs());
                                    writer.println();
                                }
                            }
                            writer.close();
                            JOptionPane.showMessageDialog(panel,"L'arene a bien été enregistée ");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    /**
     * Set Listener supprimer
     * @param deleteAreneButton DeleteAreneButton
     */
    public final void setSupprimerAreneButton(final DeleteAreneButton deleteAreneButton){
        deleteAreneButton.addActionListener(new DeleteAreneButtonListener());
    }
    /**
     * Listener supprimer.
     */
    public class DeleteAreneButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            fenetreSuppression();
        }
    }
    /**
     * Fenetre de suppression des arenes sauvegardées
     */
    public void fenetreSuppression(){
        final JFrame dialog = new JFrame();
        dialog.setTitle("Liste des arenes");
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        dialog.setMinimumSize(new Dimension(300,300));
        dialog.setLocationRelativeTo(null);
        File fileArene = new File("ressources/arenes");
        if (!fileArene.exists())
            fileArene.mkdir();
        List<File> ListeArenes = FileExplorer.getListFile("ressources/arenes","");
        final List<String> arenes = new ArrayList();
        for (File f:ListeArenes) {
            arenes.add(f.getName());
        }
        final JList listeArene = new JList(arenes.toArray());
        mainPanel.add(listeArene, BorderLayout.CENTER);
        final RemoveButton supprimer = new RemoveButton();
        JButton annuler = new JButton("Annuler");
        final ImporteAreneButton importer = new ImporteAreneButton();
        JPanel panelBouton = new JPanel();
        panelBouton.setLayout(new BoxLayout(panelBouton, BoxLayout.X_AXIS));
        panelBouton.add(Box.createHorizontalGlue());
        panelBouton.add(supprimer);
        panelBouton.add(Box.createHorizontalGlue());
        panelBouton.add(annuler);
        panelBouton.add(Box.createHorizontalGlue());
        panelBouton.add(importer);
        panelBouton.add(Box.createHorizontalGlue());
        mainPanel.add(panelBouton, BorderLayout.SOUTH);
        dialog.add(mainPanel);
        dialog.setVisible(true);
        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        listeArene.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

                supprimer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String arene = listeArene.getSelectedValue().toString();
                        if (arenes.contains(arene))
                            arenes.remove(arene);
                        FileExplorer.deleteFile("ressources/arenes/" + arene);
                        listeArene.setListData(arenes.toArray());
                        JOptionPane.showMessageDialog(panel,"L'arene "+ arene + " a bien été supprimée");
                        listeArene.repaint();
                        listeArene.revalidate();
                        dialog.dispose();
                    }
                });

                importer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String arene = listeArene.getSelectedValue().toString();
                        JOptionPane.showMessageDialog(panel, "L'arène '"+ arene +"' va être telechargé ");
                        panel.getContentPanel().getTabbedPane().getGraphicArenaPanel().getGraphicArena().getPhysics();
                        ArrayList<Obstacle> listeObstacles = new ArrayList<Obstacle>();
                        //Effacer tout les obstacles sur l'arene
                         for (Obstacle obstacle : physics.getMap().getListeObstacles()) {
                            obstacle.getMap().erase(obstacle.getCoordX(), obstacle.getCoordY());
                         }
                         physics.getMap().setListeObstacles(listeObstacles);
                         panel.repaint();
                         panel.revalidate();
                        try{
                            InputStream flux=new FileInputStream("ressources/arenes/"+arene);
                            InputStreamReader lecture=new InputStreamReader(flux);
                            BufferedReader buff=new BufferedReader(lecture);
                            String ligne;

                            while ((ligne=buff.readLine())!=null){
                                String [] spit = ligne.split("\\s");
                                for(int i = 0; i < spit.length; i++){
                                    int x = Integer.parseInt(spit[0]);
                                    int y = Integer.parseInt(spit[1]);
                                    int a = Integer.parseInt(spit[2]);
                                    Obstacle obstacle = new Obstacle(a, physics.getMap(), x, y);
                                    listeObstacles.add(obstacle);
                                }
                            }

                            buff.close();
                        }
                        catch (Exception ec){
                            System.out.println(ec.toString());
                        }
                        //Ajout des obstacles dans la liste des obstacles
                        physics.getMap().setListeObstacles(listeObstacles);
                        panel.repaint();
                        panel.revalidate();
                        dialog.dispose();
                    }
                });

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    /**
     * Listener reset.
     */
    public class ResetButtonListener implements ActionListener {
        @Override
        public final void actionPerformed(final ActionEvent e) {
            resetAll();
            Sound.getInstance().stop();
        }
    }

    /**
     * Le son de l'arene
     *
     * @param soundButton button du son
     */
    public void setSoundButton(final SoundButton soundButton) {
        soundButton.addActionListener(new SoundButtonListener());
    }

    /**
     * Listener bouton Sound.
     */
    public class SoundButtonListener implements ActionListener {
        @Override
        public void actionPerformed(final ActionEvent e) {
            if (!(e.getSource() instanceof SoundButton)) {
                return;
            }
            SoundButton soundButton = (SoundButton) e.getSource();
            if (Sound.getInstance().getClip().isActive()) {
                soundButton.setIcon(new ImageIcon(
                        "ressources/images/soundOn.png"));
                Sound.getInstance().stop();
            } else if (!Sound.getInstance().getClip().isActive()) {
                soundButton.setIcon(new ImageIcon(
                        "ressources/images/soundOff.png"));
                Sound.getInstance().play();
            }
        }
    }

    /**
     * Le mode Debug.
     *
     * @param debugButton button Debug
     */
    public final void setDebugButton(final DebugButton debugButton) {
        debugButton.addActionListener(new DebugButtonListener());
    }

    /**
     * Debug listener.
     */
    public class DebugButtonListener implements ActionListener {
        @Override
        public final void actionPerformed(final ActionEvent e) {
            debugFenetreRun();
        }
    }

    /**
     * Lancer la fenetre Debug.
     */
    public final void debugFenetreRun() {
        DebugWindow debugWindow = new DebugWindow(this);
    }

    /**
     * set reset obstacle bouton Listener.
     *
     * @param resetObstaclesButton bouton reset
     */
    public final void setResetObstaclesButton(
            final ResetObstaclesButton resetObstaclesButton) {
        resetObstaclesButton.addActionListener(new resetObstaclesListener());
    }

    /**
     * Reset obstacle listener.
     */
    public class resetObstaclesListener implements ActionListener {
        @Override
        public void actionPerformed(final ActionEvent e) {
            if (!physics.getMap().getListeObstacles().isEmpty()) {
                for (Obstacle o : physics.getMap().getListeObstacles()) {
                    o.getMap().erase(o.getCoordX(), o.getCoordY());
                    repaint();
                    validate();
                }
                physics.getMap().getListeObstacles().clear();
            }
        }
    }

    /**
     * Changer le background de l'arene.
     *
     * @param backgroundComboBox liste des background a choisir
     */
    public final void setBackground(
            final BackgroundComboBox backgroundComboBox) {
        backgroundComboBox.addActionListener(new BackgroundListener());
    }

    /**
     * Background listener.
     */
    public class BackgroundListener implements ActionListener {
        @Override
        public final void actionPerformed(final ActionEvent e) {
            try {
                BackgroundComboBox backgroundComboBox = (BackgroundComboBox) e.getSource();
                ImageLoader imgL = imgLoader;
                switch (backgroundComboBox.getSelectedItem().toString()) {
                    case "Water":
                        imgL.getImg()[ImageLoader.SpriteName.SOL.ordinal()] =
                                toBufferedImage(ImageIO.read(
                                        new File("ressources/images/"
                                                + backgroundComboBox.getSelectedItem().toString() + ".png")));
                        physics = new Physique(Vide.setVide(imgL));
                        break;
                    case "Sand":
                        imgL.getImg()[ImageLoader.SpriteName.SOL.ordinal()] =
                                toBufferedImage(ImageIO.read(
                                        new File("ressources/images/"
                                                + backgroundComboBox.getSelectedItem().toString() + ".png")));
                        physics = new Physique(Vide.setVide(imgL));
                        break;
                    case "Wood":
                        imgL.getImg()[ImageLoader.SpriteName.SOL.ordinal()] =
                                toBufferedImage(ImageIO.read(
                                        new File("ressources/images/"
                                                + backgroundComboBox.getSelectedItem().toString() + ".png")));
                        physics = new Physique(Vide.setVide(imgL));
                        break;
                    case "Grass":
                        imgL.getImg()[ImageLoader.SpriteName.SOL.ordinal()] =
                                toBufferedImage(ImageIO.read(
                                        new File("ressources/images/sol.png")));
                        physics = new Physique(Vide.setVide(imgL));
                        break;
                    case "Ocean":
                        imgL.getImg()[ImageLoader.SpriteName.SOL.ordinal()] =
                                toBufferedImage(ImageIO.read(
                                        new File("ressources/images/"
                                                + backgroundComboBox.getSelectedItem().toString() + ".png")));
                        physics = new Physique(Vide.setVide(imgL));
                        break;
                    case "Rocks":
                        imgL.getImg()[ImageLoader.SpriteName.SOL.ordinal()] =
                                toBufferedImage(ImageIO.read(
                                        new File("ressources/images/"
                                                + backgroundComboBox.getSelectedItem().toString() + ".png")));
                        physics = new Physique(Vide.setVide(imgL));
                        break;
                    default:
                        break;
                }
                updateUI();
                revalidate();
                repaint();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Getter MainPanel.
     *
     * @return mainPanel
     */
    public final MainPanel getMainPanel() {
        return panel;
    }

    /**
     * Initialiser le jeu.
     */
    public final void resetAll() {
        //Effacer tous les scripts
        FileExplorer.checkFor(directoryPath);
        List<File> filesInFolder = FileExplorer.getListFile(directoryPath, ".py");
        for (File file : filesInFolder) {
            FileExplorer.deleteFile(file.getPath());
        }
        this.getMainPanel().getFilePanel().getListPanel().getScrollPane().getBoxManager().loadFiles();
        this.getMainPanel().getFilePanel().getListPanel().getScrollPane().validate();
        this.getMainPanel().getFilePanel().getListPanel().getScrollPane().repaint();
        //Effacer tous les tanks sur l'arene
        for (Tank tank : this.physics.getMap().getTanks()) {
            tank.getMap().erase(tank.getCoordX(), tank.getCoordY());
        }
        //Effacer tout les projectiles sur l'arene
        for (Projectile projectile : this.physics.getMap().getProjectiles()) {
            projectile.getMap().erase(projectile.getCoordX(), projectile.getCoordY());
        }
        //Effacer tout les obstacles sur l'arene
        for (Obstacle obstacle : this.physics.getMap().getListeObstacles()) {
            obstacle.getMap().erase(obstacle.getCoordX(), obstacle.getCoordY());
        }
        //Effacer le fichier Couleur
        FileExplorer.deleteFile("ressources/couleurs/couleur");
        //Initialiser toutes les listes
        physics.getMap().getTanksMorts().clear();
        physics.getMap().getTanks().clear();
        physics.getMap().getProjectiles().clear();
        if (physics.getMap().getTanks().isEmpty()) {
            this.panel.getContentPanel().getTabbedPane().getGraphicArenaPanel().getGraphicArenaToolsPanel()
                    .getStartButton().setEnabled(false);
            this.panel.getContentPanel().getTabbedPane().getGraphicArenaPanel().getGraphicArenaToolsPanel()
                    .getBackgroundComboBox().setEnabled(true);
            this.panel.getContentPanel().getTabbedPane().getGraphicArenaPanel().getGraphicArenaToolsPanel()
                    .getSoundButton().setEnabled(false);
        }
        //Rafraichir l'arene
        validate();
        repaint();
        getMainPanel().getContentPanel().getTabbedPane().getGraphicArenaPanel().getGraphicArenaListTank().loadTank();
        getMainPanel().getContentPanel().getTabbedPane().getGraphicArenaPanel().getGraphicArenaListTank().updateUI();
    }

    /**
     * Set start bouton.
     *
     * @param button start button
     */
    public final void setStartButton(
            final StartButton button) {
        button.addActionListener(new SimulationLauncher());
    }

    /**
     * Ajouter tank listener.
     *
     * @param listener listener
     */
    public final void addTankSelectionListener(
            final TankSelectionListener listener) {
        selectionListeners.add(listener);
    }

    /**
     * Lancer tank listener.
     *
     * @param selectedTank tank selected
     */
    public final void triggerTankSelectionListeners(
            final DTank selectedTank) {
        for (TankSelectionListener listener : selectionListeners) {
            listener.onSelection(selectedTank);
        }
    }

    /**
     * Ajouter simulation listener.
     *
     * @param listener listener
     */
    public final void addSimulationListener(
            final SimulationListener listener) {
        simulationListeners.add(listener);
    }

    /**
     * En cours simulation.
     */
    public final void onSimulationStart() {
        for (SimulationListener listener : simulationListeners) {
            listener.onStart();
            if (pause) {
                physics.getTimer().start();
            }
        }
    }

    /**
     * Simulation est arrete.
     */
    public void onSimulationStop() {
        for (SimulationListener listener : simulationListeners) {
            listener.onStop();
            if (physics.getTimer().isRunning()) {
                physics.getTimer().stop();
                pause = true;
            }
        }
    }

    /**
     * Deplacer le tank vers une autre place.
     *
     * @param tank    le tank a deplacer
     * @param newX X
     * @param newY Y
     */
    public final void moveTankTo(final Tank tank,
                                 final int newX,
                                 final int newY) {
        int old_x = tank.getCoordX(), old_y = tank.getCoordY();
        if (newX == old_x && newY == old_y) {
            return;
        }
        if (newX < 0 || newX >= physics.getLignes()
                || newY < 0 || newY >= physics.getColonnes()) {
            physics.getMap().erase(old_x, old_y);
        } else if (physics.getMap().testAndSetCase(tank, newX, newY)) {
            tank.setCoordX(newX);
            tank.setCoordY(newY);
            physics.getMap().erase(old_x, old_y);
        }
    }

    /**
     * Simulation thread.
     */
    public class SimulationThread extends Thread {
        @Override
        public final void run() {
            while (true) {
                physics.iter();
                repaint();
                getMainPanel().getContentPanel().getTabbedPane().getGraphicArenaPanel().
                        getGraphicArenaListTank().loadTank();
                getMainPanel().getContentPanel().getTabbedPane().getGraphicArenaPanel().
                        getGraphicArenaListTank().updateUI();
            }
        }
    }

    /**
     * Simulation launcher.
     */
    public class SimulationLauncher implements ActionListener {
        @SuppressWarnings("deprecation")
        @Override
        public final void actionPerformed(final ActionEvent e) {
            if (!(e.getSource() instanceof StartButton)) {
                return;
            }
            StartButton button = (StartButton) e.getSource();
            if (thread == null) {
                thread = new SimulationThread();
                onSimulationStart();
                button.setStopText();
                thread.start();
                panel.getContentPanel().getTabbedPane().getGraphicArenaPanel().
                        getGraphicArenaToolsPanel()
                        .getSoundButton().setEnabled(true);
            } else {
                onSimulationStop();
                button.setStartText();
                thread.stop();
                thread = null;
                panel.getContentPanel().getTabbedPane().getGraphicArenaPanel().
                        getGraphicArenaToolsPanel()
                        .getSoundButton().setEnabled(false);
            }
        }

    }

    /**
     * Graphic Arena listener.
     */
    public class GraphicArenaMouseListener
            implements MouseListener, MouseMotionListener {
        @Override
        public final void mouseClicked(final MouseEvent arg0) {
            ObjetTT contenu;
            Point2D pointUsable = getPhysicPoint(arg0.getX(), arg0.getY());
            int i = (int) (pointUsable.getX());
            int j = (int) (pointUsable.getY());

            contenu = physics.detail(i, j);
            if (contenu.getType() == Physique.TypeP.tank) {
                highlightTank = (Tank) contenu;
                triggerTankSelectionListeners((DTank) contenu);
            } else {
                highlightTank = null;
            }
        }

        @Override
        public final void mouseEntered(final MouseEvent arg0) {
            boolean ok = false;
            if (cursor.getTank() == null) {
                return;
            }
            if (cursor.isActive()) {
                Point2D pointGraphic = getPhysicPoint(arg0.getX(), arg0.getY());
                int x = (int) (pointGraphic.getX());
                int y = (int) (pointGraphic.getY());
                cursor.setCoordMovingObj(x, y);
                for (Tank t : physics.getTanks()) {
                    if (t == cursor.getTank()) {
                        t.setCoordX(x);
                        t.setCoordY(y);
                    }
                }
                //Verifier si le script du tank dans le cursor existe dans la liste Filebox
                if (getMainPanel().getFilePanel().getListPanel().getScrollPane().
                        getBoxManager().getListFileBox().size() != 0) {
                    for (FileBox fb : getMainPanel().getFilePanel().getListPanel().
                            getScrollPane().getBoxManager().getListFileBox()) {
                        if (Objects.equals(fb.getFile().getPath(),
                                cursor.getTank().getScriptPath())) {
                            ok = true;
                        }
                    }
                }
                //Verifier si il existe dans la liste des tanks sur l'arene
                for (Tank t : getPhysics().getMap().getTanks()) {
                    if (((DTank) t).getColorName(((DTank) t).getColor())
                            == cursor.getTank().getColorName(cursor.getTank().getColor())) {
                        ok = false;
                    }
                }
                if (ok) {
                    addDTank(cursor.getTank());
                    cursor.getTank().setDirection(new Direction(x, y));
                    moveTankTo(cursor.getTank(), x, y);
                    cursor.desactivate();
                    repaint();
                    getMainPanel().getContentPanel().getTabbedPane().getGraphicArenaPanel().
                            getGraphicArenaListTank().loadTank();
                    getMainPanel().getContentPanel().getTabbedPane().getGraphicArenaPanel().
                            getGraphicArenaListTank().updateUI();
                }
                cursor.desactivate();
            }
        }

        @Override
        public final void mouseExited(final MouseEvent arg0) {
            if (cursor.isActive()) {
                removeObject(cursor.getTank());
                repaint();
            }
        }

        @Override
        public final void mousePressed(final MouseEvent arg0) {
            boolean exist = false;
            if (!cursor.isActive()) {
                Point2D pointGraphic = getPhysicPoint(arg0.getX(), arg0.getY());
                ObjetTT contenu = physics.detail((int) pointGraphic.getX(), (int) pointGraphic.getY());
                if (contenu.getType() == Physique.TypeP.tank) {
                    //Verifier si le tank existe deja sur l'arene
                    if (getPhysics().getMap().getTanks().size() != 0) {
                        for (Tank t : getPhysics().getMap().getTanks()) {
                            if (((DTank) t).getColorName(((DTank) t).getColor())
                                    == cursor.getTank().getColorName(cursor.getTank().getColor())) {
                                exist = true;
                            }
                        }
                    }
                    //Si il n'existe pas
                    if ((exist) && (getPhysics().getMap().getTanks().size() != 0)) {
                        cursor.setTank((DTank) contenu);
                        repaint();
                    }
                } else if (contenu.getType() == Physique.TypeP.obstacle) {
                    obsX = (int) pointGraphic.getX();
                    obsY = (int) pointGraphic.getY();
                }
                else {
                    obsX = 0;
                    obsY = 0;
                }
            }
        }

        @Override
        public final void mouseReleased(final MouseEvent arg0) {
            boolean exist = false;
            if (cursor.isActive()) {
                Point2D pointGraphic = getPhysicPoint(arg0.getX(), arg0.getY());
                //Verifier si il existe deja le tank sur Arene
                if (getPhysics().getMap().getTanks().size() != 0) {
                    for (Tank t : getPhysics().getMap().getTanks()) {
                        if (((DTank) t).getColorName(((DTank) t).getColor())
                                == cursor.getTank().getColorName(cursor.getTank().getColor())) {
                            exist = true;
                        }
                    }
                }
                int x = (int) (pointGraphic.getX());
                int y = (int) (pointGraphic.getY());
                if ((exist) && (getPhysics().getMap().getTanks().size() != 0)) {
                    moveTankTo(cursor.getTank(), x, y);
                    cursor.desactivate();
                    repaint();
                    getMainPanel().getContentPanel().getTabbedPane().getGraphicArenaPanel().
                            getGraphicArenaListTank().loadTank();
                    getMainPanel().getContentPanel().getTabbedPane().getGraphicArenaPanel().
                            getGraphicArenaListTank().updateUI();
                }
            }
        }

        @Override
        public final void mouseDragged(final MouseEvent arg0) {
            if (cursor.isActive()) {
                Point2D pointGraphic = getPhysicPoint(arg0.getX(), arg0.getY());
                int x = (int) (pointGraphic.getX());
                int y = (int) (pointGraphic.getY());
                moveTankTo(cursor.getTank(), x, y);
                revalidate();
                repaint();
                getMainPanel().getContentPanel().getTabbedPane().getGraphicArenaPanel().
                        getGraphicArenaListTank().loadTank();
                getMainPanel().getContentPanel().getTabbedPane().getGraphicArenaPanel().
                        getGraphicArenaListTank().updateUI();
            }
        }

        @Override
        public final void mouseMoved(final MouseEvent arg0) {
        }

    }

    /**
     * Get point.
     *
     * @param x x
     * @param y y
     * @return point2D
     */
    public final Point2D getPhysicPoint(final int x,
                                        final int y) {
        int virtual_width = physics.getLignes() * PIXEL_SIZE;
        int virtual_height = physics.getColonnes() * PIXEL_SIZE;
        int real_width = getWidth();
        int real_height = getHeight();
        double width_ratio = real_width / (double) virtual_width;
        double height_ratio = real_height / (double) virtual_height;
        double choosen_ratio = Math.min(width_ratio, height_ratio);
        double painting_width = choosen_ratio * virtual_width;
        double painting_height = choosen_ratio * virtual_height;
        int final_x = -1;
        int final_y = -1;
        if (width_ratio < height_ratio) { // cas on a colle sur les cotes
            final_x = (int) (x / choosen_ratio);
            double offset_height = Math.max(real_height - painting_height,
                    painting_height - real_height);
            final_y = (int) ((y - offset_height / 2) / choosen_ratio);
        } else {
            final_y = (int) (y / choosen_ratio);
            double offset_width = Math.max(real_width - painting_width,
                    painting_width - real_width);
            final_x = (int) ((x - offset_width / 2) / choosen_ratio);
        }
        return new Point(final_x / PIXEL_SIZE, final_y / PIXEL_SIZE);
    }

    /**
     * Ajouter tank.
     *
     * @param tank tank
     */
    public final void addDTank(final DTank tank) {
        DTank dTank = new DTank(physics.getMap(), tank.getScriptPath(), tank.getColor(),
                tank.getScriptPath(), imgLoader, physics);
        physics.getMap().addTankTotal(dTank);
        physics.addTank(dTank);
        getMainPanel().getContentPanel().getTabbedPane().
                getGraphicArenaPanel().getGraphicArenaListTank().loadTank();
        getMainPanel().getContentPanel().getTabbedPane().
                getGraphicArenaPanel().getGraphicArenaListTank().updateUI();
        panel.getContentPanel().getTabbedPane().
                getGraphicArenaPanel().getGraphicArenaToolsPanel()
                .getBackgroundComboBox().setEnabled(false);
        if (!physics.getMap().getTanks().isEmpty()) {
            panel.getContentPanel().getTabbedPane().
                    getGraphicArenaPanel().getGraphicArenaToolsPanel().
                    getStartButton().setEnabled(true);
        }
    }

    /**
     * Supprimer object.
     *
     * @param dTank tank
     */
    public final void removeObject(final DTank dTank) {
        physics.removeTank(dTank);
        getMainPanel().getContentPanel().getTabbedPane().
                getGraphicArenaPanel().getGraphicArenaListTank().loadTank();
        getMainPanel().getContentPanel().getTabbedPane().
                getGraphicArenaPanel().getGraphicArenaListTank().updateUI();
    }

    /**
     * Retourner le tank au point (x,y).
     *
     * @param x x
     * @param y y
     * @return tank
     */
    public final Tank getTankAt(final int x,
                                final int y) {
        for (Tank tank : physics.getTanks()) {
            if (tank.getCoordX() == x && tank.getCoordY() == y) {
                return tank;
            }
        }
        return null;
    }

    /**
     * Retourner l'obstacle au point (x,y).
     *
     * @param x x
     * @param y y
     * @return obs
     */
    public final Obstacle getObstacleAt(final int x,
                                        final int y) {
        for (Obstacle obs : physics.getObstacles()) {
            if (obs.getCoordX() == x && obs.getCoordY() == y) {
                return obs;
            }
        }
        return null;
    }

    /**
     * Tank est dans arene.
     *
     * @param tank tank
     * @return boolean
     */
    public final boolean tankIsIn(final Tank tank) {
        return (tank.getCoordX() >= 0 && tank.getCoordX() < physics.getLignes())
                && (tank.getCoordY() >= 0 && tank.getCoordY() < physics.getColonnes());
    }

    /**
     * Paint highlight.
     *
     * @param g graphic
     * @param x x
     * @param y y
     */
    public final void paintHighlight(final Graphics g,
                                     final int x,
                                     final int y) {
        g.setColor(Color.red);
        g.drawArc(x - 5, y - 4, PIXEL_SIZE, PIXEL_SIZE, 0, 360);
    }

    /**
     * Centrer.
     *
     * @param g graphic
     */
    public final void centrage(final Graphics g) {
        Graphics2D g2;
        g2 = (Graphics2D) g;
        int largeurAffichage = physics.getLignes() * PIXEL_SIZE,
                hauteurAffichage = physics.getColonnes() * PIXEL_SIZE;
        double diffX = this.getWidth() / (double) largeurAffichage,
                diffY = this.getHeight() / (double) hauteurAffichage;
        if (diffY > diffX) {
            g2.translate(0, Math.max(hauteurAffichage * diffX - this.getHeight(),
                    this.getHeight() - hauteurAffichage * diffX) / 2);
            g2.scale(diffX, diffX);
        } else {
            g2.translate(Math.max(largeurAffichage * diffY - this.getWidth(),
                    this.getWidth() - largeurAffichage * diffY) / 2, 0);
            g2.scale(diffY, diffY);
        }
    }

    /**
     * Paint.
     *
     * @param g graphic
     */
    @Override
    public final void paintComponent(final Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        centrage(g);
        ObjetTT contenu;
        for (int i = 0; i < physics.getLignes(); i++) {
            for (int j = 0; j < physics.getColonnes(); j++) {
                int x = i * tailleCase;
                int y = j * tailleCase;
                contenu = physics.detail(i, j);
                contenu.paint(g, x, y);
                if (highlightTank == contenu) {
                    paintHighlight(g, x, y);
                }
            }
        }
    }

    /**
     * DeleteObstacleListener.
     */
    private class DeleteObstacleButtonListener implements ActionListener {
        @Override
        public void actionPerformed(final ActionEvent e) {
            if (obsX != 0 && obsY != 0) {
                physics.getMap().erase(obsX, obsY);
                Iterator<Obstacle> iter = physics.getMap().getListeObstacles().iterator();
                while (iter.hasNext()) {
                    Obstacle t = iter.next();
                    if (t.getCoordX() == obsX && t.getCoordY() == obsY) {
                        iter.remove();
                    }
                }
                repaint();
                validate();
            }
        }
    }
}