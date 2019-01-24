package thinktank.javabot.physics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import thinktank.javabot.graphics.ImageLoader;

import javax.imageio.ImageIO;

import static thinktank.javabot.graphics.ImageLoader.toBufferedImage;

/**
 * Tank avec direction.
 */
public class DTank extends Tank {
    /**
     * Couleur du tank.
     */
    private Color color;

    /**
     * Chemin de script.
     */
    private String scriptPath;

    /**
     * Sprite.
     */
    private Image[] sprite = new Image[4];

    /**
     * Background sprite.
     */
    private Image backgroundSprite;

    /**
     * Enum.
     */
    public enum Orientation {
        TANKH,
        TANKB,
        TANKD,
        TANKG
    }

    /**
     * Constructor.
     *
     * @param map      map
     * @param filepath fp
     * @param c        color
     * @param sp       scriptPath
     * @param img      img
     */
    public DTank(final Terrain map,
                 final String filepath,
                 final Color c,
                 final String sp,
                 final ImageLoader img) {
        super(map, filepath);
        this.scriptPath = sp;
        this.color = c;
        loadSprite(img);
    }

    /**
     * Constructor.
     *
     * @param map      map
     * @param filepath fp
     * @param c        color
     * @param sp       scriptPath
     * @param img      img
     * @param physique physique
     */
    public DTank(final Terrain map,
                 final String filepath,
                 final Color c,
                 final String sp,
                 final ImageLoader img,
                 final Physique physique) {
        super(map, filepath, physique);
        this.scriptPath = sp;
        this.color = c;
        loadSprite(img);
    }

    /**
     * Load sprite.
     *
     * @param img imgLoader
     */
    public final void loadSprite(final ImageLoader img) {
        try {
            sprite[Orientation.TANKH.ordinal()] =
                    toBufferedImage(ImageIO.read(new File(
                            "ressources/images/TankH"
                                    + getColorName(color) + ".png")));
            sprite[Orientation.TANKB.ordinal()] =
                    toBufferedImage(ImageIO.read(
                            new File("ressources/images/TankB"
                                    + getColorName(color) + ".png")));
            sprite[Orientation.TANKD.ordinal()] =
                    toBufferedImage(ImageIO.read(new File(
                            "ressources/images/TankD"
                                    + getColorName(color) + ".png")));
            sprite[Orientation.TANKG.ordinal()] =
                    toBufferedImage(ImageIO.read(new File(
                            "ressources/images/TankG"
                                    + getColorName(color) + ".png")));
            backgroundSprite = img.getSprite(
                    ImageLoader.SpriteName.SOL.ordinal());
        } catch (IOException e) {
            System.out.println("Probleme d'ouverture des images.");
        }
    }

    /**
     * Retourner le nom de la couleur c.
     *
     * @param c color
     * @return nom de la couleur
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
     * Getter Sprite.
     *
     * @return sprite
     */
    public final Image[] getSprite() {
        return sprite;
    }

    /**
     * Getter BackgroundSprite.
     *
     * @return backgroundSprite
     */
    public final Image getBackgroundSprite() {
        return backgroundSprite;
    }

    /**
     * Getter color.
     *
     * @return color
     */
    public final Color getColor() {
        return color;
    }

    /**
     * Getter scriptpath.
     *
     * @return chemin de script
     */
    public final String getScriptPath() {
        return scriptPath;
    }

    /**
     * Getter enum direction.
     *
     * @return integer
     */
    public final int getEnumDirection() {
        if (this.getDirection().getDx() == 1) {
            return Orientation.TANKD.ordinal();
        } else if (this.getDirection().getDx() == -1) {
            return Orientation.TANKG.ordinal();
        } else if (this.getDirection().getDy() == -1) {
            return Orientation.TANKH.ordinal();
        } else {
            return Orientation.TANKB.ordinal();
        }
    }

    /**
     * Paint.
     *
     * @param g graphic
     * @param x x
     * @param y y
     */
    public final void paint(final Graphics g,
                            final int x,
                            final int y) {
        g.drawImage(backgroundSprite, x, y, null);
        g.drawImage(sprite[getEnumDirection()], x, y, null);
    }
}

