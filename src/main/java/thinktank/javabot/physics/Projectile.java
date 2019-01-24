package thinktank.javabot.physics;

import java.awt.Graphics;
import java.awt.Image;

import thinktank.javabot.graphics.GraphicArena;
import thinktank.javabot.graphics.ImageLoader;

/**
 * Projectile.
 */
public class Projectile extends Mobile {
    /**
     * Degat.
     */
    private int degatsProjectile = 20;

    /**
     * Nombre d'iter a attendre avant d'avancer.
     */
    private int vitesseProjectile = 0;

    /**
     * IdMort.
     */
    private static int idMort = -1;

    /**
     * Sprite.
     */
    private Image sprite;

    /**
     * Background Sprite.
     */
    private Image backgroundSprite;

    /**
     * Getter idMort.
     *
     * @return idMort
     */
    protected static int getIdMort() {
        return idMort;
    }

    /**
     * Initialiser idMort.
     */
    protected static void initIdMort() {
        idMort = -1;
    }

    /**
     * Setter idMort.
     *
     * @param id idMort
     */
    protected static void setIdMort(final int id) {
        Projectile.idMort = id;
    }

    /**
     * Constructor.
     *
     * @param x         x
     * @param y         y
     * @param direction direction
     * @param map       terrain
     */
    protected Projectile(final int x,
                         final int y,
                         final Direction direction,
                         final Terrain map) {
        setId(newId());
        setMap(map);
        setCoordX(x);
        setCoordY(y);
        setDirection(direction);
        sprite = GraphicArena.imgLoader.getSprite(
                ImageLoader.SpriteName.MISSILE.ordinal());
        backgroundSprite = GraphicArena.imgLoader.getSprite(
                ImageLoader.SpriteName.SOL.ordinal());
    }

    /**
     * Constructor.
     *
     * @param x         x
     * @param y         y
     * @param direction direction
     * @param map       terrain
     * @param dmg       degat
     * @param vitesse   vitesse
     */
    protected Projectile(final int x,
                         final int y,
                         final Direction direction,
                         final Terrain map,
                         final int dmg,
                         final int vitesse) {
        setId(newId());
        setMap(map);
        setCoordX(x);
        setCoordY(y);
        setDirection(direction);
        setDegatsProjectile(dmg);
    }

    /**
     * Avancer.
     */
    protected final void avancer() {
        if (getLatence() > 0) {
            setLatence(getLatence() - 1);
            return;
        }
        int x = getCoordX();
        int y = getCoordY();
        super.avancer();
        if (x != getCoordX() || y != getCoordY()) {
            setLatence(vitesseProjectile);
        }
    }

    /**
     * Getter degat du projectile.
     *
     * @return degat
     */
    public final int getDegatsProjectile() {
        return degatsProjectile;
    }

    /**
     * mMet à jours les degats du Projectile.
     *
     * @param dmg nouveaux degats du Projectile
     */
    protected final void setDegatsProjectile(final int dmg) {
        this.degatsProjectile = dmg;
    }

    /**
     * Renvoie le typePhysique projectile.
     *
     * @return type physique
     */
    @Override
    public final Physique.TypeP getType() {
        return Physique.TypeP.projectile;
    }

    /**
     * Tuer.
     */
    protected final void tuer() {
        getMap().erase(getCoordX(), getCoordY());
        meurt();
        getMap().removeProjectile(this);
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
        g.drawImage(backgroundSprite, x, y, null);
        g.drawImage(sprite, x, y, null);
    }
}
