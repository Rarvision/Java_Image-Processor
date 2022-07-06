package imageview;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

/**
 * Listener for keyEvent bound to buttons in the main frame.
 * @author ZHANG Mao
 *
 */
public class ButtonShortcutListener implements KeyListener {
  
  private Map<Integer, Runnable> keyPressedMap;
  
  /**
   * Constructor of ButtonShortcutListener class with no argument.
   */
  public ButtonShortcutListener() {
    keyPressedMap = null;
  }

  /**
   * Constructor of ButtonShortcutListener with a map as an argument.
   * @param keyPressedMap Map of triggers and corresponding result of the listener
   */
  public ButtonShortcutListener(Map<Integer, Runnable> keyPressedMap) {
    this.keyPressedMap = keyPressedMap;
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.isAltDown() 
        && keyPressedMap.containsKey(e.getKeyCode())) {
      keyPressedMap.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

}
