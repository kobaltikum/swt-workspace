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
    if ((!(a.equals(b))) && (!(b.equals(c))) && (!(a.equals(c)))) {
      this.pointA = a;
      this.pointB = b;
      this.pointC = c;
    }
  }

  
  private double area(double x1, double y1, double x2, double y2, double x3, double y3) {
    return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
  }
  
  @Override
  public boolean isInsidePrimitive(Point p) {
    double a = area(this.pointA.getX(),
        this.pointA.getY(),
        this.pointB.getX(),
        this.pointB.getY(),
        this.pointC.getX(),
        this.pointC.getY());

    /* Calculate area of triangle PBC */
    double a1 = area(p.getX(), p.getY(),
        this.pointB.getX(), this.pointB.getY(),
        this.pointC.getX(), this.pointC.getY());

    /* Calculate area of triangle PAC */
    double a2 = area(this.pointA.getX(), this.pointA.getY(),
        p.getX(), p.getY(),
        this.pointC.getX(), this.pointC.getY());

    /* Calculate area of triangle PAB */
    double a3 = area(this.pointA.getX(), this.pointA.getY(),
        this.pointB.getX(), this.pointB.getY(),
        p.getX(), p.getY());

    /* Check if sum of A1, A2 and A3 is same as A */
    return (a == a1 + a2 + a3);
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

  /**
   * Creates an Array with all three x coordinates of this triangle to simplify
   * the creation of a polygon of this triangle.
   * 
   * @return The array containing all x coordinates.
   */
  public int[] getXCoords() {
    int[] xcoords = { this.pointA.x, this.pointB.x, this.pointC.x };
    return xcoords;
  }

  /**
   * Creates an Array with all three y coordinates of this triangle to simplify
   * the creation of a polygon of this triangle.
   * 
   * @return The array containing all y coordinates.
   */
  public int[] getYCoords() {
    int[] ycoords = { this.pointA.y, this.pointB.y, this.pointC.y };
    return ycoords;
  }


}
