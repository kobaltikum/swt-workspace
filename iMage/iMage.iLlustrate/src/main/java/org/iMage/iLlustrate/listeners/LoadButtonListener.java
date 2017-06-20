package org.iMage.iLlustrate.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.iMage.iLlustrate.GeometrifyWindow;

public class LoadButtonListener implements ActionListener {

  private GeometrifyWindow window;
  
  public LoadButtonListener(GeometrifyWindow window) {
    this.window = window;
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    fileChooser.setFileFilter(new FileNameExtensionFilter(null, "png"));
    int result = fileChooser.showOpenDialog(this.window);
    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      try {
        BufferedImage newImage = ImageIO.read(selectedFile);
        this.window.setPreview(newImage);
        this.window.setImage(newImage);
      }
      catch(IOException f) {
        f.printStackTrace();
      }
    }
  }

}
