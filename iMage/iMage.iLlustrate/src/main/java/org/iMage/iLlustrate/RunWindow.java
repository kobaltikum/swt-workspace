package org.iMage.iLlustrate;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.iMage.iLlustrate.listeners.SaveButtonListener;

public class RunWindow extends JFrame {
  
  private static final long serialVersionUID = 1418611951272454118L;
  
  private GeometrifyWindow window;
  private BufferedImage image;
  private JPanel optionsMenu;
  private JButton saveButton;
  private JCheckBox updateBox;
  private JLabel updatesLabel;
  private JLabel picture;
  
  public RunWindow(GeometrifyWindow window) {
    this.window = window;
    
    this.image = window.getImage();
    this.image = App.applyFilter(this.image, window.getItCount(), window.getSamplesCount());
    this.picture = new JLabel();
    this.picture.setBounds(0, 0, this.image.getWidth(), this.image.getHeight());
    this.picture.setIcon(new ImageIcon(this.image));
    
    this.saveButton = new JButton("Save");
    this.optionsMenu = new JPanel();
    this.optionsMenu.setLayout(new BorderLayout());
    this.updateBox = new JCheckBox();
    this.optionsMenu.add(this.updateBox, BorderLayout.WEST);
    this.saveButton = new JButton("Save");
    this.saveButton.addActionListener(new SaveButtonListener(this));
    this.optionsMenu.add(this.saveButton, BorderLayout.EAST);
    this.updatesLabel = new JLabel("Continuous Updates");
    this.optionsMenu.add(this.updatesLabel, BorderLayout.CENTER);
    
    this.setTitle(window.getImageTitle());
    this.setResizable(false);
    this.setLayout(new BorderLayout());
    this.add(this.picture, BorderLayout.CENTER);
    this.add(optionsMenu, BorderLayout.NORTH);
    this.setBounds(0, 0, this.image.getWidth(), this.image.getHeight() + 20);
    this.picture.setVisible(true);
    this.optionsMenu.setVisible(true);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
    //this.displayImage();
  }

  private void displayImage() {
    boolean contUpdates = true;
    if (contUpdates) {
      BufferedImage tempImage = this.image;
      for (int i = 2; i <= this.window.getItCount(); i++) {
        tempImage = App.applyFilter(this.image, i, window.getSamplesCount());
        this.picture.setIcon(new ImageIcon(tempImage));
        this.add(this.picture, BorderLayout.CENTER);
        this.picture.setVisible(true);
        this.setVisible(true);
      }
    }
  }

  /**
   * Get-Method to return the processed picture.
   * @return The BufferedImage, on which the filter Geometrify is applied on.
   */
  public BufferedImage getProcessedImage() {
    return this.image;
  }

}
