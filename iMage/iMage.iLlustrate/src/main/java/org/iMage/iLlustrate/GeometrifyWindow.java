
/**
 * 
 */
package org.iMage.iLlustrate;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;

import org.iMage.geometrify.RandomPointGenerator;
import org.iMage.geometrify.TrianglePictureFilter;
import org.iMage.iLlustrate.listeners.IterationSliderListener;
import org.iMage.iLlustrate.listeners.LoadButtonListener;
import org.iMage.iLlustrate.listeners.SampleSliderListener;

/**
 * @author jakobdraeger
 *
 */
public class GeometrifyWindow extends JFrame {
  
  private static final long serialVersionUID = 5298460096625371008L;
  
  private JPanel mainPanel;
  private JPanel picturesCont;
  private JPanel sliderCont;
  private JPanel itSlideCont;
  private JPanel sampSlideCont;
  private JPanel menuCont;

  private JLabel picBeforeCont;
  private JLabel picAfterCont;
  private JLabel itCount;
  private JLabel sampCount;
  
  private JSlider iterationsSlider;
  private JSlider sampleSlider;
  private JButton loadButton;
  private JButton runButton;
  
  private BufferedImage originalImage;
  private BufferedImage previewImage;

  public GeometrifyWindow() {
    this.mainPanel = new JPanel();
    this.picturesCont = new JPanel();
    this.sliderCont = new JPanel();
    this.itSlideCont = new JPanel();
    this.sampSlideCont = new JPanel();
    this.menuCont = new JPanel();

    this.picBeforeCont = new JLabel();
    this.picAfterCont = new JLabel();
    this.itCount = new JLabel("(100)");
    this.sampCount = new JLabel("(30)");
    
    this.iterationsSlider = new JSlider(0, 2000);
    this.sampleSlider = new JSlider(0, 200);
    this.loadButton = new JButton("Load");
    this.runButton = new JButton("Run");
    
    try {
      this.originalImage = ImageIO.read(getClass().getResource("/example.png"));
    }
    catch(IOException e) {
      e.printStackTrace();
    }
    this.initWindow();
    this.setTitle("iLlustrate");
  }
  
