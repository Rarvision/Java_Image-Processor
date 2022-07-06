package imageview;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

/**
 * Generate a checker board pattern.
 * @author ZHANG Mao
 *
 */
public class CheckerBoardGenerator implements GraphicGenerator {
  
  private int height;
  private int squaresNumber;
  private Color firstColor;
  private Color secondColor;
  
  /**
   * Constructor of CheckerBoardGenerator class.
   * @param height expected height of the result graph
   * @param squaresNumber number of squares of each side
   * @param firstColor first color in the checker board pattern
   * @param secondColor second color in the checker board pattern
   */
  public CheckerBoardGenerator(int height, int squaresNumber, Color firstColor, Color secondColor) {
    
    if (squaresNumber > height) {
      throw new IllegalArgumentException("SquaresNumber must smaller than Height");
    }
    this.height = height;
    this.squaresNumber = squaresNumber;
    this.firstColor = firstColor;
    this.secondColor = secondColor;
  }

  @Override
  public BufferedImage draw() {

    int sideLength = height / squaresNumber;

    BufferedImage pattern = new BufferedImage(sideLength * 2, sideLength * 2, 1);
    Graphics2D patterng2d = pattern.createGraphics();
    patterng2d.setColor(firstColor);
    patterng2d.fill(new Rectangle(sideLength, 0, sideLength, sideLength));
    patterng2d.fill(new Rectangle(0, sideLength, sideLength, sideLength));

    patterng2d.setColor(secondColor);
    patterng2d.fill(new Rectangle(0, 0, sideLength, sideLength));
    patterng2d.fill(new Rectangle(sideLength, sideLength, sideLength, sideLength));

    TexturePaint tp = new TexturePaint(pattern,
        new Rectangle(0, 0, pattern.getWidth(), pattern.getHeight()));

    BufferedImage bimg = new BufferedImage(sideLength * squaresNumber, sideLength * squaresNumber,
        1);
    Graphics2D g2d = bimg.createGraphics();

    g2d.setPaint(tp);
    g2d.fill(new Rectangle(0, 0, bimg.getWidth(), bimg.getHeight()));

    return bimg;

  }

}
