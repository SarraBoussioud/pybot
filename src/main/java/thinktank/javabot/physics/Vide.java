package thinktank.javabot.physics;

import java.awt.Graphics;
import java.awt.Image;

import thinktank.javabot.graphics.GraphicArena;
import thinktank.javabot.graphics.ImageLoader;

/**
 * Vide.
 */
public class Vide implements ObjetTT {
    /**
     * Vide.
     */
    private static Vide v = new Vide(GraphicArena.imgLoader);

    /**
     * Sprite.
     */
    private Image sprite;

    /**
     * Constructor.
     *
     * @param img imageLoader
     */
    public Vide(final ImageLoader img) {
        sprite = img.getSprite(ImageLoader.SpriteName.SOL.ordinal());
    }

    /**
     * Getter vide.
     *
     * @return vide
     */
    public static Vide getVide() {
        return v;
    }

    /**
     * Setter vide.
     *
     * @param imageLoader image
     * @return vide
     */
    public static Vide setVide(final ImageLoader imageLoader) {
        Vide vv = new Vide(imageLoader);
        v = vv;
        return v;
    }

    /**
     * Getter Type.
     *
     * @return type physique
     */
    public final Physique.TypeP getType() {
        return Physique.TypeP.vide;
    }

    /**
     * Paint.
     *
     * @param g graphic
     * @param x x
     * @param y y
     */
    @Override
    public final void paint(final Graphics g,
                            final int x,
                            final int y) {
        g.drawImage(sprite, x, y, null);
    }
}
