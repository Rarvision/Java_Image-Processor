package imageview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Listener for action bound to buttons in the main frame.
 * @author ZHANG Mao
 *
 */
public class PanelActionListener implements ActionListener {

  private Map<String, Runnable> panelActions;
  
  /**
   * Constructor of PanelActionListener class with no argument.
   */
  public PanelActionListener() {
    panelActions = null;
  }

  /**
   * Constructor of PanelActionListener class with a map as an argument.
   * @param panelActions Map of triggers and corresponding result of the listener
   */
  public PanelActionListener(Map<String, Runnable> panelActions) {
    this.panelActions = panelActions;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (panelActions.containsKey(e.getActionCommand())) {
      panelActions.get(e.getActionCommand()).run();
    }
  }

}
