package iMage.geometrify;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.iMage.geometrify.RandomPointGenerator;
import org.iMage.geometrify.TrianglePictureFilter;
import org.junit.Before;
import org.junit.Test;

public class TrianglePictureFilterTest {

  private static final File IMAGE_FILE = new File("src/test/resources/walter_no_alpha.png");
  BufferedImage testImage;

  @Before
  public void setUp() throws IOException {
    testImage = null;
    try (ImageInputStream iis = ImageIO.createImageInputStream(IMAGE_FILE);) {
      ImageReader reader = ImageIO.getImageReadersByFormatName("png").next();
      reader.setInput(iis, true);
      ImageReadParam params = reader.getDefaultReadParam();
      testImage = reader.read(0, params);
      reader.dispose();
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test() throws IOException {
    RandomPointGenerator r =  new RandomPointGenerator(testImage.getWidth(), testImage.getHeight());
    TrianglePictureFilter t = new TrianglePictureFilter(r);
    File outputfile = new File("src/test/resources/walter.png");
    ImageIO.write(t.apply(testImage, 500, 30), "png", outputfile);
    //TODO bounding boxen
  }

}
