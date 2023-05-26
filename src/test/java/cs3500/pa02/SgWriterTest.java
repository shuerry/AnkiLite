package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * represents the class to test the SgWriter class
 */
class SgWriterTest {
  /**
   * input
   */
  private final String input = "src/test/resources/input";

  /**
   * output
   */
  private final String output = "src/test/resources/output/testingFile.md";
  ArrayList<Path> expectedList = new ArrayList<>();
  ArrayList<Path> actualList = new ArrayList<>();
  SgWriter sgw;
  Path path1;
  Path path2;

  Path path3;

  /**
   * initializes expectedList, actualList, sgw, and the paths before each tests
   */
  @BeforeEach
  public void setup() throws IOException {
    path1 = Path.of(input + "/arrays.md");
    path2 = Path.of(input + "/vectorNotes/vectors.md");
    path3 = Path.of(input + "/Q&A.md");
    MdFinder mdf = new MdFinder(actualList);
    Files.walkFileTree(Path.of(input), mdf);
    actualList = mdf.getList();
    expectedList.add(path3);
    expectedList.add(path1);
    expectedList.add(path2);
    sgw = new SgWriter(actualList);
  }

  /**
   * test if the filed are sorted in the proper order based on the OrderingFlag
   */
  @Test
  public void testSortFiles() throws IOException {
    // test if the sorting is working
    sgw.writeSg(output, "filename");
    assertEquals(expectedList, sgw.mdFiles);
  }

  /**
   * test if the OrderingFlag are being assigned correctly given the corresponding String
   */
  @Test
  public void testAssignFlag() throws IOException {
    sgw.writeSg(output, "filename");
    assertEquals(OrderingFlag.FILENAME, sgw.ordFlag);
    sgw.writeSg(output, "created");
    assertEquals(OrderingFlag.CREATED, sgw.ordFlag);
    sgw.writeSg(output, "modified");
    assertEquals(OrderingFlag.MODIFIED, sgw.ordFlag);
  }

  /**
   * test if IOException is thrown when the OrderingFlag is invalid
   */
  @Test
  public void testAssignFlagInvalidInput() {
    assertThrows(IOException.class, () -> sgw.writeSg(output, "invalid"));
  }

  /**
   * Test to see if the content are being generated properly
   */
  @Test
  public void testGenerateContent() throws IOException {
    sgw.writeSg(output, "filename");
    String expectedText = "\n# Java Arrays\n"
        +
        "- An **array** is a collection of variables of the same type\n"
        +
        "\n"
        +
        "## Declaring an Array\n"
        +
        "- General Form: type[] arrayName;\n"
        +
        "- only creates a reference\n"
        +
        "- no array has actually been created yet\n"
        +
        "\n"
        +
        "## Creating an Array (Instantiation)\n"
        +
        "- General form:  arrayName = new type[numberOfElements];\n"
        +
        "- numberOfElements must be a positive Integer.\n"
        +
        "- Gotcha: Array size is not modifiable once instantiated.\n"
        +
        "\n"
        +
        "# Vector\n"
        +
        "- Vector act like resizable arrays\n"
        +
        "\n"
        +
        "## Declaring a vector\n"
        +
        "- General Form: Vector<type> v = new Vector();\n"
        +
        "- type needs to be a valid reference type\n"
        +
        "\n"
        +
        "## Adding an element to a vector\n"
        +
        "- v.add(object of type);\n"
        +
        "\n";
    assertEquals(expectedText, sgw.summarizedText);
  }

  /**
   * test to see if the writeSG actually creates a new file
   * and throw an IOException if the ordering flag is invalid
   */
  @Test
  public void testWriteSg() throws IOException {
    Files.deleteIfExists(Path.of(output));
    sgw.writeSg(output, "filename");
    assertTrue(Files.exists(Path.of(output)), "File does not exist");
    sgw = new SgWriter(null);
    assertThrows(IOException.class, () -> sgw.writeSg(output, "invalid"));
  }
}