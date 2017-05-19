package iMage.geometrify;

import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.iMage.geometrify.RandomPointGenerator;
import org.junit.Before;
import org.junit.Test;

public class RandomPointGenTest {
  RandomPointGenerator generator;

  @Before
  public void setUp() throws Exception {
    this.generator = new RandomPointGenerator(40, 50);
  }

  @Test
  public void test() {
    for (int i = 0; i < 10; i++) {
      Point p = generator.nextPoint();
      assertTrue(p.getX() < 40.0);
      assertTrue(p.getY() < 50.0);
    }
  }

}
