package thinktank.javabot.physics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Terrain.
 */
public class Terrain {
    /**
     * Tableau de l'arene.
     */
    private ObjetTT[][] terrain;

    /**
     * Liste des tanks qu'on a ajoute sur l'arene.
     */
    private ArrayList<Tank> listAllTank = new ArrayList<>();

    /**
     * Liste des tanks sur arene.
     */
    private ArrayList<Tank> tanks = new ArrayList<>();

    /**
     * Liste des tanks morts.
     */
    private ArrayList<Tank> tanksMorts = new ArrayList<>();

    /**
     * Liste des projectiles.
     */
    private ArrayList<Projectile> projectiles = new ArrayList<>();

    /**
     * Liste des obstacles.
     */
    private ArrayList<Obstacle> listeObstacles = new ArrayList<>();

    /**
     * Les coordonnes.
     */
    private int x, y;

    /**
     * Si le terrain affiche.
     */
    private boolean affichageOn = false;

    /**
     * Getter affichageOn.
     *
     * @return boolean
     */
    public final boolean isAffichageOn() {
        return affichageOn;
    }

    /**
     * Afficher.
     */
    protected final void affichageOn() {
        affichageOn = true;
    }

    /**
     * Cacher.
     */
    protected final void affichageOff() {
        affichageOn = false;
    }

    /**
     * Constructor.
     *
     * @param newX x
     * @param newY y
     */
    public Terrain(final int newX,
                   final int newY) {
        terrain = new ObjetTT[newX][newY];
        for (int i = 0; i < newX; i++) {
            for (int j = 0; j < newY; j++) {
                if (i == 0 || i == newX - 1
                        || j == 0 || j == newY - 1) {
                    terrain[i][j] = Mur.getMur();
                } else {
                    terrain[i][j] = Vide.getVide();
                }
            }
        }
        this.x = newX;
        this.y = newY;
    }

    /**
     * Constructor.
     *
     * @param newX x
     * @param newY y
     * @param v    v
     */
    public Terrain(final int newX,
                   final int newY,
                   final Vide v) {
        terrain = new ObjetTT[newX][newY];
        /*
         * Initialiser le map avec les murs tout autour.
		 */
        for (int i = 0; i < newX; i++) {
            for (int j = 0; j < newY; j++) {
                if (i == 0 || i == newX - 1
                        || j == 0 || j == newY - 1) {
                    terrain[i][j] = Mur.getMur();
                } else {
                    terrain[i][j] = Vide.getVide();
                }
            }
        }
        this.x = newX;
        this.y = newY;
    }

    /**
     * Renvoie le Terrain (tableau d'objet).
     *
     * @return terrain
     */
    public final ObjetTT[][] getMap() {
        return terrain;
    }

    /**
     * renvoie l'objet sur la case de coordonnée (x,y).
     *
     * @param newX absyss
     * @param newY ordonnée
     * @return objet
     */
    public final ObjetTT detail(final int newX,
                                final int newY) {
        if (newX < 0 || newY < 0 || newX >= tailleX()
                || newY >= tailleY()) {
            return Mur.getMur();
        }
        return terrain[newX][newY];
    }

    /**
     * Renvoie toute la liste des obstacles.
     *
     * @return liste des obstacles
     */
    public final ArrayList<Obstacle> getListeObstacles() {
        return listeObstacles;
    }

    /**
     * Change la liste des obstacles.
     * @param
     */
    public void setListeObstacles(ArrayList<Obstacle> liste){
        this.listeObstacles = liste;
    }

    /**
     * Renvoie toute la liste des tanks.
     *
     * @return liste des tanks
     */
    public final ArrayList<Tank> getListAllTank() {
        return listAllTank;
    }

    /**
     * Renvoie la liste des tanks sur l'arene.
     *
     * @return liste des tanks sur arene
     */
    public final ArrayList<Tank> getTanks() {
        return tanks;
    }

    /**
     * renvoie la liste des tanks morts.
     *
     * @return arraylist
     */
    public final ArrayList<Tank> getTanksMorts() {
        return tanksMorts;
    }

    /**
     * Renvoie la liste des projectiles.
     *
     * @return arraylist
     */
    public final ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    /**
     * Ajouter un tank dans la liste totale des tanks.
     *
     * @param tank tank a ajoute
     * @return tank ajoute
     */
    public final Tank addTankTotal(final Tank tank) {
        if (!estLibre(tank.getCoordX(), tank.getCoordY())) {
            return null;
        }
        listAllTank.add(tank);
        return tank;
    }

