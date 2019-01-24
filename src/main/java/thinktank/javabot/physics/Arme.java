package thinktank.javabot.physics;

/**
 * Arme.
 */
public class Arme {
    /**
     * Temps decharge.
     */
    private int tempsDecharge = 10;

    /**
     * Temps restant.
     */
    private int tempsRestant = 0;

    /**
     * Creer un projectile.
     *
     * @param x         x
     * @param y         y
     * @param direction direction
     * @param map       terrain
     * @return projectile
     */
    protected final Projectile creerProjectile(final int x,
                                               final int y,
                                               final Direction direction,
                                               final Terrain map) {
        return new Projectile(x, y, direction, map);
    }

    /**
     * Renvoie le temps de recharge de l'arme.
     *
     * @return tempsDecharge
     */
    public final int getTempsDecharge() {
        return tempsDecharge;
    }

    /**
     * Renvoie le temps de recharge restant de l'arme.
     *
     * @return tempsRestant
     */
    public final int getTempsRestant() {
        return tempsRestant;
    }

    /**
     * Initialise le temps de recharge de l'arme.
     */
    protected final void initTempsRestant() {
        this.tempsRestant = tempsDecharge;
    }

    /**
     * Reduit le temps de recharge de l'arme.
     */
    protected final void reduireTempsRestant() {
        if (tempsRestant > 0) {
            this.tempsRestant = tempsRestant - 1;
        }
    }
}
