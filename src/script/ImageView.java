package script;

/**
 * The interface of the view.
 * @author ZHANG Mao
 *
 */
public interface ImageView {
  
  /**
   * Show massage based on certain given text.
   * @param message certain text given by users
   */
  public void showMessage(String message);
  
  /**
   * Show massage of successfully loaded an image.
   * @param fileName the file name of the loaded image
   */
  public void showLoadStatus(String fileName);
  
  /**
   * Show massage of successfully saved an image. 
   * @param fileName the file name of the saved image
   */
  public void showSaveStatus(String fileName);
}
