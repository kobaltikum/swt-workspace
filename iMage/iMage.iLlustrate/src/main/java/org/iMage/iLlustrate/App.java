package org.iMage.iLlustrate;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.iMage.geometrify.RandomPointGenerator;
import org.iMage.geometrify.TrianglePictureFilter;

public class App {
  
  private static GeometrifyWindow window;

  public static void main(String[] args) {
    window = new GeometrifyWindow();
  }
  
  /** Helping method to scale a BufferedImage to a 150 by 150 image.
  * @param imageToScale The BufferedImage to be scaled.
  * @return The scaled BufferedImage.
  */
 static BufferedImage scaleImage(BufferedImage imageToScale) {
   BufferedImage scaledImage = null;
   if (imageToScale != null) {
       scaledImage = new BufferedImage(150, 150, imageToScale.getType());
       Graphics2D graphics2D = scaledImage.createGraphics();
       graphics2D.drawImage(imageToScale, 0, 0, 150, 150, null);
       graphics2D.dispose();
   }
   return scaledImage;
 }

 /**
  * Helping method to apply the Geometrify-Filter on  a BufferedImage.
  * @param image The image to be processed.
  * @param numberOfIterations The number of iterations to go through (needed the TrianglePictureFilter#apply).
  * @param numberOfSamples The number of samples to generate per iteration (needed the TrianglePictureFilter#apply).
  * @return The filtered BufferedImage.
  */
 static BufferedImage applyFilter(BufferedImage image, int numberOfIterations, int numberOfSamples) {
   RandomPointGenerator generator = new RandomPointGenerator(image.getWidth(), image.getHeight());
   TrianglePictureFilter filter = new TrianglePictureFilter(generator);
   return filter.apply(image, numberOfIterations, numberOfSamples);
 }

}
