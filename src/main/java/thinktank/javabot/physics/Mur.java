package thinktank.javabot.physics;

import java.awt.Graphics;
import java.awt.Image;

import thinktank.javabot.graphics.GraphicArena;
import thinktank.javabot.graphics.ImageLoader;

/**
 * Mur.
 */
public class Mur implements ObjetTT {
    /**
     * Mur.
     */
    private static Mur mur = new Mur(GraphicArena.imgLoader);

    /**
     * Sprite.
     */
    private Image sprite;

    /**
     * Constructor.
     *
     * @param img imageLoader
     */
    public Mur(final ImageLoader img) {
        sprite = img.getSprite(ImageLoader.SpriteName.MUR.ordinal());
    }

    /**
     * Renvoie le Mur de la classe.
     * @return mur
     */
    public final static Mur getMur() {
        return mur;
    }

    /**
     * Renvoie un typePhysique mur.
     * @return type physique
     */
    public final Physique.TypeP getType() {
        return Physique.TypeP.mur;
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
        g.drawImage(sprite, x, y, null);
    }
}
