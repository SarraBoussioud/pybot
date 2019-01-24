package thinktank.javabot.physics;

import thinktank.javabot.graphics.GraphicArena;
import thinktank.javabot.graphics.ImageLoader;

import java.awt.*;

/**
 * Created by SoF on 18/03/2017.
 */
public class ImageObstacle3 implements ObjetTT {

    private Image sprite;
    private Image background_sprinte;

    public ImageObstacle3(ImageLoader img){
        sprite = img.getSprite(ImageLoader.SpriteName.OBSTACLE3.ordinal());
        background_sprinte = GraphicArena.imgLoader.getSprite(ImageLoader.SpriteName.SOL.ordinal());
    }
    final static ImageObstacle3 IMAGE_OBSTACLE_3 = new ImageObstacle3(GraphicArena.imgLoader);

    public static ImageObstacle3 getImageObstacle3(){
        return IMAGE_OBSTACLE_3;
    }

    @Override
    public Physique.TypeP getType() {
        return Physique.TypeP.obstacle;
    }

    @Override
    public void paint(Graphics g, int x, int y) {
        g.drawImage(background_sprinte, x, y, null);
        g.drawImage(sprite, x, y, null);
    }
}