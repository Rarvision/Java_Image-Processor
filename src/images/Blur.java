package images;

/**
 * This class is for applying filter of blur to an image.
 * @author ZHANG Mao
 *
 */
public class Blur extends AbstractFilter {
  
  /**
   * The constructor of the Blur class. Arguments of the filter are 
   * automatically given.
   */
  public Blur() {
    super(new double[][] {{0.0625, 0.125, 0.0625}, 
      {0.125, 0.25, 0.0625}, {0.0625, 0.125, 0.0625}});
  }
  

}
