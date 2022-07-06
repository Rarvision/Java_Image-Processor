package script;

import images.ConcreteImageModel;
import images.ImageModel;
import imageview.ImageGuiView;

/**
 * Driver for the GUI-based image processor.
 * @author ZHANG Mao
 *
 */
public class GuiDriver {

  /**
   * The main method triggering the start of image processing.
   * @param args console arguments
   */
  public static void main(String[] args) {
    
    ImageModel m = new ConcreteImageModel();
    ImageView v = new ImageGuiView();
    ImageController c = new ImageGuiController(m);
    
    c.setView(v);
    
  }

}
