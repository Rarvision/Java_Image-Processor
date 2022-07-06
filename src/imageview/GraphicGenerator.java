package imageview;

import java.awt.image.BufferedImage;

/**
 * Interface which presents graphic generator for users to draw simple graphs.
 * @author ZHANG Mao
 *
 */
public interface GraphicGenerator {

  /**
   * Draw a graphic on the screen according to user's setting.
   * 
   * @return Image to display
   */
  public BufferedImage draw();
}
