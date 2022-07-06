package images;

/**
 * This class is for applying filter of sharpeness to an image.
 * @author ZHANG Mao
 *
 */
public class Sharpen extends AbstractFilter {
  
  /**
   * The constructor of the Sharpen class. Arguments of the filter are
   * automatically given.
   */
  public Sharpen() {
    
    super(new double[][] {{-0.125, -0.125, -0.125, -0.125, -0.125}, 
      {-0.125, 0.25, 0.25, 0.25, -0.125},
      {-0.125, 0.25, 1, 0.25, -0.125},
      {-0.125, 0.25, 0.25, 0.25, -0.125},
      {-0.125, -0.125, -0.125, -0.125, -0.125}});
      
  }
}
