package images;

/**
 * This class is for converting a color image into a sepia-toned image.
 * @author ZHANG Mao
 *
 */
public class Sepia extends AbstractTransformer {
  
  /**
   * The constructor of the Sepia class. Arguments of the converter are
   * automatically given.
   */
  public Sepia() {
    super(new double[][] {{0.393, 0.769, 0.189},
      {0.349, 0.686, 0.168},
      {0.272, 0.534, 0.131}});
  }
}
