package script;

import images.ImageModel;

/**
 * Abstract controller of this image processor.
 * @author ZHANG Mao
 *
 */
public abstract class AbstractImageController implements ImageController {

  protected final Readable in;
  protected ImageModel im;

  /**
   * The constructor of the controller.
   * 
   * @param m the model of this image processor
   * @param in the source of input to use
   */
  public AbstractImageController(ImageModel m, Readable in) {
    this.in = in;
    this.im = m;
  }
  
  @Override
  public void go() {
    
  }

}
