package org.iMage.iLlustrate.listeners;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.iMage.iLlustrate.GeometrifyWindow;

public class SampleSliderListener implements ChangeListener {
  
  private GeometrifyWindow window;
  
  public SampleSliderListener(GeometrifyWindow window) {
    this.window = window;
  }
  
  @Override
  public void stateChanged(ChangeEvent e) {
    JSlider sampleSlider = (JSlider) e.getSource();
    int value = sampleSlider.getValue();
    window.setSampleCount(value);
  }
}
