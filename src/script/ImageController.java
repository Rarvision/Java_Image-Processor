package script;

/**
 * The interface of the controller.
 * @author ZHANG Mao
 *
 */
public interface ImageController {
  
  /**
   * Set the view for the controller.
   * @param v view to be set
   * @throws IllegalArgumentException for the mismatched view
   */
  void setView(ImageView v) throws IllegalArgumentException;
  
  /**
   * Trigger the start of image processing.
   */
  void go();

}
