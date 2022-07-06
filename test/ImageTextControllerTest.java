import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;
import script.ImageController;
import script.ImageTextController;
import script.ImageTextView;

/**
 * Test case of the ImageController class using mock model and mock view. 
 * @author ZHANG Mao
 *
 */
public class ImageTextControllerTest {
  private StringBuilder modelLog;
  private StringBuilder viewLog;
  private Readable input;
  private MockImageModel mm;
  private MockImageTextView mv;
  private ImageController ic;
  
  /**
   * Set up the test process.
   */
  @Before
  public void setUp() {
    this.modelLog = new StringBuilder();
    this.viewLog = new StringBuilder();
    this.input = new StringReader("load fakeImage.png" 
        + System.lineSeparator() + "blur" 
        + System.lineSeparator() + "sharpen" 
        + System.lineSeparator() + "sepia" 
        + System.lineSeparator() + "grayscale" 
        + System.lineSeparator() + "dithering" 
        + System.lineSeparator() + "mosaic 2000" 
        + System.lineSeparator() + "save fakeImageBlur.png"
        + System.lineSeparator());
    this.mm = new MockImageModel(modelLog);
    this.mv = new MockImageTextView(viewLog);
    this.ic = new ImageTextController(mm, input);
    this.ic.setView(mv);
  }
  

  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    Appendable a = new FailingAppendable();
    ImageTextView cv = new ImageTextView(a);
    ic.setView(cv);
    ic.go();
  }
  
  @Test
  public void testCorrectInput() {
    StringBuilder expectedModelLog = new StringBuilder();
    
    expectedModelLog.append("fakeImage.png");
    expectedModelLog.append(System.lineSeparator());
    expectedModelLog.append("Blurring...");
    expectedModelLog.append(System.lineSeparator());
    expectedModelLog.append("Sharpening...");
    expectedModelLog.append(System.lineSeparator());
    expectedModelLog.append("Sepia applyting...");
    expectedModelLog.append(System.lineSeparator());
    expectedModelLog.append("Grayscale applying...");
    expectedModelLog.append(System.lineSeparator());
    expectedModelLog.append("Dithering applying...");
    expectedModelLog.append(System.lineSeparator());
    expectedModelLog.append("Mosaic with 2000 seeds applying...");
    expectedModelLog.append(System.lineSeparator());
    expectedModelLog.append("fakeImageBlur.png");
    expectedModelLog.append(System.lineSeparator());
    
    StringBuilder expectedViewLog = new StringBuilder();
    expectedViewLog.append("The image processer is running... is shown.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("......");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("fakeImage.png is loaded.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("blur is been processed.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("sharpen is been processed.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("sepia is been processed.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("grayscale is been processed.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("dithering is been processed.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("mosaic (seeds: 2000) is been processed.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("fakeImageBlur.png is saved.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("......");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Image processing completed! is shown.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("......");
    expectedViewLog.append(System.lineSeparator());
    
    ic.go();
    
    assertEquals(expectedModelLog.toString(), this.modelLog.toString());
    assertEquals(expectedViewLog.toString(), this.viewLog.toString());
  }
  
  @Test
  public void testIllegalLoadFileName() {
    
    StringBuilder expectedModelLog = new StringBuilder();
    StringBuilder expectedViewLog = new StringBuilder();
    
    expectedModelLog.append("fileUnexists");
    expectedModelLog.append(System.lineSeparator());
    
    expectedViewLog.append("The image processer is running... is shown.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("......");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("......");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("!!! OPERATION FAILED: load is shown.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Please check the path then try again. is shown.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("......");
    expectedViewLog.append(System.lineSeparator());
    
    Readable testIn = new StringReader("load fileUnexists" 
        + System.lineSeparator() + "save newFile.png" 
        + System.lineSeparator());
    ImageController testc = new ImageTextController(mm, testIn);
    testc.setView(mv);
    testc.go();
    assertEquals(expectedModelLog.toString(), this.modelLog.toString());
    assertEquals(expectedViewLog.toString(), this.viewLog.toString());
  }
  
  @Test
  public void testIllegalSaveFileName() {
    
    StringBuilder expectedModelLog = new StringBuilder();
    
    expectedModelLog.append("fakeImage.png");
    expectedModelLog.append(System.lineSeparator());
    expectedModelLog.append("newFile.pdf");
    expectedModelLog.append(System.lineSeparator());
    
    StringBuilder expectedViewLog = new StringBuilder();
    expectedViewLog.append("The image processer is running... is shown.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("......");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("fakeImage.png is loaded.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("......");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("!!! OPERATION FAILED: save is shown.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Please check the path then try again. is shown.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("......");
    expectedViewLog.append(System.lineSeparator());
    
    Readable testIn = new StringReader("load fakeImage.png" 
        + System.lineSeparator() + "save newFile.pdf" 
        + System.lineSeparator());
    ImageController testc = new ImageTextController(mm, testIn);
    testc.setView(mv);
    testc.go();
    assertEquals(expectedModelLog.toString(), this.modelLog.toString());
    assertEquals(expectedViewLog.toString(), this.viewLog.toString());
  }
  
  @Test
  public void testIllegalMosaicArgument() {
    
    StringBuilder expectedModelLog = new StringBuilder();
    
    expectedModelLog.append("fakeImage.png");
    expectedModelLog.append(System.lineSeparator());
    
    StringBuilder expectedViewLog = new StringBuilder();
    expectedViewLog.append("The image processer is running... is shown.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("......");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("fakeImage.png is loaded.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("......");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("!!! OPERATION FAILED: mosaic is shown.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("The amount of seeds must be an integer. is shown.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("......");
    expectedViewLog.append(System.lineSeparator());
    
    Readable testIn = new StringReader("load fakeImage.png" 
        + System.lineSeparator() + "mosaic 20.18" 
        + System.lineSeparator() + "save newFile.png"
        + System.lineSeparator());
    ImageController testc = new ImageTextController(mm, testIn);
    testc.setView(mv);
    testc.go();
    assertEquals(expectedModelLog.toString(), this.modelLog.toString());
    assertEquals(expectedViewLog.toString(), this.viewLog.toString());
  }
  
  @Test
  public void testWrongSpell() {
    
    StringBuilder expectedModelLog = new StringBuilder();
    expectedModelLog.append("");
    
    StringBuilder expectedViewLog = new StringBuilder();
    
    expectedViewLog.append("The image processer is running... is shown.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("......");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("......");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("!!! CANNOT RECOGNIZE THE OPERATION is shown.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Please check the grammer then try again. is shown.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("......");
    expectedViewLog.append(System.lineSeparator());
    
    Readable testIn = new StringReader("loaf fakeImage.png" 
        + System.lineSeparator() + "save newFile.png"
        + System.lineSeparator());
    ImageController testc = new ImageTextController(mm, testIn);
    testc.setView(mv);
    testc.go();
    assertEquals(expectedModelLog.toString(), this.modelLog.toString());
    assertEquals(expectedViewLog.toString(), this.viewLog.toString());
  }
  
  @Test
  public void testBlankLine() {
    
    StringBuilder expectedModelLog = new StringBuilder();
    StringBuilder expectedViewLog = new StringBuilder();
    
    expectedModelLog.append("fakeImage.png");
    expectedModelLog.append(System.lineSeparator());
    
    expectedViewLog.append("The image processer is running... is shown.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("......");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("fakeImage.png is loaded.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("......");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("!!! CANNOT RECOGNIZE THE OPERATION is shown.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Please check the grammer then try again. is shown.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("......");
    expectedViewLog.append(System.lineSeparator());
    
    Readable testIn = new StringReader("load fakeImage.png" 
        + System.lineSeparator() + System.lineSeparator()
        + "save newFile.png"
        + System.lineSeparator());
    ImageController testc = new ImageTextController(mm, testIn);
    testc.setView(mv);
    testc.go();
    assertEquals(expectedModelLog.toString(), this.modelLog.toString());
    assertEquals(expectedViewLog.toString(), this.viewLog.toString());
  }
  
  @Test
  public void testNewMethod() {
    
    StringBuilder expectedModelLog = new StringBuilder();
    
    expectedModelLog.append("fakeImage.png");
    expectedModelLog.append(System.lineSeparator());
    expectedModelLog.append("Blurring...");
    expectedModelLog.append(System.lineSeparator());
    expectedModelLog.append("Edge Detection applying...");
    expectedModelLog.append(System.lineSeparator());
    expectedModelLog.append("Histogram equalization applying...");
    expectedModelLog.append(System.lineSeparator());
    expectedModelLog.append("Sharpening...");
    expectedModelLog.append(System.lineSeparator());
    expectedModelLog.append("Sepia applyting...");
    expectedModelLog.append(System.lineSeparator());
    expectedModelLog.append("Grayscale applying...");
    expectedModelLog.append(System.lineSeparator());
    expectedModelLog.append("Dithering applying...");
    expectedModelLog.append(System.lineSeparator());
    expectedModelLog.append("Mosaic with 2000 seeds applying...");
    expectedModelLog.append(System.lineSeparator());
    expectedModelLog.append("fakeImageBlur.png");
    expectedModelLog.append(System.lineSeparator());
    
    StringBuilder expectedViewLog = new StringBuilder();
    expectedViewLog.append("The image processer is running... is shown.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("......");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("fakeImage.png is loaded.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("blur is been processed.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("sharpen is been processed.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("sepia is been processed.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("grayscale is been processed.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("dithering is been processed.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("mosaic (seeds: 2000) is been processed.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("fakeImageBlur.png is saved.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("......");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("Image processing completed! is shown.");
    expectedViewLog.append(System.lineSeparator());
    expectedViewLog.append("......");
    expectedViewLog.append(System.lineSeparator());
    
    Readable newInput = new StringReader("load fakeImage.png" 
        + System.lineSeparator() + "blur" 
        + System.lineSeparator() + "edge-detection" 
        + System.lineSeparator() + "histogram-equalization"
        + System.lineSeparator() + "sharpen" 
        + System.lineSeparator() + "sepia" 
        + System.lineSeparator() + "grayscale" 
        + System.lineSeparator() + "dithering" 
        + System.lineSeparator() + "mosaic 2000" 
        + System.lineSeparator() + "save fakeImageBlur.png"
        + System.lineSeparator());
    this.ic = new ImageTextController(this.mm, newInput);
    this.ic.setView(this.mv);
    ic.go();
    
    assertEquals(expectedModelLog.toString(), this.modelLog.toString());
    assertEquals(expectedViewLog.toString(), this.viewLog.toString());
  }
}
