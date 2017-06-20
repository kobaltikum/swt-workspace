package org.iMage.iLlustrate.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import org.iMage.iLlustrate.RunWindow;

public class SaveButtonListener implements ActionListener {

  private RunWindow window;
  
  public SaveButtonListener(RunWindow window) {
    this.window = window;
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int result = fileChooser.showSaveDialog(this.window);
    
    if (result == JFileChooser.APPROVE_OPTION) {
      File outputFile = fileChooser.getSelectedFile();
      try {
        ImageIO.write(window.getProcessedImage(), "png", outputFile);
      } catch (IOException i) {
        i.printStackTrace();
      }
    }
  }

}
