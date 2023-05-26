package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * represents the class to test the MdReader class
 */
class FileReaderTest {
  /**
   * input directory
   */
  private final String input = "src/test/resources/input/arrays.md";
  FileReader mr;


  /**
   * initializes mr and check that the Scanner is properly initialized before each test
   */
  @BeforeEach
  public void setup() throws IOException {
    mr = new FileReader(Path.of(input));
    assertNotNull(mr.scanner);
  }

  /**
   * test to see if an IOException is thrown when the path is invalid
   */
  @Test
  public void throwException() {
    Path nonExistentPath = Path.of("non_existent_file.txt");
    assertThrows(IOException.class, () -> new FileReader(nonExistentPath));
  }

  /**
   * test to see if the questions are being extracted correctly
   * the SampleForTesting.txt has the same Q&A blocks in Q&A.md
   *
   */
  @Test
  void testAllQuestions() throws IOException {
    FileReader fr = new FileReader(Path.of("src/test/resources/input/Q&A.md"));
    ArrayList<String> expected =
        (ArrayList<String>) Files.readAllLines(
            Path.of("/Users/sherrychen/Downloads/CS3500/pa02-shuerry"
                + "/src/test/resources/SampleForTesting.txt"));
    StringBuilder expectedContent = new StringBuilder();
    for (String s : expected) {
      expectedContent.append(s + "\n");
    }
    assertEquals(expectedContent.toString(), fr.allQuestions().toString());
  }

  /**
   * test to see if the content is being retrieved correctly (testing getContent()) and
   * if formatted content is working correctly (testing format()) and
   * test if summarize() is working correctly
   */
  @Test
  void testSummarize() throws IOException {
    // calls the method
    mr.summarize();
    // add all lines from the arrays.md to the expectedList
    ArrayList<String> expected =
        (ArrayList<String>) Files.readAllLines(Path.of(input));

    // check to see if the expected content is the same as content retrieved from the MdReader
    assertEquals(expected, mr.content);

    // check to see if the formatted content is the same as content formatted from the MdReader
    ArrayList<String> formatted = new ArrayList<>();
    for (String s : expected) {
      if (s.contains("#")) {
        formatted.add(s);
      }
      if (s.contains("##")) {
        formatted.add("\n" + s);
        formatted.remove(s);
      }
      if (s.contains("[[")) {
        StringBuilder merge = new StringBuilder();
        Pattern pattern = Pattern.compile("\\[\\[(.*?)\\]\\]");
        if (s.endsWith("]]") || s.endsWith("]].")) {
          merge.append(s);
        }
        if (!s.endsWith("]]") && !s.endsWith("]].")) {
          merge.append(s);
          merge.append(expected.get(expected.indexOf(s) + 1).substring(1));
        }
        Matcher matcher = pattern.matcher(merge);
        while (matcher.find()) {
          String extractedPhrase = matcher.group(1);
          formatted.add("- " + extractedPhrase + "\n");
        }
      }
    }
    assertEquals(formatted, mr.formattedContent);
  }
}