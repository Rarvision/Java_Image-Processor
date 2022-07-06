import script.ImageTextView;

/**
 * Mock view of the image processor.
 * 
 * @author ZHANG Mao
 *
 */
public class MockImageTextView extends ImageTextView {
  
  StringBuilder log = (StringBuilder) super.out;

  /**
   * The constructor of the mock view for testing console-based view.
   * 
   * @param log the log for recording activities in the mock views
   */
  public MockImageTextView(StringBuilder log) {
    super(log);
  }

  @Override
  public void showMessage(String message) {
    log.append(message + " is shown.");
    log.append(System.lineSeparator());
  }

  @Override
  public void showLoadStatus(String fileName) {
    log.append(fileName + " is loaded.");
    log.append(System.lineSeparator());

  }

  @Override
  public void showSaveStatus(String fileName) {
    log.append(fileName + " is saved.");
    log.append(System.lineSeparator());

  }

  @Override
  public void showSeparator() {
    log.append("......");
    log.append(System.lineSeparator());

  }
  
  @Override
  public void showStatus(String processName) {
    log.append(processName + " is been processed.");
    log.append(System.lineSeparator());

  }

}
