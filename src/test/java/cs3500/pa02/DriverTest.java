package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * represents the class to test the main method in the driver
 */
public class DriverTest {

  /**
   * test to see if IOException is thrown when the command line arguments do not have 3 arguments
   */
  @Test
  public void testMainWithMissingArguments() {
    String[] args = {
        "/src/test/resources/input", "not_working_flag"};
    assertThrows(IOException.class, () -> Driver.main(args));
  }

  /**
   * test to see if IOException is thrown when the given path is invalid
   */
  @Test
  public void testMainWithInvalidPath() {
    String[] args = {
        "invalid_path",
        "fileName",
        "/path/to/output/file"
    };
    assertThrows(IOException.class, () -> Driver.main(args));
  }

  /**
   * test to see if IOException is thrown when the given ordering flag is invalid
   */
  @Test
  public void testMainWithInvalidOrderingFlag() {
    String[] args = {
        "/src/test/java/input",
        "1",
        "/path/to/output/file"
    };
    assertThrows(IOException.class, () -> Driver.main(args));
  }
}