  /**
   * Initializes all components and properties of the window.
   */
  private void initWindow() {
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    
    //---  initializing layouts  ---
    this.mainPanel.setLayout(new BorderLayout());
    this.picturesCont.setLayout(new BorderLayout());
    this.picBeforeCont.setLayout(new BorderLayout());
    this.picAfterCont.setLayout(new BorderLayout());
    this.sliderCont.setLayout(new BorderLayout());
    this.itSlideCont.setLayout(new BorderLayout());
    this.sampSlideCont.setLayout(new BorderLayout());
    this.menuCont.setLayout(new FlowLayout());
    
    //---  initializing example pictures  ---
    BufferedImage scaledImage = this.scaleImage(originalImage);
    ImageIcon originalImageIcon = new ImageIcon(scaledImage);
    ImageIcon previewImageIcon = new ImageIcon(this.applyFilter(scaledImage, 100, 30));
    
    this.picBeforeCont.setIcon(originalImageIcon);
    this.picBeforeCont.setBorder(new EmptyBorder(30, 30, 30, 30));
    this.picBeforeCont.setVisible(true);
    
    this.picAfterCont.setIcon(previewImageIcon);
    this.picAfterCont.setBorder(new EmptyBorder(30, 30, 30, 30));
    this.picAfterCont.setVisible(true);
    
    this.picturesCont.add(this.picBeforeCont, BorderLayout.WEST);
    this.picturesCont.add(this.picAfterCont, BorderLayout.EAST);
    this.mainPanel.add(this.picturesCont, BorderLayout.NORTH);
    
    //---  initializing JSliders  ---
    Hashtable lableTableIT = new Hashtable();
    lableTableIT.put(new Integer(0), new JLabel("0"));
    lableTableIT.put(new Integer(2000), new JLabel("2000"));
    this.iterationsSlider.setLabelTable(lableTableIT);
    this.iterationsSlider.setPaintLabels(true);
    this.iterationsSlider.setValue(100);
    
    Hashtable lableTableSMP = new Hashtable();
    lableTableSMP.put(new Integer(0), new JLabel("0"));
    lableTableSMP.put(new Integer(200), new JLabel("200"));
    this.sampleSlider.setLabelTable(lableTableSMP);
    this.sampleSlider.setPaintLabels(true);
    this.sampleSlider.setValue(30);
    
    this.iterationsSlider.setPreferredSize(new Dimension(250, 40));
    this.itSlideCont.add(this.iterationsSlider, BorderLayout.EAST);
    JLabel iterationsText = new JLabel("Iterations");
    iterationsText.setBorder(new EmptyBorder(0, 0, 10, 0));
    this.itSlideCont.add(iterationsText, BorderLayout.WEST);
    this.itCount.setBorder(new EmptyBorder(0, 0, 10, 0));
    this.itSlideCont.add(this.itCount, BorderLayout.CENTER);
    this.sliderCont.add(this.itSlideCont, BorderLayout.NORTH);
    IterationSliderListener itSlider = new IterationSliderListener(this);
    this.iterationsSlider.addChangeListener(itSlider);

    this.sampleSlider.setPreferredSize(new Dimension(250, 40));
    this.sampleSlider.setBorder(new EmptyBorder(5, 0, 0, 0));
    this.sampSlideCont.add(this.sampleSlider, BorderLayout.EAST);
    JLabel samplesText = new JLabel("Samples");
    samplesText.setBorder(new EmptyBorder(0, 5, 10, 0));
    this.sampSlideCont.add(samplesText, BorderLayout.WEST);
    this.sampCount.setBorder(new EmptyBorder(0, 5, 10, 0));
    this.sampSlideCont.add(this.sampCount, BorderLayout.CENTER);
    this.sliderCont.add(this.sampSlideCont, BorderLayout.SOUTH);
    SampleSliderListener sampSlider = new SampleSliderListener(this);
    this.sampleSlider.addChangeListener(sampSlider);
    
    this.sliderCont.setBorder(new EmptyBorder(25, 25, 25, 25));
    this.mainPanel.add(sliderCont, BorderLayout.CENTER);
    
    //---  initializing JButtons  ---
    this.menuCont.add(this.loadButton);
    this.loadButton.addActionListener(new LoadButtonListener(this));
    this.menuCont.add(this.runButton);
    this.loadButton.setEnabled(true);
    this.runButton.setEnabled(true);
    this.mainPanel.add(this.menuCont, BorderLayout.SOUTH);
    
    this.add(mainPanel);
    
    this.setBounds(0, 0, 400, 400);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
    this.setResizable(false);
  }

  /**
   * Set-Method for the iteration count next to the iterations slider.
   * @param iterationCount The Integer to be displayed.
   */
  public void setItCount(int iterationCount) {
    this.itCount.setText("(" + iterationCount + ")");
  }
  
  /**
   * Set-Method for the sample count next to the samples slider.
   * @param sampleCount The Integer to be displayed.
   */
  public void setSampleCount(int sampleCount) {
    this.sampCount.setText("(" + sampleCount + ")");
  }
  
  /**
   * Helping method to scale a BufferedImage to a 150 by 150 image.
   * @param imageToScale The BufferedImage to be scaled.
   * @return The scaled BufferedImage.
   */
  private BufferedImage scaleImage(BufferedImage imageToScale) {
    BufferedImage scaledImage = null;
    if (imageToScale != null) {
        scaledImage = new BufferedImage(150, 150, imageToScale.getType());
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.drawImage(imageToScale, 0, 0, 150, 150, null);
        graphics2D.dispose();
    }
    return scaledImage;
}
 
  /**
   * Helping method to apply the Geometrify-Filter on 
   * @param image The image to be processed.
   * @param numberOfIterations The number of iterations to go through (needed the TrianglePictureFilter#apply).
   * @param numberOfSamples The number of samples to generate per iteration (needed the TrianglePictureFilter#apply).
   * @return The filtered BufferedImage.
   */
  private BufferedImage applyFilter(BufferedImage image, int numberOfIterations, int numberOfSamples) {
    RandomPointGenerator generator = new RandomPointGenerator(image.getWidth(), image.getHeight());
    TrianglePictureFilter filter = new TrianglePictureFilter(generator);
    return filter.apply(image, numberOfIterations, numberOfSamples);
  }
}
