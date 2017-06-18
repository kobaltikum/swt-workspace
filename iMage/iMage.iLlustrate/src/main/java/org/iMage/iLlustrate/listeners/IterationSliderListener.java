package org.iMage.iLlustrate.listeners;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.iMage.iLlustrate.GeometrifyWindow;

public class IterationSliderListener implements ChangeListener {
  
  private GeometrifyWindow window;
  
  public IterationSliderListener(GeometrifyWindow window) {
    this.window = window;
  }
  
  @Override
  public void stateChanged(ChangeEvent e) {
    JSlider iterationSlider = (JSlider) e.getSource();
    int value = iterationSlider.getValue();
    window.setItCount(value);
  }
}