    /**
     * Rajouter un tank au Terrain, ainsi qu'a la liste des tanks.
     *
     * @return tank ajoute
     */
    protected final Tank addTank() {
        Tank t = new Tank(this);
        tanks.add(t);
        addObjetTT(t.getCoordX(), t.getCoordY(), t);
        return t;
    }

    /**
     * Rajoute un tank au Terrain, ainsi qu'a la liste des tanks.
     *
     * @param tank tank a ajoute
     * @return tank ajoute
     */
    protected final Tank addTank(final Tank tank) {
        if (!estLibre(tank.getCoordX(), tank.getCoordY())) {
            return null;
        }
        tanks.add(tank);
        tank.setMap(this);
        addObjetTT(tank.getCoordX(), tank.getCoordY(), tank);
        return tank;
    }

    /**
     * Ajouter un obstacle au Terrain,
     * ainsi qu'a la liste des obstacle.
     *
     * @param obstacle obstacle a ajoute
     */
    protected final void addObstacle(final Obstacle obstacle) {
        listeObstacles.add(obstacle);
        addObjetTT(obstacle.getCoordX(), obstacle.getCoordY(), obstacle);
    }

    /**
     * Rajoute un tank mort a la liste des tanks morts.
     *
     * @param tank tank ajoute
     * @return tank
     */
    protected final Tank addTankMorts(final Tank tank) {
        if (!estLibre(tank.getCoordX(), tank.getCoordY())) {
            return null;
        }
        tanksMorts.add(tank);
        tank.setMap(this);
        return tank;
    }

    /**
     * Enleve le tank désigné de la liste.
     *
     * @param tank le tank à enlever
     */
    protected final void removeTank(final Tank tank) {
        Iterator<Tank> iter = getTanks().iterator();
        while (iter.hasNext()) {
            Tank t = iter.next();
            if (t == tank) {
                iter.remove();
            }
        }
    }

    /**
     * Enleve le tank désigné de la liste.
     *
     * @param tank le tank à enlever
     */
    protected final void removeTankMorts(final Tank tank) {
        Iterator<Tank> iter = getTanksMorts().iterator();
        while (iter.hasNext()) {
            Tank t = iter.next();
            if (t == tank) {
                iter.remove();
            }
        }
    }

