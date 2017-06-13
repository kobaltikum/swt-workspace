/**
 * 
 */
package org.iMage.iLlustrate;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * @author jakobdraeger
 *
 */
public class GeometrifyWindow extends JFrame {
  /**
   * 
   */
  private static final long serialVersionUID = 5298460096625371008L;

  public GeometrifyWindow() {
    this.initWindow();
  }
  
  /**
   * Initializes all components and properties of the window.
   */
  private void initWindow() {
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.getContentPane().setLayout(new BorderLayout());
    this.setBounds(0, 0, 400, 400);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }

}
