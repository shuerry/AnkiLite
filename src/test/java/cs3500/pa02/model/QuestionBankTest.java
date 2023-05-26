package cs3500.pa02.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A test class for QuestionBank
 */
class QuestionBankTest {
  private final String input = "/Users/sherrychen/Downloads/CS3500/pa02-shuerry"
      + "/src/test/resources/SampleForTesting.txt";
  QuestionBank qb;

  /**
   * Initializes qb
   */
  @BeforeEach
  public void setUp() {
    try {
      qb = new QuestionBank(Path.of(input));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    assertNotNull(qb.scanner);
  }

  /**
   * Test if the questions are being extracted correctly
   *
   * @throws IOException if there's an issue when extracting the questions
   */
  @Test
  public void testGetQuestions() throws IOException {
    ArrayList<String> expected =
        (ArrayList<String>) Files.readAllLines(
            Path.of(input));
    assertEquals(expected, qb.getQuestions());
  }

  /**
   * Test if the hard questions are being extracted correctly
   *
   * @throws IOException if there's an issue when extracting the hard questions
   */
  @Test
  public void testGetHard() throws IOException {
    qb.getQuestions();
    ArrayList<String> expected =
        (ArrayList<String>) Files.readAllLines(
            Path.of(input));
    assertEquals(expected.size(), qb.getHard().size());
  }

  /**
   * Test if the easy questions are being extracted correctly
   *
   * @throws IOException if there are any issues when extracting the easy questions
   */
  @Test
  public void testGetEasy() {
    qb.getQuestions();
    assertEquals(0, qb.getEasy().size());
  }

  /**
   * test to see if an IOException is thrown when the path is invalid
   */
  @Test
  public void throwException() {
    Path nonExistentPath = Path.of("non_existent_file.txt");
    assertThrows(IOException.class, () -> new QuestionBank(nonExistentPath));
  }
}