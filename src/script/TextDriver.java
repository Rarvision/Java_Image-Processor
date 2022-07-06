package script;

import images.ConcreteImageModel;
import images.ImageModel;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * The driver which triggers the start of the image processor.
 * @author ZHANG Mao
 *
 */
public class TextDriver {
  
  /**
   * The main method triggering the start of image processing.
   * @param args the file name of script contains operation orders of 
   *     image processing
   */
  public static void main(String[] args) {
    
    String fileName = args[args.length - 1];
    Readable in;
    
    try {
      in = new FileReader(fileName);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Can not find the file using the given path.");
    }
    
    ImageModel m = new ConcreteImageModel();
    ImageView v = new ImageTextView(System.out);
    ImageController c = new ImageTextController(m, in);
    c.setView(v);
    c.go();
    
  }

}
