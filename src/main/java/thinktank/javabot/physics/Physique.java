package thinktank.javabot.physics;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Physique.
 */
public class Physique {
	/**
	 * Terrain.
	 */
	private Terrain map;

	/**
	 * Lignes.
	 */
	private int lignes = 42;

	/**
	 * Colonnes.
	 */
	private int colonnes = 24;

	/**
	 * Timer.
	 */
	private Timer timer = new Timer(0, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	});

	/**
	 * Enum.
	 */
	public enum TypeP {
		tank,
		vide,
		projectile,
		obstacle,
		mur
	}

	/**
	 * Retourner le timer.
	 *
	 * @return timer
	 */
	public final Timer getTimer() {
		return timer;
	}

	/**
	 * Retourner le nombre de lignes total.
	 *
	 * @return lignes
	 */
	public final int getLignes() {
		return lignes;
	}

	/**
	 * Retourner le nombre de colonnes total.
	 *
	 * @return colonnes
	 */
	public final int getColonnes() {
		return colonnes;
	}

	/**
	 * Check if terrain est affiche.
	 *
	 * @return boolean
	 */
	public final boolean isAffichageOn() {
		return map.isAffichageOn();
	}

	/**
	 * Afficher arene.
	 */
	public final void affichageOn() {
		map.affichageOn();
	}

	/**
	 * Cacher arene.
	 */
	public final void affichageOff() {
		map.affichageOff();
	}

	/**
	 * Constructor.
	 */
	public Physique() {
		map = new Terrain(lignes, colonnes);
	}

	/**
	 * Constructor avec vide.
	 *
	 * @param v vide
	 */
	public Physique(final Vide v) {
		map = new Terrain(lignes, colonnes, v);
	}

	/**
	 * Getter Map.
	 *
	 * @return terrain
	 */
	public final Terrain getMap() {
		return map;
	}

	/**
	 * Retourne la matrice du terrain.
	 *
	 * @return objet
	 */
	public final ObjetTT[][] getTerrain() {
		return map.getMap();
	}

	/**
	 * Retourne la taille abscisse de la matrice.
	 *
	 * @return x
	 */
	public final int tailleX() {
		return map.tailleX();
	}

	/**
	 * Retourne la taille ordonnée de la matrice.
	 *
	 * @return y
	 */
	public final int tailleY() {
		return map.tailleY();
	}

	/**
	 * Retourne l'objet à la position (x,y).
	 *
	 * @param x x
	 * @param y y
	 * @return objet
	 */
	public final ObjetTT detail(final int x, final int y) {
		return map.detail(x, y);
	}

	/**
	 * Verifier si la position (x,y) est libre.
	 *
	 * @param x x
	 * @param y y
	 * @return boolean
	 */
	public final boolean estLibre(final int x, final int y) {
		return map.estLibre(x, y);
	}

	/**
	 * Retourne la liste des Obstacles.
	 *
	 * @return listObstacle
	 */
	public final ArrayList<Obstacle> getObstacles() {
		return map.getListeObstacles();
	}
	public void addObstacle(Obstacle obstacle){
		map.addObstacle(obstacle);
	}

	/**
	 * Retourner la liste des tanks sur l'arene.
	 *
	 * @return listTanks
	 */
	public final ArrayList<Tank> getTanks() {
		return map.getTanks();
	}

	/**
	 * Retourner la liste des tanks qui sont morts.
	 *
	 * @return listTanksMorts
	 */
	public final ArrayList<Tank> getTanksMorts() {
		return map.getTanksMorts();
	}

	/**
	 * Ajouter un tank par defaut.
	 */
	public final void addTank() {
		map.addTank();
	}

	/**
	 * Ajouter un tank.
	 *
	 * @param tank tank a ajouter
	 */
	public final void addTank(final Tank tank) {
		map.addTank(tank);
	}

	/**
	 * Supprimer un tank.
	 *
	 * @param tank tank a ajouter
	 */
	public final void removeTank(final Tank tank) {
		map.removeTank(tank);
	}


	/**
	 * Retourner la liste des Projectiles.
	 *
	 * @return listeProjectile
	 */
	public final ArrayList<Projectile> getProjectiles() {
		return map.getProjectiles();
	}

	/**
	 * Retourner le TypeP contenu dans la case(x,y).
	 * @param x x
	 * @param y y
	 * @return TypeP
	 */
	public final TypeP caseContent(final int x, final int y) {
		return map.caseContent(x, y);
	}

	/**
	 * Lancer la prochaine action de tout les éléments du Terrain.
	 */
	public final void iter() {
		int i = 0;
        /*
        Lancer le timer pour chaque tank morts dans la liste des tanks morts
         */
		for (final Tank t : getTanksMorts()) {
			if (!t.isTimerRunning()) {
				timer = new Timer(3000, new ActionListener() {
					@Override
					public void actionPerformed(
							final ActionEvent e) {
						t.setPointsDeVie(100);
						t.vivre();
						t.setTimerRunning(false);
						getMap().addTank(t);
						getMap().removeTankMorts(t);
					}
				});
				timer.setRepeats(false);
				t.setTimerRunning(true);
				timer.start();
			}
		}
        /*
        Les tanks sur arene marchent
         */
		for (Tank t : getTanks()) {
			t.lancerIA();
			t.reduireTempsRestant();
		}
        /*
        Les projectiles marchent
         */
		ArrayList<Projectile> ps = map.getProjectiles();
		if (ps.size() > 0) {
			Projectile p = ps.get(i);
			while (p != null && i < ps.size()) {
				p.avancer();
				if (i < ps.size()) {
					if (!p.getMort()) {
						i++;
						if (i < ps.size()) {
							p = ps.get(i);
						}
					} else if (Projectile.getIdMort()
							== -1) {
						p = ps.get(i);
					} else {
						if (p.getId()
								> Projectile.getIdMort()) {
							i = i--;
							if (i < ps.size()) {
								p = ps.get(i);
							}
						} else {
							if (i < ps.size()) {
								p = ps.get(i);
							}
						}
						Projectile.initIdMort();
					}
				}
			}
		}
        /*
        Timer de 100 ms pour les projectiles
         */
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		i = 0;
		ArrayList<Tank> ts = getTanks();
		if (ts.size() > 0) {
			Tank t = ts.get(i);
			while (t != null && i < ts.size()) {
				t.getAction();
				if (i < ts.size()) {
					if (!t.getMort()) {
						i++;
						if (i < ts.size()) {
							t = ts.get(i);
						}
					} else {
						t = ts.get(i);
					}
				}
			}
		}
	}
}
