package thinktank.javabot.intelligences;

import thinktank.javabot.physics.Physique;
import thinktank.javabot.physics.Tank;
import thinktank.javabot.sensors.DetectionLigneDroite;

/**
 * The Class TankRemote.
 */
public class TankRemote {
    /**
     * The tank phy.
     */
    private Tank tankPhy;

    /**
     * The lock.
     */
    private boolean lock = true; //Verrou de la télécommande

    /**
     * The ia.
     */
    private Intelligence ia;

    /**
     * Instantiates a new tank remote.
     *
     * @param intel the ia
     * @param tankP the tank phy
     */
    public TankRemote(final Intelligence intel, final Tank tankP) {
        this.ia = intel;
        this.tankPhy = tankP;
    }

    /**
     * Vérrouille la télécommande, l'IA associée s'endors.
     */
    public final synchronized void lock() {
        lock = true;
    }

    /**
     * dévérrouille la télécommande,
     * l'IA associée est solicité pour un calcul d'action.
     */
    public final synchronized void unlock() {
        lock = false;
        notifyAll();
    }

    /**
     * Renvoie l'état du vérrou de la télécommande.
     *
     * @return true, if is locked
     */
    public final boolean isLocked() {
        return lock;
    }

    /**
     * Endors l'IA (et donc le script python)
     * jusqu'à la prochaine demande d'action.
     */
    public final synchronized void bePrepared() {
        try {
            this.lock();
            ia.noMoreRunning();
            while (isLocked()) {
                wait();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

	/* Commandes accessibles pour les scripts IA (jython/python).*/

    /**
     * Do nothing.
     */
    public final void doNothing() {
        ia.setAction(Action.noAction);
        bePrepared();
    }

    /**
     * Move forward.
     */
    public final void moveForward() {
        ia.setAction(Action.moveForward);
        bePrepared();
    }

    /**
     * Move backward.
     */
    public final void moveBackward() {
        ia.setAction(Action.moveBackward);
        bePrepared();
    }

    /**
     * Turn clockwise.
     */
    public final void turnClockwise() {
        ia.setAction(Action.turnClockwise);
        bePrepared();
    }

    /**
     * Turn counter clockwise.
     */
    public final void turnCounterClockwise() {
        ia.setAction(Action.turnCounterClockwise);
        bePrepared();
    }

    /**
     * Shoot.
     */
    public final void shoot() {
        ia.setAction(Action.shoot);
        bePrepared();
    }

    /**
     * Radar.
     *
     * @return the int
     */
    public final int radar() {
        return 42;
    }

    /**
     * Look forward.
     *
     * @return the int
     */
    public final int distanceOfForwardObstacle() {
        DetectionLigneDroite dld = ((DetectionLigneDroite) tankPhy.getSensor());
        return dld.detection().getDistance();
    }

    /**
     * Type de l'obstacle devant.
     *
     * @return type physique
     */
    public final Physique.TypeP typeOfForwardObstacle() {
        DetectionLigneDroite dld = ((DetectionLigneDroite) tankPhy.getSensor());
        return dld.detection().getTypePhysique();
    }
}
