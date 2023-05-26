package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa02.model.Difficulty;
import cs3500.pa02.model.Question;
import cs3500.pa02.model.QuestionBank;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * represents the class to test the QuestionWriter class
 */
class QuestionWriterTest {
  /**
   * input
   */
  private final String input = "src/test/resources/input";

  /**
   * output
   */
  private final String output = "src/test/resources/output/testingQA.txt";
  ArrayList<Path> expectedList = new ArrayList<>();
  ArrayList<Path> actualList = new ArrayList<>();
  QuestionWriter qw;
  QuestionWriter qwe;
  Path path1;
  Path path2;
  Path path3;

  /**
   * Initializes expectedList, actualList, sgw, and the paths
   */
  @BeforeEach
  public void setup() throws IOException {
    path1 = Path.of(input + "/arrays.md");
    path2 = Path.of(input + "/vectorNotes/vectors.md");
    path3 = Path.of(input + "/Q&A.md");
    MdFinder mdf = new MdFinder(actualList);
    Files.walkFileTree(Path.of(input), mdf);
    actualList = mdf.getList();
    expectedList.add(path1);
    expectedList.add(path2);
    expectedList.add(path3);
    qw = new QuestionWriter(actualList);
    qwe = new QuestionWriter();
  }

  /**
   * test to see if the Q&A content are being generated properly
   */
  @Test
  public void testGenerateContent() throws IOException {
    qw.writeQuestionBank(output);
    ArrayList<String> expected =
        (ArrayList<String>) Files.readAllLines(
            Path.of("/Users/sherrychen/Downloads/CS3500/pa02-shuerry"
                +
                "/src/test/resources/SampleForTesting.txt"));
    StringBuilder expectedContent = new StringBuilder();
    for (String s : expected) {
      expectedContent.append(s + "\n");
    }
    expectedContent.append("\n" + "\n" + "\n");
    assertEquals(expectedContent.toString(), qw.questions);
    Files.deleteIfExists(Path.of(output));
  }

  /**
   * test to see if the writeQuestion actually creates a new file
   * and throw an IOException if the output path is invalid
   */
  @Test
  public void testWriteQuestionBank() throws IOException {
    Files.deleteIfExists(Path.of(output));
    qw.writeQuestionBank(output);
    assertTrue(Files.exists(Path.of(output)), "File does not exist");
    Files.deleteIfExists(Path.of(output));
  }

  /**
   * test to see if the updateQuestionBank actually updates the questions
   * and throw an IOException if the output path is not found
   */
  @Test
  public void testUpdateBank() throws IOException {
    ArrayList<Question> updatedQuestions = new ArrayList<>();
    updatedQuestions.add(new Question("q1", "a1", Difficulty.EASY));
    updatedQuestions.add(new Question("q2", "a2", Difficulty.HARD));
    qwe.updateBank(updatedQuestions, Path.of(output));
    QuestionBank qb = new QuestionBank(Path.of(output));
    qb.getQuestions();
    assertEquals(1, qb.getEasy().size());
    assertEquals(1, qb.getHard().size());
    Files.deleteIfExists(Path.of(output));
  }
}