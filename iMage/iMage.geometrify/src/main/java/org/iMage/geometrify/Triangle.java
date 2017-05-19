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
    assert (!(a.equals(b)));
    assert (!(b.equals(c)));
    assert (!(a.equals(c)));
    this.pointA = a;
    this.pointB = b;
    this.pointC = c;
  }

  @Override
  public boolean isInsidePrimitive(Point p) {
    boolean b1 = this.getCrossProd(p, this.pointA, this.pointB) < 0.0f;
    boolean b2 = this.getCrossProd(p, this.pointB, this.pointC) < 0.0f;
    boolean b3 = this.getCrossProd(p, this.pointC, this.pointA) < 0.0f;

    return ((b1 == b2) && (b2 == b3));
  }

  /**
   * Helping method to calculate the cross-product between two points (vectors).
   * @param a Point A.
   * @param b Point B.
   * @return The int cross-product.
   */
  private float getCrossProd(Point a, Point b, Point c) {
    return (a.x - c.x) * (b.y - c.y) - (b.x - c.x) * (a.y - c.y);
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
