package imageview;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * A JLabel used to contain image displayed in the main frame.
 * @author ZHANG Mao
 *
 */
public class ImageLabel extends JLabel {
  
  private static final long serialVersionUID = 120L;
  
  /**
   * Constructor of ImageLabel with no argument.
   */
  public ImageLabel() {
    super();
  }
  
  /**
   * Set or reset image contains in this label.
   * @param img image to be displayed
   */
  public void displayImg(BufferedImage img) {
    this.setIcon(new ImageIcon(img));
  }

}
