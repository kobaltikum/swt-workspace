package org.iMage.iLlustrate.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

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
    int result = fileChooser.showOpenDialog(window);
  }

}
