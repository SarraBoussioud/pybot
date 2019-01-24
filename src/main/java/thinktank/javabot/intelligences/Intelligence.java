package thinktank.javabot.intelligences;

import org.python.util.PythonInterpreter;
import thinktank.javabot.physics.Tank;

/**
 * Classe exécutant sur un thread dédié le script ia d'un utilisateur.
 *
 * @author cedric
 */
public class Intelligence extends Thread {
    /**
     * The tank r.
     */
    private TankRemote tankR;

    /**
     * The filepath.
     */
    private String filepath;

    /**
     * The action.
     */
    private Action action;

    /**
     * The intelligences.
     */
    private Intelligences intelligences;

    /**
     * The is initialized.
     */
    private boolean isInitialized = false; //Verrou d'initialisation.

    /**
     * Reprend le script python pour calculer
     * une nouvelle action à effectuer.
     * (Fonction non bloquante).
     */
    public final void computeAction() {
        if (action == Action.scriptCompleted
                || action == Action.scriptTerminated) {
            return;
        }
        this.setRunning();
        this.action = Action.noAction;
        tankR.unlock();
    }

    /**
     * Attend la réponse du script. (Fonction bloquante).
     *
     * @return the action
     * @deprecated
     */
    @Deprecated
    public final synchronized Action waitForAction() {
        while (this.action == Action.noAction) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return action;
    }

    /**
     * Retourne l'action calculée par le script avec computeAction.
     * Il faut penser à laiser le temps
     * au script de calculer (delay + timeout).
     * @return the action
     */
    public final Action getAction() {
        return action;
    }

    /**
     * Fonction interne au package. Permet au script python,
     * via un objet TankRemote, de stoquer l'action à effectuer.
     *
     * @param a the new action
     */
    public final synchronized void setAction(final Action a) {
        this.action = a;
        notifyAll();
    }

    /**
     * Constructeur. Demande le chemin du script utilisateur.
     *
     * @param fp      Chemin vers le script utilisateur
     *                (python/jython) codant l'IA du tank.
     * @param intel Liste d'intélligences.
     * @deprecated Utilisez {@Link
     * #Intelligence(String, Intelligences, Tank)} à la place.
     */
    @Deprecated
    public Intelligence(final String fp, final Intelligences intel) {
        this.tankR = new TankRemote(this, null);
        this.filepath = fp;
        this.intelligences = intel;
    }

    /**
     * Constructeur. Demande le chemin du script utilisateur.
     *
     * @param fp      Chemin vers le script utilisateur
     *                (python/jython) codant l'IA du tank.
     * @param intel Liste d'intélligences.
     * @param tankPhy Tank physique, utilisé pour l'accès
     *                aux différents capteurs
     *                par les scripts d'IA utilisateurs.
     */
    public Intelligence(final String fp,
                        final Intelligences intel,
                        final Tank tankPhy) {
        this.tankR = new TankRemote(this, tankPhy);
        this.filepath = fp;
        this.intelligences = intel;
    }

    /**
     * Lève le vérrou d'initialisation.
     */
    public final synchronized void setInitialized() {
        isInitialized = true;
        notifyAll();
    }

    /**
     * Initialise l'IA. IL FAUT effectuer obligatoirement
     * l'initialisation avant d'utiliser les accesseurs d'actions.
     */
    public final synchronized void initialize() {
        this.start();
        while (!isInitialized) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Sets the running.
     */
    public final void setRunning() {
        this.intelligences.addRunningIntelligence();
    }

    /**
     * No more running.
     */
    public final void noMoreRunning() {
        this.intelligences.removeRunningIntelligence();
    }

    /**
     * Termine un script utilisateur.
     * Ne marche que lorsque le script est en pause.
     * Cela est du au fait que l'interprète Jython cache les appels à interrupt.
     * A part bidouillages, il est impossible de terminer
     * un script coincé dans une boucle infinie.
     *
     * @deprecated NE MARCHE PAS!!
     */
    @Deprecated
    public final void terminate() {
        this.interrupt();
        this.setAction(Action.scriptTerminated);
    }

    /**
     * Thread dédié à l'interpréteur python pour l'IA utilisateur.
     */
    public final void run() {
        this.setRunning();
        PythonInterpreter interp = new PythonInterpreter();
        interp.setOut(System.out);
        interp.exec("import sys");
        interp.exec("print sys");
        interp.set("tank", tankR);
        setInitialized();
        tankR.bePrepared();
        interp.execfile(filepath);
        setAction(Action.scriptCompleted);
        this.noMoreRunning();
    }
}
