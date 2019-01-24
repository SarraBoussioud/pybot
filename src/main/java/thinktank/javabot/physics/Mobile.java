package thinktank.javabot.physics;

/**
 * Mobile.
 */
public abstract class Mobile implements ObjetTT {
    public int getIdMob() {
        return idMob;
    }

    /**
     * Id Mobile.
     */
    private static int idMob = 0;

    /**
     * Id.
     */
    private int id;

    /**
     * x.
     */
    private int coordX;

    /**
     * y.
     */
    private int coordY;

    /**
     * Terrain.
     */
    private Terrain map;

    /**
     * Direction.
     */
    private Direction direction;

    /**
     * Latence.
     */
    private int latence = 0;

    /**
     * Verifier etat d'un tank.
     */
    private boolean mort = false;

    /**
     * Getter boolean mort.
     *
     * @return mort
     */
    public final boolean getMort() {
        return mort;
    }

    /**
     * Revive.
     */
    protected final void vivre() {
        mort = false;
    }

    /**
     * Mort.
     */
    protected final void meurt() {
        mort = true;
    }

    /**
     * Getter latence.
     *
     * @return latence
     */
    public final int getLatence() {
        return latence;
    }

    /**
     * Setter latence.
     *
     * @param l latence
     */
    protected final void setLatence(final int l) {
        this.latence = l;
    }

    /**
     * Nouveau id.
     *
     * @return integer
     */
    protected final int newId() {
        idMob++;
        return idMob - 1;
    }

    /**
     * Permet le mouvement dans le sens de la direction.
     */
    protected void avancer() {
        int oldX = coordX, oldY = coordY;
        if (map.testAndSetCase(this,
                coordX + direction.getDx(),
                coordY + direction.getDy())) {
            coordX = coordX + direction.getDx();
            coordY = coordY + direction.getDy();
            map.erase(oldX, oldY);
        }
    }

    /**
     * Permet le mouvement dans le sens de la direction.
     */
    protected void reculer() {
        int oldX = coordX, oldY = coordY;
        if (map.testAndSetCase(this,
                coordX - direction.getDx(),
                coordY - direction.getDy())) {
            coordX = coordX - direction.getDx();
            coordY = coordY - direction.getDy();
            map.erase(oldX, oldY);
        }
    }

    /**
     * Renvoie l'identifiant du mobile.
     * @return id
     */
    public final int getId() {
        return id;
    }

    /**
     * Met à jours l'identifiant.
     *
     * @param i identifiant fourni
     */
    protected final void setId(final int i) {
        this.id = i;
    }

    /**
     * Renvoie la coordonnée en absyss.
     * @return x
     */
    public final int getCoordX() {
        return coordX;
    }

    /**
     * Met à jours la coordonnée en absyss.
     *
     * @param x nouvelle coordonnée en absyss
     */
    public final void setCoordX(final int x) {
        this.coordX = x;
    }

    /**
     * Renvoie la coordonnée en ordonnée.
     * @return y
     */
    public final int getCoordY() {
        return coordY;
    }

    /**
     * Met à jours la coordonnée en absyss.
     *
     * @param y nouvelle coordonnée en ordonnée
     */
    public final void setCoordY(final int y) {
        this.coordY = y;
    }

    /**
     * Renvoie la Direction du mobile.
     * @return direction
     */
    public final Direction getDirection() {
        return direction;
    }

    /**
     * Met à jours la Direction.
     *
     * @param d nouvelle Direction
     */
    public final void setDirection(final Direction d) {
        this.direction = d;
    }

    /**
     * Renvoie le Terrain.
     * @return map
     */
    public final Terrain getMap() {
        return map;
    }

    /**
     * Met à jours le Terrain.
     *
     * @param m nouveau Terrain
     */
    protected final void setMap(final Terrain m) {
        this.map = m;
    }

    /**
     * Permet de detruire l'objet.
     */
    protected abstract void tuer();
}
