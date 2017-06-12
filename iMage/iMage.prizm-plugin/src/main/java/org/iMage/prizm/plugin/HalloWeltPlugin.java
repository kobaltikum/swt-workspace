package org.iMage.prizm.plugin;

import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.iMage.plugins.JmjrstPlugin;
import org.iMage.plugins.PluginPriority;
import org.jis.Main;
import org.kohsuke.MetaInfServices;

/**
 * Hello world!
 *
 */
@MetaInfServices(JmjrstPlugin.class)
public class HalloWeltPlugin extends JmjrstPlugin
{

    private org.jis.Main main;
    private PluginPriority priority;

    /**
     * Sets the priority of this plugin.
     * @param priority The priority to be set.
     */
    public void setPriority(PluginPriority priority) {
      this.priority = priority;
    }

    /**
     * Returns the priority of this plugin.
     * @return The PluginPriority priority.
     */
    public PluginPriority getPriority() {
      return this.priority;
    }

    @Override
    public String getMenuText() {
      return "HalloWeltPlugin";
    }

    @Override
    public String getName() {
      return "HalloWeltPlugin";
    }

    @Override
    public void init(Main main) {
      this.main = main;
      System.out.println("iMage: Ihr freundlicher Nutzerdatensammler seit 2016!");
    }

    @Override
    public void run() {
      System.err.println("Ueberwachung laeuft seit " + new Date().toString());
    }

    @Override
    public boolean isConfigurable() {
      return true;
    }

    @Override
    public void configure() {
      JFrame configFrame = new JFrame(this.getName());
      JLabel name = new JLabel(this.getName(), SwingConstants.NORTH_WEST);
      JLabel priority = new JLabel(this.getPriority().name(), SwingConstants.NORTH_EAST);
      JLabel user = new JLabel("Ueberwache Nutzer " + System.getProperty("user.name") + "!", SwingConstants.SOUTH_EAST);
      
      configFrame.setSize(300, 150);
      configFrame.setLocationRelativeTo(this.main);
      configFrame.add(name);
      configFrame.add(priority);
      configFrame.add(user);
      configFrame.setVisible(true);
    }
}
