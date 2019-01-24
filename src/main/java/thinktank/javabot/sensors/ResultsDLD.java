package thinktank.javabot.sensors;

import thinktank.javabot.physics.Physique;

/**
 * Resultat de Detection Ligne Droite.
 */
public class ResultsDLD {
    /**
     * Distance.
     */
    private int distance;

    /**
     * Type physique.
     */
    private Physique.TypeP type;

    /**
     * Constructor.
     *
     * @param d     distance
     * @param typeP type
     */
    public ResultsDLD(final int d,
                      final Physique.TypeP typeP) {
        this.distance = d;
        this.type = typeP;
    }

    /**
     * Getter distance.
     *
     * @return distance
     */
    public final int getDistance() {
        return distance;
    }

    /**
     * Getter type physique.
     *
     * @return type
     */
    public final Physique.TypeP getTypePhysique() {
        return type;
    }
}
