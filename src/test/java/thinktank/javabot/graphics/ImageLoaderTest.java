package thinktank.javabot.graphics;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.Assert.*;

/**
 * Created by SoF on 27/04/2017.
 */
public class ImageLoaderTest {

    ImageLoader imageLoader;
    Image image;

    @Before
    public void setUp() throws Exception {
        imageLoader = new ImageLoader();
        image = new BufferedImage(100,100,2);
    }

    @Test
    public void getColorName() throws Exception {
        assertTrue(imageLoader.getColorName(Color.BLUE)=="Blue");
        assertFalse(imageLoader.getColorName(Color.YELLOW)=="Blue");
    }

    @Test
    public void toBufferedImage() throws Exception {
        assertTrue(imageLoader.toBufferedImage(image) instanceof BufferedImage);
    }

    @Test
    public void getSprite() throws Exception {
        assertTrue(imageLoader.getSprite(1) instanceof BufferedImage);
    }

    @Test
    public void getImg() throws Exception {
        assertTrue(imageLoader.getImg() instanceof BufferedImage[]);
    }

}