package org.iMage.geometrify;
//TODO commenten
import java.awt.Point;

/**
 * Provides an infinite source of points at random coordinates within a given
 * range.
 *
 * @author Dominic Ziegler
 * @version 1.0
 */
public class RandomPointGenerator implements IPointGenerator {
  
  private int width;
  private int height;
  
  /**
   * Constructs the generator for points within the specified coordinate space.
   *
   * @param width
   *          the maximum x coordinate
   * @param height
   *          the maximum y coordinate
   */
  public RandomPointGenerator(int width, int height) {
    this.width = width;
    this.height = height;
  }

  @Override
  public Point nextPoint() {
    int randomWidth = (int) (Math.random() * (this.width - 1));
    int randomHeight = (int) (Math.random() * (this.height - 1));
    return new Point(randomWidth, randomHeight);
  }
}
