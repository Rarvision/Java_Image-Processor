package imageview;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Generate a nation flag.
 * @author ZHANG Mao
 *
 */
public class FlagGenerator implements GraphicGenerator {
  
  private int height;
  private String country;
  
  /**
   * Constructor of FlagGenerator class.
   * @param height expected height of the generated nation flag
   * @param country country of the flag
   */
  public FlagGenerator(int height, String country) {
    this.height = height;
    this.country = country;
  }

  @Override
  public BufferedImage draw() {
    if ("Norway".equals(country)) {
      int width = 22 * height / 16;

      BufferedImage bimg = new BufferedImage(width, height, 1);
      Graphics2D g2d = bimg.createGraphics();
      g2d.setColor(new Color(186, 12, 47));
      g2d.fill(new Rectangle(0, 0, width, height));
      g2d.setColor(Color.WHITE);
      g2d.fill(new Rectangle(6 * width / 22, 0, 4 * width / 22, height));
      g2d.fill(new Rectangle(0, 6 * height / 16, width, 4 * height / 16));
      g2d.setColor(new Color(0, 32, 91));
      g2d.fill(new Rectangle(7 * width / 22, 0, 2 * width / 22, height));
      g2d.fill(new Rectangle(0, 7 * height / 16, width, 2 * height / 16));

      return bimg;
    } else if ("Greece".equals(country)) {
      int width = 3 * height / 2;

      BufferedImage bimg = new BufferedImage(width, height, 1);
      Graphics2D g2d = bimg.createGraphics();
      g2d.setColor(new Color(0, 91, 174));
      g2d.fill(new Rectangle(0, 0, width, height));
      g2d.setColor(Color.WHITE);
      g2d.fill(new Rectangle(10 * width / 27, height / 9, 17 * width / 27 + 1, height / 9));
      g2d.fill(new Rectangle(10 * width / 27, height / 3, 17 * width / 27 + 1, height / 9));
      g2d.fill(new Rectangle(0, 5 * height / 9, width, height / 9));
      g2d.fill(new Rectangle(0, 7 * height / 9, width, height / 9));
      g2d.fill(new Rectangle(4 * width / 27, 0, 2 * width / 27, 5 * height / 9));
      g2d.fill(new Rectangle(0, 2 * height / 9, 10 * width / 27, height / 9));

      return bimg;
    } else if ("Switzerland".equals(country)) {
      int width = height;

      BufferedImage bimg = new BufferedImage(width, height, 1);
      Graphics2D g2d = bimg.createGraphics();
      g2d.setColor(new Color(218, 41, 28));
      g2d.fill(new Rectangle(0, 0, width, height));
      g2d.setColor(Color.WHITE);
      g2d.fill(new Rectangle(13 * width / 32, 6 * height / 32, 6 * width / 32, 20 * height / 32));
      g2d.fill(new Rectangle(6 * width / 32, 13 * height / 32, 20 * width / 32, 6 * height / 32));

      return bimg;
    }

    return null;
  }

}
