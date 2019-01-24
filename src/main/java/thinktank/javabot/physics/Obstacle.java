package thinktank.javabot.physics;

import java.awt.*;
import java.util.Random;

/**
 * Created by Sarra Boussioud on 07/03/2017.
 */
public class Obstacle extends Mobile {
    private int typeObs;
    public Obstacle(int a, Terrain map) {
        typeObs = a;
        setId(newId());
        while (!map.estLibre(getCoordX(), getCoordY())) {
            setCoordX(aleatoire(1, map.tailleX() - 1));
            setCoordY(aleatoire(1, map.tailleY() - 1));
        }
        switch (a) {
            case 1:
                map.newObstacle1(getCoordX(), getCoordY());
                setMap(map);
                setDirection(new Direction(0, 1));
                break;
            case 2:
                map.newObstacle2(getCoordX(), getCoordY());
                setMap(map);
                setDirection(new Direction(0, 1));
                break;
            case 3:
                map.newObstacle3(getCoordX(), getCoordY());
                setMap(map);
                setDirection(new Direction(0, 1));
                break;
            default:
                break;
        }
    }
    /**
     * Creation d'un obstacle lors de la modification de l'arene
     * @param map Terrain
     * @param x int
     * @param y int
     */
    public Obstacle(int a, Terrain map, int x, int y) {
        typeObs = a;
        setId(newId());
        this.setCoordX(x);
        this.setCoordY(y);
        switch (a) {
            case 1:
                map.newObstacle1(x, y);
                setMap(map);
                setDirection(new Direction(0, 1));
                break;
            case 2:
                map.newObstacle2(x, y);
                setMap(map);
                setDirection(new Direction(0, 1));
                break;
            case 3:
                map.newObstacle3(x, y);
                setMap(map);
                setDirection(new Direction(0, 1));
                break;
            default:
                break;
        }
    }

    private final int aleatoire(final int valMin,
                                final int valMax) {
        Random random = new Random();
        return valMin + random.nextInt(valMax - valMin);
    }

    @Override
    public Physique.TypeP getType() {
        return Physique.TypeP.obstacle;
    }

    @Override
    public void paint(Graphics g, int x, int y) {
    }

    @Override
    protected void tuer() {
        getMap().erase(getCoordX(), getCoordY());
        meurt();
        getMap().removeObstacle(this);
    }

    public int getTypeObs() {
        return typeObs;
    }
}
