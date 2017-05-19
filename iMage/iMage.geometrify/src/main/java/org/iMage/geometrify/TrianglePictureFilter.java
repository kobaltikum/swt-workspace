package org.iMage.geometrify;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class TrianglePictureFilter extends AbstractPrimitivePictureFilter {
  public TrianglePictureFilter(IPointGenerator pointGenerator) {
    super(pointGenerator);
  }

  @Override
  protected Color calculateColor(BufferedImage image, IPrimitive primitive) {
    /*
     * YOUR SOLUTION HERE
     */
    return null;
  }

  @Override
  public BufferedImage apply(BufferedImage image, int numberOfIterations, int numberOfSamples) {
    /*
     * YOUR SOLUTION HERE
     */
    return null;
  }

  @Override
  protected IPrimitive generatePrimitive() {
    /*
     * YOUR SOLUTION HERE
     */
    return null;
  }

  @Override
  protected int calculateDifference(BufferedImage orign, BufferedImage curr, IPrimitive primitive) {
    /*
     * YOUR SOLUTION HERE
     */
    return 0;
  }

  @Override
  protected void addToImage(BufferedImage image, IPrimitive primitive) {
    /*
     * YOUR SOLUTION HERE
     */
  }
}
