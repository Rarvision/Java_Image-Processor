package images;

/**
 * This class is for converting a color image into a gray scale image.
 * @author ZHANG Mao
 *
 */
public class Grayscale extends AbstractTransformer {
  
  /**
   * The constructor of the Grayscale class. Arguments of the converter are
   * automatically given.
   */
  public Grayscale() {
    super(new double[][] {{0.2126, 0.7152, 0.0722},
      {0.2126, 0.7152, 0.0722},
      {0.2126, 0.7152, 0.0722}});
  }
}
