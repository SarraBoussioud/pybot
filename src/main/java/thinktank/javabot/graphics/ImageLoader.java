package thinktank.javabot.graphics;

import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Image Loader.
 */
public class ImageLoader {
    /**
     * Repertoire contient les images.
     */
    public static final String IMAGES_FOLDER = "ressources/images/";
    /**
     * Tableau contient les images.
     */
    private BufferedImage[] img = new BufferedImage[11];

    /**
     * Enum.
     */
    public enum SpriteName {
        MUR,
        MISSILE,
        SOL,
        HIGHLIGHT,
        OBSTACLE1,
        OBSTACLE2,
        OBSTACLE3
    }

    /**
     * Constructor.
     */
    public ImageLoader() {
        try {
            img[SpriteName.MUR.ordinal()] =
                    toBufferedImage(ImageIO.read(new File(IMAGES_FOLDER + "mur.png")));
            img[SpriteName.MISSILE.ordinal()] =
                    toBufferedImage(ImageIO.read(new File(IMAGES_FOLDER + "missile.png")));
            img[SpriteName.SOL.ordinal()] =
                    toBufferedImage(ImageIO.read(new File(IMAGES_FOLDER + "sol.png")));
            img[SpriteName.HIGHLIGHT.ordinal()] =
                    toBufferedImage(ImageIO.read(new File(IMAGES_FOLDER + "select.png")));
            img[SpriteName.OBSTACLE1.ordinal()] =
                    toBufferedImage(ImageIO.read(new File(IMAGES_FOLDER + "mur3.png")));
            img[SpriteName.OBSTACLE2.ordinal()] =
                    toBufferedImage(ImageIO.read(new File(IMAGES_FOLDER + "mur4.png")));
            img[SpriteName.OBSTACLE3.ordinal()] =
                    toBufferedImage(ImageIO.read(new File(IMAGES_FOLDER + "mur5.png")));
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
            return "Blue";
        } else if (Color.YELLOW.equals(c)) {
            return "Jaune";
        } else if (Color.RED.equals(c)) {
            return "Red";
        } else if (Color.PINK.equals(c)) {
            return "Rose";
        } else if (Color.GREEN.equals(c)) {
            return "Vert";
        }
        return null;
    }

    /**
     * Transformer image a bufferedimage.
     *
     * @param img imageLoader
     * @return bufferedImage
     */
    public static BufferedImage toBufferedImage(final Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bimage = new BufferedImage(
                img.getWidth(null),
                img.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }

    /**
     * Getter Sprite.
     *
     * @param sprite sprite
     * @return sprite
     */
    public final BufferedImage getSprite(final int sprite) {
        return img[sprite];
    }

    /**
     * Getter BufferedImage.
     *
     * @return img
     */
    public final BufferedImage[] getImg() {
        return img;
    }

}
