package jmjrst.main;

import static org.junit.Assert.*;

import java.awt.ImageCapabilities;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jis.generator.Generator;
import org.junit.Test;

public class RotateImageTest {
    
  Generator generator;
  BufferedImage image;
  
  /**
   *  .
   */
  public void setUp() {
    this.generator = new Generator(null, 0);
    try {
      this.image = ImageIO.read(new File("src/test/resources/picture.jpg"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  private boolean areImagesEqual(BufferedImage image1, BufferedImage image2) {
    if (image1.getWidth() == image2.getWidth() && image1.getHeight() == image2.getHeight()) {
      for (int i = 0; i < image1.getWidth(); i++) {
        for (int j = 0; j < image1.getHeight(); j++) {
          if (image1.getRGB(i, j) != image2.getRGB(i, j)) {
            return false;
          }
        }
      }
    } else {
      return false;
    }
    return true;
  }
  
  @Test
  public void rotateImageTest1() {
    this.setUp();
    BufferedImage sameImage = this.generator.rotateImage(image, 0.0);
    assertTrue(this.areImagesEqual(this.image, sameImage));
  }
  
  @Test
  public void rotateImageTest2() {
    this.setUp();
    BufferedImage nullImage = this.generator.rotateImage(null, 0.0);
    assertNull(nullImage);
  }



}
