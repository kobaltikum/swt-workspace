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

  private static final File WALTER_FILE = new File("src/test/resources/walter_no_alpha.png");
  private static final File DICE_FILE = new File("src/test/resources/dices_alpha.png");
  BufferedImage walter;
  BufferedImage dices;
  RandomPointGenerator randmPointGRnWalter;
  RandomPointGenerator randmPointGRnDices;
  TrianglePictureFilter triPicFilterWalter;
  TrianglePictureFilter triPicFilterDices;

  @Before
  public void setUp() throws IOException {
    try {
      this.walter = ImageIO.read(WALTER_FILE);
      this.randmPointGRnWalter =  new RandomPointGenerator(
          this.walter.getWidth(),
          this.walter.getHeight());
      this.triPicFilterWalter = new TrianglePictureFilter(this.randmPointGRnWalter);
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      this.dices = ImageIO.read(DICE_FILE);
      this.randmPointGRnDices =  new RandomPointGenerator(
          this.dices.getWidth(),
          this.dices.getHeight());
      this.triPicFilterDices = new TrianglePictureFilter(this.randmPointGRnDices);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testWalter() throws IOException {
    SimpleDateFormat sdf = new SimpleDateFormat("HHmmss_SSS");
    String time = sdf.format(new Date());
    File outputfile = new File("src/test/resources/walter " + time + ".png");
    ImageIO.write(this.triPicFilterWalter.apply(walter, 500, 30), "png", outputfile);
  }
  
  @Test
  public void testDices() throws IOException {
    SimpleDateFormat sdf = new SimpleDateFormat("HHmmss_SSS");
    String time = sdf.format(new Date());
    File outputfile = new File("src/test/resources/dices " + time + ".png");
    ImageIO.write(this.triPicFilterDices.apply(dices, 1000, 50), "png", outputfile);
  }

}
