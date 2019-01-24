package thinktank.javabot.sensors;

import thinktank.javabot.physics.Physique;
import thinktank.javabot.physics.Tank;

/**
 * Detection de la ligne droite.
 */
public class DetectionLigneDroite implements Sensors {
    /**
     * Tank.
     */
    private Tank objTank;

    /**
     * Physique.
     */
    private Physique physique;

    /**
     * Constructor.
     *
     * @param obj tank
     * @param phys    physique
     */
    public DetectionLigneDroite(final Tank obj,
                                final Physique phys) {
        this.objTank = obj;
        this.physique = phys;
    }

    /**
     * Detection.
     *
     * @return resultsDLD
     */
    public final ResultsDLD detection() {
        int x = objTank.getCoordX();
        int y = objTank.getCoordY();
        int dx = objTank.getDirection().getDx();
        int dy = objTank.getDirection().getDy();
        for (int i = 1; i <= 10; i++) {
            Physique.TypeP contenu = physique.caseContent(
                    x + i * dx, y + i * dy);
            if (contenu != Physique.TypeP.vide) {
                return new ResultsDLD(i, contenu);
            }
        }
        return new ResultsDLD(0, Physique.TypeP.vide);
    }
}
