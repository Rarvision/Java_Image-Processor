package imageview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Action listener for items in the menu.
 * @author ZHANG Mao
 *
 */
public class MenuActionListener implements ActionListener {

  private Map<String, Runnable> menuActions;
  
  /**
   * Constructor of MenuActionListener with no argument.
   */
  public MenuActionListener() {
    menuActions = null;
  }
  
  /**
   * Constructor of MenuActionListener with a map as an argument.
   * @param menuActions Map of triggers and corresponding result of the listener
   */
  public MenuActionListener(Map<String, Runnable> menuActions) {
    this.menuActions = menuActions;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (menuActions.containsKey(e.getActionCommand())) {
      menuActions.get(e.getActionCommand()).run();
    }
  }

}
