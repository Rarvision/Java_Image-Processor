package imageview;

import java.awt.LayoutManager;
import javax.swing.JFrame;
import javax.swing.JLabel;
import script.ImageView;

/**
 * Abstract class for GUI-based view.
 * @author ZHANG Mao
 *
 */
public abstract class AbstractImageGuiView extends JFrame implements ImageView {

  private static final long serialVersionUID = 2890871L;

  protected JLabel messageLabel;

  /**
   * Constructor of AbstractImageGuiView class.
   * @param width initial width of the frame
   * @param height initial height of the frame
   * @param defaultCloseOperation action performed when the x button is clicked
   * @param manager layout of the frame
   */
  protected AbstractImageGuiView(int width, int height, int defaultCloseOperation,
      LayoutManager manager) {
    super("Image Processor");
    setSize(width, height);
    setDefaultCloseOperation(defaultCloseOperation);
    setLayout(manager);

    messageLabel = new JLabel();
  }

  @Override
  public void showMessage(String message) {
    messageLabel.setText(message);

  }

  /**
   * Give the focus back to the main frame.
   */
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

}
