package thinktank.javabot.fileManagement;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import demaciatanks.scripteditor.ScriptEditor;
import demaciatanks.swinginterface.FileBoxesScrollPane;
import demaciatanks.swinginterface.NewButtonPanel;
import demaciatanks.swinginterface.ColorTankFrame;
import thinktank.javabot.graphics.ImageLoader;
import thinktank.javabot.physics.DTank;
import thinktank.javabot.physics.SmartCursor;
import thinktank.javabot.physics.Tank;

/**
 * FileBoxManager.
 */
public class FileBoxManager extends JPanel {
    /**
     * Serial.
     */
    private static final long serialVersionUID = -4841135013487590132L;

    /**
     * Le repertoire scripts.
     */
    private static final String DIRECTORY_PATH = "scripts";

    /**
     * Image Loader.
     */
    private ImageLoader imageLoader;

    /**
     * Smart cursor.
     */
    private SmartCursor cursor;

    /**
     * Script editor.
     */
    private ScriptEditor editor;

    /**
     * FileBoxesScrollPane.
     */
    private FileBoxesScrollPane scrollpane;

    /**
     * NewButtonPanel.
     */
    private NewButtonPanel newButtonPanel;

    /**
     * Le fichier selectionne.
     */
    private File selected = null;

    /**
     * La liste des FileBox.
     */
    private ArrayList<FileBox> listFileBox = new ArrayList<>();

    /**
     * Nombre max des filebox.
     */
    private static final int NB_MAX_FILEBOX = 5;

