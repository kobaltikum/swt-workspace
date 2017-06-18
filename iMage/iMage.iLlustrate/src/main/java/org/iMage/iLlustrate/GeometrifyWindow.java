
/**
 * 
 */
package org.iMage.iLlustrate;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;

import org.iMage.iLlustrate.listeners.IterationSliderListener;
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
    
    this.initWindow();
    this.setTitle("iLlustrate");
  }
  
  /**
   * Initializes all components and properties of the window.
   */
  private void initWindow() {
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    //initializing layouts
    this.mainPanel.setLayout(new BorderLayout());
    this.picturesCont.setLayout(new BorderLayout());
    this.picBeforeCont.setLayout(new BorderLayout());
    this.picAfterCont.setLayout(new BorderLayout());
    this.sliderCont.setLayout(new BorderLayout());
    this.itSlideCont.setLayout(new BorderLayout());
    this.sampSlideCont.setLayout(new BorderLayout());
    this.menuCont.setLayout(new FlowLayout());
    //--TEST--
    ImageIcon testimage1 = new ImageIcon(getClass().getResource("/swt1test1.png"));
    ImageIcon testimage2 = new ImageIcon(getClass().getResource("/swt1test2.png"));
    //--    --
    this.picBeforeCont.setIcon(testimage1);
    this.picBeforeCont.setBorder(new EmptyBorder(30, 30, 30, 30));
    this.picBeforeCont.setVisible(true);
    
    this.picAfterCont.setIcon(testimage2);
    this.picAfterCont.setBorder(new EmptyBorder(30, 30, 30, 30));
    this.picAfterCont.setVisible(true);
    
    this.picturesCont.add(this.picBeforeCont, BorderLayout.WEST);
    this.picturesCont.add(this.picAfterCont, BorderLayout.EAST);
    this.mainPanel.add(this.picturesCont, BorderLayout.NORTH);
    
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
    
    this.menuCont.add(this.loadButton);
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
 }
