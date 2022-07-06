package imageview;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

/**
 * Key event listener for items in the menu.
 * @author ZHANG Mao
 *
 */
public class MenuShortcutListener implements KeyListener {
  
  private Map<Integer, Runnable> keyPressedMap;
  
  /**
   * Constructor of MenuShortcutListener with a map as an argument.
   * @param keyPressedMap Map of triggers and corresponding result of the listener
   */
  public MenuShortcutListener(Map<Integer, Runnable> keyPressedMap) {
    this.keyPressedMap = keyPressedMap;
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.isControlDown() && keyPressedMap.containsKey(e.getKeyCode())) {
      keyPressedMap.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

}