    /**
     * Constructor.
     *
     * @param img        imageLoader
     * @param c          cursor
     * @param e          editor
     * @param newButton  newButton
     * @param scrollPane scrollPane
     */
    public FileBoxManager(final ImageLoader img,
                          final SmartCursor c,
                          final ScriptEditor e,
                          final NewButtonPanel newButton,
                          final FileBoxesScrollPane scrollPane) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.imageLoader = img;
        this.cursor = c;
        this.editor = e;
        this.newButtonPanel = newButton;
        this.scrollpane = scrollPane;
        loadFiles();
        newButtonPanel.getNewFileButton().addActionListener(
                new NewFileButtonListener());
        newButtonPanel.getRmButton().addActionListener(
                new RemoveButtonListener());
    }

    /**
     * Getter liste des FileBox.
     *
     * @return listFileBox
     */
    public final ArrayList<FileBox> getListFileBox() {
        return listFileBox;
    }

    /**
     * Rafraichir les FileBox.
     */
    public final void loadFiles() {
        FileExplorer.checkFor(DIRECTORY_PATH);
        List<File> filesInFolder = FileExplorer.getListFile(
                DIRECTORY_PATH, ".py");
        this.removeAll();
        listFileBox = new ArrayList<>();
        BufferedReader lecteurAvecBuffer = null;
        String ligne;
        Color c = null;
        /*
         * Creation du fichier Couleur contient toutes les couleurs des tanks
         */
        File couleur = new File("ressources/couleurs/couleur");
        /*
         * Verifier si le fichier existe deja
		 */
        if (!couleur.exists()) {
            FileExplorer.createFile("ressources/couleurs", "couleur", "");
        } else {
            try {
                lecteurAvecBuffer = new BufferedReader(
                        new FileReader("ressources/couleurs/couleur"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            for (File file : filesInFolder) {
                /**
                 * Lire la ligne qui contient la couleur du tank
                 */
                try {
                    ligne = lecteurAvecBuffer.readLine();
                    if (ligne != null) {
                        c = generateColorFromString(ligne);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                /*
                 * Ajouter le FileBox
				 */
                JPanel panel = new JPanel();
                panel.setPreferredSize(new Dimension(200, this.getWidth()));
                panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
                panel.setAlignmentX(0);
                FileBox fb = new FileBox(file, imageLoader, cursor, editor, c);
                listFileBox.add(fb);
                panel.add(fb);
                /*
                 * Ajouter son image
				 */
                ImageIcon image = new ImageIcon(fb.getTank().
                        getSprite()[DTank.Orientation.TANKB.ordinal()]);
                JLabel label = new JLabel("", image, JLabel.CENTER);
                panel.add(label);
                /*
                 * Ajouter nbVie
				 */
                if (editor.getContentTabbedPane() != null) {
                    if (editor.getContentTabbedPane().
                            getGraphicArenaPanel().getGraphicArena().
                            getPhysics().getMap().getListAllTank() != null) {
                        for (Tank tank : editor.getContentTabbedPane().
                                getGraphicArenaPanel().getGraphicArena().
                                getPhysics().getMap().getListAllTank()) {
                            if (fb.getTank().getColor()
                                    == ((DTank) tank).getColor()) {
                                fb.getTank().setNbVie(tank.getNbVie());
                            }
                        }
                    }
                }
                /*
                    Recuperer le nombre de vie d'un tank
				 */
                int vie = fb.getTank().getNbVie();
                ImageIcon nbVie = null;
                switch (vie) {
                    case 1:
                        nbVie = new ImageIcon("ressources/images/nbVie1.png");
                        break;
                    case 2:
                        nbVie = new ImageIcon("ressources/images/nbVie2.png");
                        break;
                    case 3:
                        nbVie = new ImageIcon("ressources/images/nbVie3.png");
                        break;
                    case 4:
                        nbVie = new ImageIcon("ressources/images/nbVie4.png");
                        break;
                    case 5:
                        nbVie = new ImageIcon("ressources/images/nbVie5.png");
                        break;
                    default:
                        break;
                }
                JLabel labelVie = new JLabel("", nbVie, JLabel.CENTER);
                panel.add(labelVie);
                this.add(panel);
            }
            /*
             * Fermer le fichier
			 */
            try {
                lecteurAvecBuffer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Retourner la couleur d'un tank.
     *
     * @param file fichier
     * @return color
     */
    public final Color generateColor(final File file) {
        if (("ressources/images/TankBBLUE.png".
                equals(file.toString()))
                || ("ressources\\images\\TankBBLUE.png".
                equals(file.toString()))) {
            return Color.BLUE;
        } else if (("ressources/images/TankBROSE.png".
                equals(file.toString()))
                || ("ressources\\images\\TankBROSE.png".
                equals(file.toString()))) {
            return Color.PINK;
        } else if (("ressources/images/TankBRED.png".
                equals(file.toString()))
                || ("ressources\\images\\TankBRED.png".
                equals(file.toString()))) {
            return Color.RED;
        } else if (("ressources/images/TankBJAUNE.png".
                equals(file.toString()))
                || ("ressources\\images\\TankBJAUNE.png".
                equals(file.toString()))) {
            return Color.YELLOW;
        } else if (("ressources/images/TankBVERT.png".
                equals(file.toString()))
                || ("ressources\\images\\TankBVERT.png".
                equals(file.toString()))) {
            return Color.GREEN;
        } else if (("ressources/images/TankBCYAN.png".
                equals(file.toString()))
                || ("ressources\\images\\TankBCYAN.png".
                equals(file.toString()))) {
            return Color.CYAN;
        } else if (("ressources/images/TankBVIOLET.png".
                equals(file.toString()))
                || ("ressources\\images\\TankBVIOLET.png".
                equals(file.toString()))) {
            return Color.MAGENTA;
        } else if (("ressources/images/TankBGRIS.png".
                equals(file.toString()))
                || ("ressources\\images\\TankBGRIS.png".
                equals(file.toString()))) {
            return Color.GRAY;
        }
        return new Color(172, 162, 64);
    }

    /**
     * Retourner la couleur a partir d'un string.
     *
     * @param s string
     * @return color
     */
    public final Color generateColorFromString(final String s) {
        if ("BLUE".equals(s)) {
            return Color.BLUE;
        } else if ("JAUNE".equals(s)) {
            return Color.YELLOW;
        } else if ("RED".equals(s)) {
            return Color.RED;
        } else if ("ROSE".equals(s)) {
            return Color.PINK;
        } else if ("VERT".equals(s)) {
            return Color.GREEN;
        } else if ("CYAN".equals(s)) {
            return Color.CYAN;
        } else if ("VIOLET".equals(s)) {
            return Color.MAGENTA;
        } else if ("GRIS".equals(s)) {
            return Color.GRAY;
        }
        return new Color(172, 162, 64);
    }

    /**
     * Retourner le nom de la couleur c.
     *
     * @param c color
     * @return string
     */
    public final String getColorName(final Color c) {
        if (Color.BLUE.equals(c)) {
            return "BLUE";
        } else if (Color.YELLOW.equals(c)) {
            return "JAUNE";
        } else if (Color.RED.equals(c)) {
            return "RED";
        } else if (Color.PINK.equals(c)) {
            return "ROSE";
        } else if (Color.GREEN.equals(c)) {
            return "VERT";
        } else if (Color.CYAN.equals(c)) {
            return "CYAN";
        } else if (Color.MAGENTA.equals(c)) {
            return "VIOLET";
        } else if (Color.GRAY.equals(c)) {
            return "GRIS";
        }
        return "BRUN";
    }

    /**
     * Listener quand on clique sur le bouton Creer nouveau.
     */
    public class NewFileButtonListener implements ActionListener {
        @Override
        public final void actionPerformed(final ActionEvent e) {
            int nbImagesTank; //Nombre des images des tanks
            int nb = 0;
            /*
                Limiter le nombre de scripts a 5
             */
            List<File> filesInFolder = FileExplorer.getListFile(
                    DIRECTORY_PATH, ".py");
            if (filesInFolder.size() < NB_MAX_FILEBOX) {
                /*
                    Main window
                 */
                final ColorTankFrame frame = new ColorTankFrame();
                /*
                    Liste des tanks avec toutes les couleurs
                 */
                List<File> listTank = FileExplorer.getListFile(
                        "ressources/images", ".png");
                nbImagesTank = listTank.size();
                final JLabel[] label = new JLabel[nbImagesTank];
                /*
                    Liste des tanks dispo
                 */
                List<File> listTankDispo = new ArrayList<>(listTank);
                for (FileBox fb : listFileBox) {
                    listTankDispo.remove((new File(
                            "ressources/images/TankB"
                                    + fb.getTank().getColorName(
                                    fb.getTank().getColor()).
                                    toUpperCase() + ".png")));
                }
                /*
                    Ajouter le bouton Valider
                 */
                final JButton valide = new JButton("Valider");
                valide.setEnabled(false);
                /*
                    Ajouter les tanks dispo dans la fenetre de choix
                 */
                for (final File file : listTankDispo) {
                    if (file.toString().toLowerCase().contains("tankb")) {
                        try {
                            ImageIcon image = new ImageIcon(ImageIO.read(file));
                            label[nb] = new JLabel("", image, JLabel.CENTER);
                            label[nb].addMouseListener(new MouseListener() {
                                @Override
                                public final void mouseClicked(
                                        final MouseEvent e) {
                                    selected = file;
                                    for (int i = 0; i < label.length; i++) {
                                        if (label[i] != null) {
                                            label[i].setBorder(
                                                    new EmptyBorder(0, 0, 0, 0));
                                        }
                                    }
                                    ((JLabel) e.getComponent()).setBorder(
                                            new BevelBorder(BevelBorder.RAISED));
                                    valide.setEnabled(true);
                                }

                                @Override
                                public final void mousePressed(
                                        final MouseEvent e) {
                                }

                                @Override
                                public final void mouseReleased(
                                        final MouseEvent e) {
                                }

                                @Override
                                public final void mouseEntered(
                                        final MouseEvent e) {
                                }

                                @Override
                                public final void mouseExited(
                                        final MouseEvent e) {
                                }
                            });
                            frame.add(label[nb]);
                            nb++;
                        } catch (IOException ex) {
                            System.out.println(
                                    "Probleme d'ouverture des images.");
                        }
                    }
                }
                frame.add(valide);
                valide.addActionListener(new ActionListener() {
                    @Override
                    public final void actionPerformed(
                            final ActionEvent e) {
                        addFileBox();
                        frame.dispose();
                    }
                });
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Le nombre limit des scripts ne peut pas superieur a 5");
            }
        }
    }

    /**
     * Listener quand on clique sur le bouton Supprimer.
     */
    public class RemoveButtonListener implements ActionListener {
        @Override
        public final void actionPerformed(final ActionEvent e) {
            try {
                removeBox();
            } catch (IOException ex) {
            }
        }
    }

    /**
     * Generer le nom du fichier.
     *
     * @return nom du fichier
     */
    public final String generateFileName() {
        String basePath = "scripts/new_script_";
        String pathEnd = ".py";
        int counter = 0;
        while (new File(basePath + counter + pathEnd).exists()) {
            counter++;
        }
        return basePath + counter + pathEnd;
    }

    /**
     * Supprimer les FileBox.
     *
     * @throws IOException exception
     */
    public final void removeBox() throws IOException {
        editor.getContentTabbedPane().getGraphicArenaPanel().
                getGraphicArena().validate();
        editor.getContentTabbedPane().getGraphicArenaPanel().
                getGraphicArena().repaint();
        if (!editor.getContentTabbedPane().getGraphicArenaPanel().
                getGraphicArena().getPhysics().
                getMap().getTanks().isEmpty()) {
            for (Iterator<Tank> iter =
                 editor.getContentTabbedPane().getGraphicArenaPanel().
                         getGraphicArena().getPhysics().getMap().
                         getTanks().listIterator(); iter.hasNext();) {
                while (iter.hasNext()) {
                    Tank tank = iter.next();
                    if (Objects.equals(
                            tank.getScript(), editor.getFile().getPath())) {
                        tank.getMap().erase(tank.getCoordX(), tank.getCoordY());
                        iter.remove();
                    }
                }
            }
        }
        if (editor.getContentTabbedPane().getGraphicArenaPanel().
                getGraphicArena().getPhysics().getMap().
                getTanks().isEmpty()) {
            editor.getContentTabbedPane().getGraphicArenaPanel().
                    getGraphicArenaToolsPanel().getStartButton().
                    setEnabled(false);
        }
        List<File> filesInFolder = FileExplorer.getListFile(
                DIRECTORY_PATH, ".py");
        String ligne;
        File orig = new File("ressources/couleurs/couleur");
        File tmp = new File(orig + ".tmp");
        /*
            Ouvrir le fichier Couleur
         */
        BufferedReader in = new BufferedReader(new FileReader(orig));
        /*
            Creer un fichier temporaire tmp
         */
        PrintWriter pw = new PrintWriter(new BufferedWriter(
                new FileWriter(tmp, true)));
        for (File file : filesInFolder) {
            /*
                Si c'est le bon fichier on skip la ligne (pour effacer)
             */
            if (file.equals(editor.getFile())) {
                in.readLine();
                FileExplorer.deleteFile(editor.getFile().getPath());
            } else {
                ligne = in.readLine();
                if (ligne != null) {
                    pw.print(ligne);
                }
                pw.println();
            }
        }
        //Fermer le fichier
        pw.close();
        in.close();
        //Remplacer le fichier Couleur par le fichier tmp
        if (orig.delete()) {
            tmp.renameTo(orig);
        } else {
            JOptionPane.showMessageDialog(null, "Cannot delete script");
        }
        //Retourner a l'image de l'arene si c'est pas le cas
        if (editor.getContentTabbedPane().getSelectedIndex() == 1) {
            editor.getContentTabbedPane().setSelectedIndex(0);
        }
        cursor.setTank(null);
        /*
            Rafraichir
         */
        loadFiles();
        editor.getContentTabbedPane().getGraphicArenaPanel().
                getGraphicArena().validate();
        editor.getContentTabbedPane().getGraphicArenaPanel().
                getGraphicArena().repaint();
        scrollpane.validate();
        scrollpane.repaint();
        editor.getContentTabbedPane().getGraphicArenaPanel().
                getGraphicArenaListTank().loadTank();
        editor.getContentTabbedPane().getGraphicArenaPanel().
                getGraphicArenaListTank().updateUI();
    }

    /**
     * Ajouter un nouveau FileBox.
     */
    public final void addFileBox() {
        String newName = generateFileName();
        FileExplorer.checkFor("ressources/couleurs");
        FileExplorer.createFile(newName, "while True:");
        File couleur = new File("ressources/couleurs/couleur");
        if (!couleur.exists()) {
            FileExplorer.createFile(
                    "ressources/couleurs", "couleur",
                    getColorName(generateColor(selected)) + "\r\n");
        } else {
            try {
                PrintWriter writer = new PrintWriter(new BufferedWriter(
                        new FileWriter(
                                "ressources/couleurs/couleur", true)));
                //Ajouter dans le fichier Couleur la couleur du tank ajoute'
                writer.print(getColorName(generateColor(selected)));
                writer.println();
                writer.close();
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        loadFiles();
        scrollpane.validate();
        scrollpane.repaint();
    }
}
