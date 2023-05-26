package cs3500.pa02.view;


import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa02.model.Difficulty;
import cs3500.pa02.model.Question;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A test class for StudySessionView
 */
public class StudySessionViewTest {
  private final InputStream systemIn = System.in;
  private final PrintStream systemOut = System.out;
  private ByteArrayOutputStream testOut;
  private StudySessionView ssv;

  /**
   * Set the output
   */
  @BeforeEach
  public void setUpOutput() {
    testOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(testOut));
  }

  /**
   * Get the output
   */
  private String getOutput() {
    return testOut.toString();
  }

  /**
   * Restore the input and output to system default
   */
  @AfterEach
  public void restoreSystemInputOutput() {
    System.setIn(systemIn);
    System.setOut(systemOut);
  }

  /**
   * Initializes the ssv
   */
  @BeforeEach
  public void setUp() {
    ssv = new StudySessionView();
  }

  /**
   * Test if the source is retrieved correctly from the user's input
   */
  @Test
  public void testGetSource() {
    String prompt = "Enter the source to the question bank: ";
    Path expectedSource = Path.of("/Users/sherrychen/Downloads/CS3500/pa02-shuerry"
        + "/src/test/resources/output/StudyGuide.txt");
    String input = "/Users/sherrychen/Downloads/CS3500/pa02-shuerry"
        + "/src/test/resources/output/StudyGuide.txt";
    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream);

    Scanner scanner = new Scanner(System.in);
    Path actualSource = StudySessionView.getSource(prompt, scanner);

    assertEquals(expectedSource, actualSource);
  }

  /**
   * Test if the number of questions is retrieved correctly from the user's input
   */
  @Test
  public void testGetNumOfQuestion() {
    String prompt = "Enter the number of questions: ";
    int expectedNumOfQuestions = 5;
    String input = "5"; // Simulating user input
    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream);

    Scanner scanner = new Scanner(System.in);
    int actualNumOfQuestions = StudySessionView.getNumOfQuestion(prompt, scanner);

    assertEquals(expectedNumOfQuestions, actualNumOfQuestions);
  }

  /**
   * Test if the line is shown correctly to the user
   */
  @Test
  public void testShowLine() {
    String prompt = "This is a test prompt.";
    ssv.showLine(prompt);

    assertEquals(prompt + System.lineSeparator(), getOutput());
  }

  /**
   * Test if the choice is retrieved correctly from the user's input
   */
  @Test
  public void testGetChoice() {
    String prompt = "Enter your choice: ";
    int expectedChoice = 2;
    String input = "2";
    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream);

    Scanner scanner = new Scanner(System.in);
    int actualChoice = StudySessionView.getChoice(prompt, scanner);

    assertEquals(expectedChoice, actualChoice);
  }

  /**
   * Test if the answer to the question is being shown correctly to the user
   */
  @Test
  public void testShowQuestion() {
    Question question = new Question("What is the capital of France?",
        "Paris", Difficulty.EASY);
    ssv.showQuestion(question);

    assertEquals(question.getQuestion() + System.lineSeparator(), getOutput());
  }
}
