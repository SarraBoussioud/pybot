package thinktank.javabot.physics;

import thinktank.javabot.graphics.GraphicArena;
import thinktank.javabot.graphics.ImageLoader;

import java.awt.*;

/**
 * Created by SoF on 17/03/2017.
 */
public class ImageObstacle2 implements ObjetTT {

    private Image sprite;
    private Image background_sprinte;

    public ImageObstacle2(ImageLoader img){
        sprite = img.getSprite(ImageLoader.SpriteName.OBSTACLE2.ordinal());
        background_sprinte = GraphicArena.imgLoader.getSprite(ImageLoader.SpriteName.SOL.ordinal());
    }
    final static ImageObstacle2 IMAGE_OBSTACLE_2 = new ImageObstacle2(GraphicArena.imgLoader);

    public static ImageObstacle2 getImageObstacle2(){
        return IMAGE_OBSTACLE_2;
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
