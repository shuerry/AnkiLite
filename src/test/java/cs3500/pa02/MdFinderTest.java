package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * represents the class to test the MdFinder class
 */
class MdFinderTest {
  /**
   * input directory
   */
  private final String input = "src/test/resources/input";
  ArrayList<Path> myList;
  MdFinder mdf;
  Path path1;
  Path path2;

  /**
   * initializes myList, mdf, and the paths before each tests
   */
  @BeforeEach
  public void setup() {
    myList = new ArrayList<>();
    mdf = new MdFinder(myList);
    path1 = Path.of(input + "/arrays.md");
    path2 = Path.of(input + "/vectorNotes/vectors.md");
  }

  /**
   * Tests building a list of all valid Markdown paths in a directory
   */
  @Test
  public void testGetList() throws IOException {
    Files.walkFileTree(Path.of(input), mdf);

    // build list of expected file paths
    ArrayList<Path> expectedFiles = new ArrayList<>();
    expectedFiles.add(path2);
    expectedFiles.add(path1);

    // get list of traversed Markdown file paths
    ArrayList<Path> actualFiles = mdf.getList();

    // compare both lists
    assertEquals(3, actualFiles.size());
  }


  /**
   * tests if an exception is thrown when the file cannot be visited
   */
  @Test
  public void testException() {
    // normal2.txt is locked
    Path file = Path.of(input + "/vectorNotes/normal2.txt");
    IOException exception = new IOException("File visit failed");
    FileVisitResult result = mdf.visitFileFailed(file, exception);
    assertEquals(FileVisitResult.CONTINUE, result);
  }
}
