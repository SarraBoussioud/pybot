package thinktank.javabot.physics;

import java.awt.Graphics;
import java.util.Random;

import thinktank.javabot.intelligences.Action;
import thinktank.javabot.intelligences.Intelligence;
import thinktank.javabot.intelligences.Intelligences;
import thinktank.javabot.sensors.DetectionLigneDroite;
import thinktank.javabot.sensors.Sensors;

/**
 * Tank.
 */
public class Tank extends Mobile {
    /**
     * Arme.
     */
    private Arme arme;

    /**
     * Point de vie du tank.
     */
    private int pointsDeVie = 100;

    /**
     * Nombre de vie du tank.
     */
    private int nbVie = 5;

    /**
     * IA.
     */
    private Intelligence ia;

    /**
     * Intelligences.
     */
    private static Intelligences intels = new Intelligences();

    /**
     * Sensor.
     */
    private Sensors sensor;

    /**
     * Filepath.
     */
    private String filepath;

    /**
     * Boolean timer du tank.
     */
    private boolean timerRunning;

    /**
     * Si le timer est en cours.
     *
     * @return timerRunning l'etat du Timer
     */
    public final boolean isTimerRunning() {
        return timerRunning;
    }

    /**
     * Changer l'etat du timer.
     *
     * @param tR boolean
     */
    public final void setTimerRunning(final boolean tR) {
        this.timerRunning = tR;
    }

    /**
     * Setter point de vie.
     *
     * @param pv point de vie
     */
    public final void setPointsDeVie(final int pv) {
        this.pointsDeVie = pv;
    }

    /**
     * Getter nombre de vie.
     *
     * @return nombre de vie
     */
    public final int getNbVie() {
        return nbVie;
    }

    /**
     * Setter nombre de vie.
     *
     * @param nbV nombre de vie
     */
    public final void setNbVie(final int nbV) {
        this.nbVie = nbV;
    }

    /**
     * Diminuer le nombre de vie.
     */
    public final void diminuerNbVie() {
        this.nbVie--;
    }

    /**
     * Getter sensor.
     *
     * @return sensor
     */
    public final Sensors getSensor() {
        return sensor;
    }

    /**
     * Getter script.
     *
     * @return script
     */
    public final String getScript() {
        return filepath;
    }

    /**
     * Setter sensor.
     *
     * @param s sensor
     */
    public final void setSensor(final Sensors s) {
        this.sensor = s;
    }

    /**
     * Intelligences.
     *
     * @return intel
     */
    public static Intelligences getIntels() {
        return intels;
    }

    /**
     * Gerer un nombre aleatoire entre borne inferieur
     * et borne superieure.
     *
     * @param valeurMin borne inferieure
     * @param valeurMax borne superieure
     * @return nombre aleatoire
     */
    private int alea(final int valeurMin,
                     final int valeurMax) {
        Random r = new Random();
        return valeurMin + r.nextInt(valeurMax - valeurMin);
    }

    /**
     * Constructor.
     *
     * @param map terrain
     */
    protected Tank(final Terrain map) {
        setId(newId());
        while (!map.estLibre(getCoordX(), getCoordY())) {
            setCoordX(alea(1, map.tailleX() - 1));
            setCoordY(alea(1, map.tailleY() - 1));
        }
        arme = new Arme();
        setArme(arme);
        setMap(map);
        setDirection(new Direction(0, 1));
        ia = intels.newIntelligence("ressources/tank1.py", this);
        ia.initialize();
    }

    /**
     * Constructor.
     *
     * @param map terrain
     * @param fp  chemin de fichier
     */
    protected Tank(final Terrain map,
                   final String fp) {
        if (map == null) {
            return;
        }

        setId(newId());
        while (!map.estLibre(getCoordX(), getCoordY())) {
            setCoordX(alea(1, map.tailleX() - 1));
            setCoordY(alea(1, map.tailleY() - 1));
        }

        arme = new Arme();
        setArme(arme);
        setMap(map);
        setDirection(new Direction(0, 1));
        ia = intels.newIntelligence(fp, this);
        ia.initialize();
        this.filepath = fp;
    }

    /**
     * Constructor.
     *
     * @param x   x
     * @param y   y
     * @param map terrain
     */
    protected Tank(final int x,
                   final int y,
                   final Terrain map) {
        setId(newId());

        setCoordX(x);
        setCoordY(y);

        arme = new Arme();
        setArme(arme);
        setMap(map);
        setDirection(new Direction(0, 1));
        ia = intels.newIntelligence("ressources/tank1.py", this);
        ia.initialize();

    }

