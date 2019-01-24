package thinktank.javabot.physics;

import java.awt.Graphics;

/**
 * Objet.
 */
public interface ObjetTT {
    /**
     * Type physique.
     *
     * @return type
     */
    Physique.TypeP getType();

    /**
     * Paint.
     *
     * @param g graphic
     * @param x x
     * @param y y
     */
    void paint(Graphics g, int x, int y);
}
