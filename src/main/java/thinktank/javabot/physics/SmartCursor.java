package thinktank.javabot.physics;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;

/**
 * SmartCursor.
 */
public class SmartCursor {
    /**
     * Tank avec direction.
     */
    private DTank movingObject;

    /**
     * Les obstacles.
     */
    private Obstacle obstacle;

    /**
     * Verifier si cursor active.
     */
    private boolean active;

    /**
     * Mur.
     */
    private Mur mur;

    /**
     * Setter obstacles.
     *
     * @param obs obstacle
     */
    public final void setObstacle(final Obstacle obs) {
        this.obstacle = obs;
        activate();
    }

    /**
     * Getter obstacle.
     *
     * @return obstacle
     */
    public final Obstacle getObstacle() {
        return obstacle;
    }

    /**
     * Setter tank.
     *
     * @param tank tank
     */
    public final void setTank(final DTank tank) {
        this.movingObject = tank;
        activate();
    }

    /**
     * Setter Mur.
     *
     * @param m mur
     */
    public final void setMur(final Mur m) {
        this.mur = m;
        activate();
    }

    /**
     * Getter tank.
     *
     * @return tank
     */
    public final DTank getTank() {
        return movingObject;
    }

    /**
     * Getter active.
     *
     * @return active
     */
    public final boolean isActive() {
        return active;
    }

    /**
     * Activer cursor.
     */
    public final void activate() {
        active = true;
    }

    /**
     * Desactiver cursor.
     */
    public final void desactivate() {
        active = false;
    }

    /**
     * Set coordonnes d'un tank.
     *
     * @param x x
     * @param y y
     */
    public final void setCoordMovingObj(
            final int x,
            final int y) {
        movingObject.setCoordX(x);
        movingObject.setCoordY(y);
    }

    /**
     * Paint.
     *
     * @param g graphic
     * @param x x
     * @param y y
     */
    public final void paint(final Graphics2D g,
                            final int x,
                            final int y) {
        Graphics2D g2 = g;
        Composite comp = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, (float) 0.5);
        g2.setComposite(comp);
        movingObject.paint(g2, x, y);
    }
}

