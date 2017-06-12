package org.iMage.TestPlugIn;

import org.iMage.plugins.JmjrstPlugin;
import org.iMage.plugins.PluginPriority;
import org.jis.Main;
import org.kohsuke.MetaInfServices;

/**
 * Hello world!
 *
 */
@MetaInfServices
public class TestPlugIn extends JmjrstPlugin 
{

  public void setPriority(PluginPriority priority) {
    // TODO Auto-generated method stub
    
  }

  public PluginPriority getPriority() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getMenuText() {
    // TODO Auto-generated method stub
    return "TestPlugIn";
  }

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void init(Main main) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void run() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean isConfigurable() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void configure() {
    // TODO Auto-generated method stub
    
  }
}