    /**
     * Constructor.
     *
     * @param map      terrain
     * @param fp       chemin du fichier
     * @param physique physique
     */
    protected Tank(final Terrain map,
                   final String fp,
                   final Physique physique) {
        if (map == null) {
            return;
        }

        setId(newId());
        while (!map.estLibre(getCoordX(), getCoordY())) {
            setCoordX(alea(1, map.tailleX() - 1));
            setCoordY(alea(1, map.tailleY() - 1));
        }

        arme = new Arme();
        setArme(arme);
        setMap(map);
        setDirection(new Direction(0, 1));
        ia = intels.newIntelligence(fp, this);
        ia.initialize();
        this.filepath = fp;
        sensor = new DetectionLigneDroite(this, physique);
    }

    /**
     * Constructor.
     *
     * @param x        x
     * @param y        y
     * @param map      terrain
     * @param fp       chemin du fichier
     * @param physique physique
     */
    protected Tank(final int x,
                   final int y,
                   final Terrain map,
                   final String fp,
                   final Physique physique) {
        setId(newId());

        setCoordX(x);
        setCoordY(y);

        arme = new Arme();
        setArme(arme);
        setMap(map);
        setDirection(new Direction(0, 1));
        ia = intels.newIntelligence(fp, this);
        ia.start();
        sensor = new DetectionLigneDroite(this, physique);
    }

    /**
     * Reduire le temps de recharge de l'arme.
     */
    protected final void reduireTempsRestant() {
        arme.reduireTempsRestant();
    }

    /**
     * Renvoie l'arme équipée sur le tank.
     *
     * @return arme
     */
    public final Arme getArme() {
        return arme;
    }

    /**
     * Met à jours l'arme équipée.
     *
     * @param a nouvelle arme
     */
    protected final void setArme(final Arme a) {
        this.arme = a;
    }

    /**
     * Crée un Projectile devant le tank,
     * ou reduit le temps de rechage de l'arme.
     */
    protected final void tirer() {
        if (getMap().estLibre(getCoordX() + getDirection().getDx(),
                getCoordY() + getDirection().getDy())
                && arme.getTempsRestant() <= 0) {
            Direction d = new Direction(
                    getDirection().getDx(), getDirection().getDy());
            Projectile p = arme.creerProjectile(getCoordX()
                    + getDirection().getDx(), getCoordY()
                    + getDirection().getDy(), d, getMap());
            getMap().addProjectile(p);
            arme.initTempsRestant();
        } else {
            arme.reduireTempsRestant();
        }
    }

    /**
     * Renvoie les points de vie du tank.
     *
     * @return point de vie
     */
    public final int getPointsDeVie() {
        return pointsDeVie;
    }

    /**
     * Reduit les points de vies du tank de "dommage".
     *
     * @param dommage degats subit par le tank
     */
    protected final void subit(final int dommage) {
        this.pointsDeVie = pointsDeVie - dommage;
        if (pointsDeVie <= 0) {
            pointsDeVie = 0;
            meurt();
            tuer();
        }
    }

    /**
     * Renvoie le typePhysique tank.
     *
     * @return type physique
     */
    public final Physique.TypeP getType() {
        return Physique.TypeP.tank;
    }

    /**
     * Tuer.
     */
    protected final void tuer() {
        getMap().erase(getCoordX(), getCoordY());
        this.diminuerNbVie();
        if (this.getNbVie() != 0) {
            getMap().addTankMorts(this);
            getMap().removeTank(this);
        } else {
            this.setNbVie(5);
            getMap().removeTank(this);
        }
    }

    /**
     * Réalise l'action donnée par l'IA.
     *
     * @param act action donnée par l'IA
     */
    protected final void action(final Action act) {
        if (getLatence() > 0) {
            setLatence(getLatence() - 1);
            return;
        }
        if (pointsDeVie <= 0) {
            return;
        }
        switch (act) {
            case moveForward:
                avancer();
                setLatence(0);
                break;
            case moveBackward:
                reculer();
                setLatence(0);
                break;
            case shoot:
                tirer();
                setLatence(0);
                break;
            case turnClockwise:
                getDirection().tournerDroite();
                setLatence(0);
                break;
            case turnCounterClockwise:
                getDirection().tournerGauche();
                setLatence(0);
                break;
            default:
                break;
        }
    }

    /**
     * Lancer IA.
     */
    protected final void lancerIA() {
        if (getLatence() == 0) {
            ia.computeAction();
        }
    }

    /**
     * Renvoie l'action ordonnée par l'IA.
     */
    public final void getAction() {
        Action act = Action.noAction;
        if (getLatence() > 0) {
            setLatence(getLatence() - 1);
            return;
        }
        if (ia.getAction() != null) {
            act = ia.getAction();
            if (getMap().isAffichageOn()) {
                System.out.println("id: " + getId() + " act: " + act);
            }
        }
        action(act);
    }

    @Override
    public void paint(final Graphics g,
                      final int x,
                      final int y) {
    }
}
