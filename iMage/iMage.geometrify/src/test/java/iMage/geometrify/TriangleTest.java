package iMage.geometrify;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.Point;

import org.iMage.geometrify.Triangle;
import org.junit.Before;
import org.junit.Test;

public class TriangleTest {

  private Triangle testTriangle;
  private final Point pointA = new Point(0, 0);
  private final Point pointB = new Point(9, 0);
  private final Point pointC = new Point(0, 9);
  private final Color color1 = new Color(84, 157, 232);
  private final Color color2 = new Color(101, 232, 84);

  /**
   * Sets up the test class.
   */
  @Before
  public void setUp() {
    try {
      this.testTriangle = new Triangle(new Point(5, 5), new Point(5, 5), new Point(0, 3));
    } catch (AssertionError e) {
      assertTrue(true);
    }
    try {
      this.testTriangle = new Triangle(new Point(0, 0), new Point(0, 0), new Point(0, 3));
    } catch (AssertionError e) {
      assertTrue(true);
    }
    this.testTriangle = new Triangle(pointA, pointB, pointC);
  }

  @Test
  public void testIsInsidePrim() {
    assertTrue(this.testTriangle.isInsidePrimitive(new Point(1, 1)));
    assertTrue(this.testTriangle.isInsidePrimitive(new Point(0, 0)));
    assertTrue(this.testTriangle.isInsidePrimitive(new Point(9, 0)));
    assertFalse(this.testTriangle.isInsidePrimitive(new Point(0, -1)));
    assertFalse(this.testTriangle.isInsidePrimitive(new Point(9, 9)));
  }

  @Test
  public void testBoundingBox() {
    assertEquals(pointC, this.testTriangle.getBoundingBox().getLowerRightCorner());
    assertEquals(pointA, this.testTriangle.getBoundingBox().getUpperLeftCorner());
  }

  @Test
  public void testGetSetter() {
    assertNotEquals(color1, color2);
    this.testTriangle.setColor(color1);
    assertEquals(color1, this.testTriangle.getColor());
    this.testTriangle.setColor(color2);
    assertEquals(color2, this.testTriangle.getColor());
  }

}
