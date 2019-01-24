package thinktank.javabot.intelligences;

import java.util.ArrayList;

import thinktank.javabot.physics.Tank;

/**
 * Classe de gestions des intelligences artificielles.
 *
 * @author cedric
 */
public class Intelligences {
    /**
     * The running intelligences.
     */
    private int runningIntelligences = 0;

    /**
     * The intelligences.
     */
    private ArrayList<Intelligence> intelligences = new ArrayList<>();

    /**
     * Incrémente le compteur (sémaphore) d'intelligences s'éxécutants.
     */
    public final synchronized void addRunningIntelligence() {
        this.runningIntelligences++;
    }

    /**
     * Décrémente le compteur (sémaphore) d'intelligences s'éxécutants.
     */
    public final synchronized void removeRunningIntelligence() {
        this.runningIntelligences--;
        if (runningIntelligences <= 0) {
            this.notifyAll();
        }
    }

    /**
     * Renvoi le nombre d'ia qui sont toujours en train de calculer
     * (non endormies).
     *
     * @return nombre d'intelligences s'exécutants.
     */
    public final int runningIntelligences() {
        return runningIntelligences;
    }

    /**
     * Crée une nouvelle intelligence et l'ajoute à la liste.
     *
     * @param filepath Chemin de fichier vers le script (python)
     *                 de l'IA utilisateur.
     * @return intelligence
     * @deprecated Utilisez {@Link #newIntelligence(String, Tank)} à la place.
     */
    @Deprecated
    public final Intelligence newIntelligence(final String filepath) {
        Intelligence ia = new Intelligence(filepath, this);
        intelligences.add(ia);
        return ia;
    }

    /**
     * New intelligence.
     *
     * @param filepath the filepath
     * @param tankPhy  the tank phy
     * @return the intelligence
     */
    public final Intelligence newIntelligence(final String filepath,
                                        final Tank tankPhy) {
        Intelligence ia = new Intelligence(filepath, this, tankPhy);
        intelligences.add(ia);
        return ia;
    }

    /**
     * Réveille toutes les IA pour calculer leurs prochaines actions.
     * La fonction est non bloquante.
     */
    public final void computeAllActions() {
        for (Intelligence ia : intelligences) {
            ia.computeAction();
        }
    }

    /**
     * Attend la fin du calcul de toutes les IA. à exécuter
     * APRES un computeAllActions().
     *
     * @deprecated La fonction est BLOQUANTE. Ne l'utilisez QUE POUR DES TESTS.
     */
    @Deprecated
    public final synchronized void waitForAllActions() {
        while (runningIntelligences() > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    /**
     * Attend la fin du calcul de toutes les IA.
     * à exécuter APRES un computeAllActions().
     * La fonction est SEMI-BLOQUANTE
     * (elle dispose d'un timeout en millisecondes).
     *
     * @param timeout Timeout en ms.
     * @deprecated Ne fonctionne pas correctement:
     * l'intelligence peut ne pas se réveiller.
     * NE PAS UTILISER! Insérer un sleep à la place.
     */
    @Deprecated
    public final synchronized void waitForAllActions(final long timeout) {
        try {
            wait(timeout);
            /*
            Note: il faudrait mettre ce wait dans une boucle avec condition,
            mais comment savoir si le wait termine
            à cause d'un timeout ou juste d'un notify ?
             */
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int getRunningIntelligences() {
        return runningIntelligences;
    }

    public ArrayList<Intelligence> getIntelligences() {
        return intelligences;
    }
}
