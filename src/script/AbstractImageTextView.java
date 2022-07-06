package script;

import java.io.IOException;

/**
 * The abstract view of console-based text view.
 * @author ZHANG Mao
 *
 */
public abstract class AbstractImageTextView implements ImageView {
  
  protected Appendable out;
  
  /**
   * The constructor of the view.
   * @param out the way how output displayed by the view 
   */
  protected AbstractImageTextView(Appendable out) {
    if (out == null) {
      throw new IllegalArgumentException("Invalid output passed to the view..");
    }
    this.out = out;
  }

  @Override
  public void showMessage(String message) {
    try {
      out.append(message);
      out.append(System.lineSeparator());
    } catch (IOException ex) {
      throw new IllegalStateException("Error occurred writing to the view.");
    }
  }

  @Override
  public void showLoadStatus(String fileName) {
    try {
      out.append(fileName);
      out.append(" is successfully loaded.");
      out.append(System.lineSeparator());
    } catch (IOException ex) {
      throw new IllegalStateException("Error occurred writing to the view.");
    }
    
  }

  @Override
  public void showSaveStatus(String fileName) {
    try {
      out.append("The picure is saved as:");
      out.append(System.lineSeparator());
      out.append(fileName);
      out.append(".");
      out.append(System.lineSeparator());
    } catch (IOException ex) {
      throw new IllegalStateException("Error occurred writing to the view.");
    }
  }

  /**
   * Show an separator.
   */
  public void showSeparator() {
    try {
      out.append("------------------------------------------------");
      out.append(System.lineSeparator());
    } catch (IOException ex) {
      throw new IllegalStateException("Error occurred writing to the view.");
    }
  }

}