    /**
     * Renvoie le mobile d'identifiant id.
     *
     * @param id identifiant d'un mobile
     * @return mobile
     */
    public final Mobile getMobFromId(final int id) {
        for (Tank t : tanks) {
            if (t.getId() == id) {
                return t;
            }
        }
        for (Tank t : tanksMorts) {
            if (t.getId() == id) {
                return t;
            }
        }
        for (Projectile p : projectiles) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     * Renvoie le Tank d'identifiant id.
     *
     * @param id l'identifiant d'un Tank
     * @return tank ajoute
     */
    public final Tank getTankFromId(final int id) {
        for (Tank t : tanks) {
            if (t.getId() == id) {
                return t;
            }
        }
        for (Tank t : tanksMorts) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    /**
     * Renvoie le Projectile d'identifiant id.
     *
     * @param id identifiant d'un Projectile
     * @return projectile
     */
    public final Projectile getProFromId(final int id) {
        for (Projectile p : projectiles) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     * Ajoute un Projectile à la liste des Projectiles.
     *
     * @param proj Projectile à ajouter à la liste des Projectiles
     */
    protected final void addProjectile(final Projectile proj) {
        projectiles.add(proj);
        addObjetTT(proj.getCoordX(), proj.getCoordY(), proj);
    }

    /**
     * Enleve un Projectile à la liste des Projectiles.
     *
     * @param proj Projectile à enlever de la liste des Projectiles
     */
    protected final void removeProjectile(final Projectile proj) {
        Iterator<Projectile> iter = getProjectiles().iterator();
        while (iter.hasNext()) {
            Projectile t = iter.next();
            if (t == proj) {
                iter.remove();
            }
        }
    }

    /**
     * Renvoie true si la case(x,y) contient un Vide,
     * sinon renvoie false.
     *
     * @param newX absyss
     * @param newY ordonnée
     * @return boolean
     */
    public final boolean estLibre(final int newX,
                                  final int newY) {
        return detail(newX, newY) == Vide.getVide();
    }

    /**
     * Renvoie la taille en absyss du Terrain.
     *
     * @return x
     */
    public final int tailleX() {
        return x;
    }

    /**
     * renvoie la taille en ordonnée du Terrain.
     *
     * @return y
     */
    public final int tailleY() {
        return y;
    }

    /**
     * renvoie la nature de l'objet en (x,y).
     *
     * @param newX absyss
     * @param newY ordonnée
     * @return type physique
     */
    protected final Physique.TypeP caseContent(final int newX,
                                               final int newY) {
        if (newX < terrain.length && newY < terrain[newX].length) {
            if (terrain[newX][newY] == Mur.getMur()) {
                return Physique.TypeP.mur;
            }
            if (terrain[newX][newY] == Vide.getVide()) {
                return Physique.TypeP.vide;
            }
            return terrain[newX][newY].getType();
        } else {
            return Physique.TypeP.mur;
        }
    }

    /**
     * Test et Set case.
     *
     * @param mob  mobile
     * @param newX x
     * @param newY y
     * @return boolean
     */
    public final boolean testAndSetCase(final Mobile mob,
                                        final int newX,
                                        final int newY) {
        int x = mob.getCoordX();
        int y = mob.getCoordY();
        if(estLibre(newX, newY)){
            erase(x,y);
            terrain[newX][newY] = mob;
            return true;
        }
        if(mob.getType() == Physique.TypeP.projectile) {
            if(caseContent(newX,newY) == Physique.TypeP.mur || caseContent(newX,newY) == Physique.TypeP.obstacle )
            { // un mur ou obstacle
                mob.tuer();
                return true;
            }
            if(caseContent(newX, newY) == Physique.TypeP.projectile)
            {
                Projectile p =((Projectile) detail(newX, newY));
                mob.tuer();
                Projectile.setIdMort(p.getId());
                p.tuer();
                return true;
            }
            if(caseContent(newX, newY) == Physique.TypeP.tank)
            {
                mob.tuer();
                ((Tank) detail(newX, newY)).subit(((Projectile)mob).getDegatsProjectile());
                return true;
            }
        }
        if(mob.getType() == Physique.TypeP.tank){
            if(caseContent(newX, newY) == Physique.TypeP.projectile)
            {
                ((Tank) mob).subit(((Projectile)detail(newX, newY)).getDegatsProjectile());
                ((Projectile) detail(newX, newY)).tuer();
                return true;
            }
        }
        return false;
    }

    /**
     * Enleve du Terrain l'objet en (x,y).
     *
     * @param newX absyss
     * @param newY ordonnée
     */
    public final void erase(final int newX,
                            final int newY) {
        terrain[newX][newY] = Vide.getVide();
    }

    /**
     * Ajoute obj sur le Terrain.
     *
     * @param newX absyss
     * @param newY ordonnée
     * @param obj  objet à ajouter
     */
    public final void addObjetTT(final int newX,
                                 final int newY,
                                 final ObjetTT obj) {
        terrain[newX][newY] = obj;
    }

    /**
     * Met à jours le Terrain (tableau d'objet).
     *
     * @param map terrain
     */
    protected final void setMap(final ObjetTT[][] map) {
        this.terrain = map;
    }

    /**
     * Ajouter un obstacle1 sur map.
     *
     * @param newX x
     * @param newY y
     */
    public void newObstacle1(int newX, int newY){
        if(estLibre(newX,newY))
            terrain[newX][newY] = ImageObstacle1.getImageObstacle1();
    }

    /**
     * Ajouter un obstacle2 sur map.
     *
     * @param newX x
     * @param newY y
     */
    public void newObstacle2(int newX, int newY){
        if(estLibre(newX,newY))
            terrain[newX][newY] = ImageObstacle2.getImageObstacle2();
    }

    /**
     * Ajouter un obstacle3 sur map.
     *
     * @param newX x
     * @param newY y
     */
    public void newObstacle3(final int newX,
                             final int newY){
        if(estLibre(newX,newY))
            terrain[newX][newY] = ImageObstacle3.getImageObstacle3();
    }

    /**
     * Supprimer l'obstacle.
     *
     * @param obs obstacle
     */
    public final void removeObstacle(final Obstacle obs) {
        listeObstacles.remove(obs);
    }
}
