package thinktank.javabot.physics;

/**
 * Classe permettant de connaitre et changer
 * la direction d'un mobile (sous forme de vecteur).
 */
public class Direction {
    /**
     * dx.
     */
    private int dx;

    /**
     * dy.
     */
    private int dy;

    /**
     * Constructor.
     * @param x dx
     * @param y dy
     */
    public Direction(final int x,
                     final int y) {
        this.dx = x;
        this.dy = y;
    }

    /**
     * Renvoie dx.
     * @return dx
     */
    public final int getDx() {
        return dx;
    }

    /**
     * Renvoie dy.
     * @return dy
     */
    public final int getDy() {
        return dy;
    }

    /**
     * Permet la rotation droite d'un mobile.
     */
    protected final void tournerDroite() {
        int tmp = dx;
        dx = dx * 0 + dy * -1;
        dy = tmp * 1 + dy * 0;
    }

    /**
     * Permet la rotation gauche d'un mobile.
     */
    protected final void tournerGauche() {
        int tmp = dx;
        dx = dx * 0 + dy * 1;
        dy = tmp * -1 + dy * 0;
    }
}
