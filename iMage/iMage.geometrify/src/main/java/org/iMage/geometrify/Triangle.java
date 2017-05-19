package org.iMage.geometrify;

import java.awt.Color;
import java.awt.Point;

/**
 * A triangle.
 *
 * @author Jakob Dräger
 * @version 1.0
 */
public class Triangle implements IPrimitive {

  private Point pointA;
  private Point pointB;
  private Point pointC;
  private Color color;

  /**
   * Creates a new triangle from the given vertices.
   *
   * @param a
   *          the first vertex
   * @param b
   *          the second vertex
   * @param c
   *          the third vertex
   */
  public Triangle(Point a, Point b, Point c) {
    this.pointA = a;
    this.pointB = b;
    this.pointC = c;
  }

  @Override
  public boolean isInsidePrimitive(Point p) {
    int c1 = this.getCrossProd(this.pointA, p);
    int c2 = this.getCrossProd(this.pointB, p);
    int c3 = this.getCrossProd(this.pointC, p);
    return (c1 > 0 && c2 > 0 && c3 > 0) || (c1 < 0 && c2 < 0 && c3 < 0);
  }

  private int getCrossProd(Point a, Point b) {
    return (a.x * b.y) - (a.y * b.x);
  }

  @Override
  public BoundingBox getBoundingBox() {
    return new BoundingBox(pointA, pointC);
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public void setColor(Color c) {
    this.color = c;
  }
}
