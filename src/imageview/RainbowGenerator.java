package imageview;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Generate a rainbow pattern.
 * 
 * @author ZHANG Mao
 *
 */
public class RainbowGenerator implements GraphicGenerator {
  
  private int width;
  private int height;
  private boolean isVertical;
  
  /**
   * Constructor of RainbowGenerator class.
   * @param width expected width of the result graph
   * @param height expected height of the result graph
   * @param isVertical true if wanting to draw a vertical rainbow
   */
  public RainbowGenerator(int width, int height, boolean isVertical) {
    this.width = width;
    this.height = height;
    this.isVertical = isVertical;
  }

  @Override
  public BufferedImage draw() {

    Color[] rainbow = new Color[] { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE,
        new Color(102, 0, 153) };
    BufferedImage bimg = new BufferedImage(width, height, 1);
    Graphics2D g2d = bimg.createGraphics();

    if (isVertical) {
      int stripeLen = width / 6;
      for (int i = 0; i < 6; i++) {
        g2d.setColor(rainbow[i]);
        Rectangle2D rect = new Rectangle(i * stripeLen, 0, stripeLen, height);
        g2d.draw(rect);
        g2d.fill(rect);
      }
      return bimg;

    } else {

      int stripeLen = height / 6;
      for (int i = 0; i < 6; i++) {
        g2d.setColor(rainbow[i]);
        Rectangle2D rect = new Rectangle(0, i * stripeLen, width, stripeLen);
        g2d.draw(rect);
        g2d.fill(rect);
      }
      return bimg;
    }
  }

}
