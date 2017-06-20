package org.iMage.iLlustrate.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.iMage.iLlustrate.GeometrifyWindow;
import org.iMage.iLlustrate.RunWindow;

public class RunButtonListener implements ActionListener {

  private GeometrifyWindow window;
  
  public RunButtonListener(GeometrifyWindow window) {
    this.window = window;
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    new RunWindow(this.window);
  }

}
