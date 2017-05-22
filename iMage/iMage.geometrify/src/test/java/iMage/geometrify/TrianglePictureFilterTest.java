package iMage.geometrify;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.iMage.geometrify.RandomPointGenerator;
import org.iMage.geometrify.TrianglePictureFilter;
import org.junit.Before;
import org.junit.Test;

public class TrianglePictureFilterTest {

  private static final File IMAGE_FILE = new File("src/test/resources/walter_no_alpha.png");
  BufferedImage testImage;
  RandomPointGenerator randmPointGRn;
  TrianglePictureFilter triPicFilter;

  @Before
  public void setUp() throws IOException {
    try {
      this.testImage = ImageIO.read(IMAGE_FILE);
      this.randmPointGRn =  new RandomPointGenerator(
          this.testImage.getWidth(),
          this.testImage.getHeight());
      this.triPicFilter = new TrianglePictureFilter(this.randmPointGRn);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void test() throws IOException {
    SimpleDateFormat sdf = new SimpleDateFormat("HHmmss_SSS");
    String time = sdf.format(new Date());
    File outputfile = new File("src/test/resources/walter" + time + ".png");
    ImageIO.write(this.triPicFilter.apply(testImage, 500, 30), "png", outputfile);
  }

}
