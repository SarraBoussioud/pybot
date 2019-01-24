package thinktank.javabot.physics;

import thinktank.javabot.graphics.GraphicArena;
import thinktank.javabot.graphics.ImageLoader;

import java.awt.*;

/**
 * Created by SoF on 16/03/2017.
 */
public class ImageObstacle1 implements ObjetTT {

    private Image sprite;
    private Image background_sprinte;

    public ImageObstacle1(ImageLoader img){
        sprite = img.getSprite(ImageLoader.SpriteName.OBSTACLE1.ordinal());
        background_sprinte = GraphicArena.imgLoader.getSprite(ImageLoader.SpriteName.SOL.ordinal());
    }
    final static ImageObstacle1 IMAGE_OBSTACLE_1 = new ImageObstacle1(GraphicArena.imgLoader);

    public static ImageObstacle1 getImageObstacle1(){
        return IMAGE_OBSTACLE_1;
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
