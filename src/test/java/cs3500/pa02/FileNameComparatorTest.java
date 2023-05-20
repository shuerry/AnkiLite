package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * represents the class to test the FileNameComparator class
 */
class FileNameComparatorTest {
  /**
   * input directory
   */
  static final String input = "src/test/resources/input";
  FileNameComparator fnc;
  Path path1;
  Path path2;
  Path path3;

  /**
   * initializes the FileNameComparator and the paths before each tests
   */
  @BeforeEach
  public void setup() {
    fnc = new FileNameComparator();
    path1 = Path.of(input + "/arrays.md");
    path2 = Path.of(input + "/vectorNotes/vectors.md");
    path3 = Path.of(input + "/normal1.txt");
  }

  /**
   * test if the compare method is working
   */
  @Test
  public void compare() {
    FileNameComparator fnc = new FileNameComparator();
    assertEquals(0, fnc.compare(path1, path1));
    assertEquals(-13, fnc.compare(path1, path3));
    assertEquals(-21, fnc.compare(path1, path2));
    assertEquals(21, fnc.compare(path2, path1));
  }
}