package cs3500.pa02.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A test class for StudySessionModel
 */
public class StudySessionModelTest {
  // a StudySessionModel
  private StudySessionModel ssm;
  // A path to the question bank
  private Path source;
  // An ArrayList storing all the hard questions
  private ArrayList<Question> hard = new ArrayList<>();
  // An ArrayList storing all the easy questions
  private ArrayList<Question> easy = new ArrayList<>();
  // hard questions
  private Question hard1;
  private Question hard2;
  private Question easy1;
  // stream for testing input and output
  private InputStream standardIn;
  private PrintStream standardOut;
  private ByteArrayOutputStream outputStream;

  /**
   * Initializes ssm, source, hard and easy questions, and the hard and easy lists
   */
  @BeforeEach
  public void setUp() {
    ssm = new StudySessionModel();
    source = Path.of("/Users/sherrychen/Downloads/CS3500/pa02-shuerry"
        + "/src/test/resources/output/TestingUpdate.txt");
    hard1 = new Question("A hard question",
        "An answer", Difficulty.HARD);
    hard2 = new Question("Another hard question",
        "An answer", Difficulty.HARD);
    easy1 = new Question("An easy question",
        "An answer", Difficulty.EASY);
    hard.add(hard1);
    hard.add(hard2);
    easy.add(easy1);
  }

  /**
   * Test to see if correct number of questions are being generated when the
   * input number of questions is equal to the amount of hard questions
   */
  @Test
  public void testGenerateRandomQuestionsWithNumberEqualsHard() {
    assertEquals(2,
        ssm.generateRandomQuestions(source, hard, easy, 2).size());
  }

  /**
   * Test to see if correct number of questions are being generated when the
   * input number of questions is equal to the amount of all questions
   */
  @Test
  public void testGenerateRandomQuestionsWithNumberEqualsAll() {
    assertEquals(3,
        ssm.generateRandomQuestions(source, hard, easy, 3).size());
  }

  /**
   * Test to see if correct number of questions are being generated when the
   * input number of questions is less than the amount of hard questions
   */
  @Test
  public void testGenerateRandomQuestionsWithNumberLessThanAll() {
    assertEquals(3,
        ssm.generateRandomQuestions(source, hard, easy, 5).size());
  }

  /**
   * Test to see if the correct output is shown when the user tries to mark
   * an already easy question easy
   *
   * @throws IOException when there's issue generating the question
   */
  @Test
  public void testUserChoices1AlreadyEasy() throws IOException {
    ssm.generateRandomQuestions(source, hard, easy, 3);
    String input = "1";
    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
    standardIn = System.in;
    standardOut = System.out;
    System.setIn(inputStream);

    outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    Scanner scanner = new Scanner(System.in);
    ssm.userChoices(easy1, scanner);

    System.setIn(standardIn);
    System.setOut(standardOut);

    String output = outputStream.toString();
    assertEquals("1. Mark Easy 2. Mark Hard 3. Show Answer 4. Exit\n"
        + "This question is already easy\n", output);
  }

  /**
   * test to see if the question is being marked easy when the user chooses 1
   *
   * @throws IOException when there's issue generating the question
   */
  @Test
  public void testUserChoices1() throws IOException {
    ssm.generateRandomQuestions(source, hard, easy, 3);
    String input = "1";
    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
    standardIn = System.in;
    standardOut = System.out;
    System.setIn(inputStream);

    outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    Scanner scanner = new Scanner(System.in);
    ssm.userChoices(hard1, scanner);

    System.setIn(standardIn);
    System.setOut(standardOut);

    assertEquals(Difficulty.EASY, hard1.getDifficulty());
  }

  /**
   * Test to see if the correct output is shown when the user tries to mark
   * an already hard question hard
   *
   * @throws IOException when there's issue generating the question
   */
  @Test
  public void testUserChoices2AlreadyHard() throws IOException {
    ssm.generateRandomQuestions(source, hard, easy, 3);
    String input = "2";
    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
    standardIn = System.in;
    standardOut = System.out;
    System.setIn(inputStream);

    outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    Scanner scanner = new Scanner(System.in);
    ssm.userChoices(hard1, scanner);

    System.setIn(standardIn);
    System.setOut(standardOut);

    String output = outputStream.toString();
    assertEquals("1. Mark Easy 2. Mark Hard 3. Show Answer 4. Exit\n"
        + "This question is already hard\n", output);
  }

  /**
   * Test to see if the question is being marked hard when the user chooses 2
   *
   * @throws IOException when there's issue generating the question
   */
  @Test
  public void testUserChoices2() throws IOException {
    ssm.generateRandomQuestions(source, hard, easy, 3);
    String input = "2";
    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
    standardIn = System.in;
    standardOut = System.out;
    System.setIn(inputStream);

    outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    Scanner scanner = new Scanner(System.in);
    ssm.userChoices(easy1, scanner);

    System.setIn(standardIn);
    System.setOut(standardOut);

    assertEquals(Difficulty.HARD, easy1.getDifficulty());
  }

  /**
   * Test to see if the answer of the question is being shown correctly when the user chooses 3
   *
   * @throws IOException when there's issue generating the question
   */
  @Test
  public void testUserChoices3() throws IOException {
    ssm.generateRandomQuestions(source, hard, easy, 3);
    String input = "3\n1";
    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
    standardIn = System.in;
    standardOut = System.out;
    System.setIn(inputStream);
    outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    Scanner scanner = new Scanner(System.in);
    ssm.userChoices(easy1, scanner);

    System.setIn(standardIn);
    System.setOut(standardOut);

    String output = outputStream.toString();
    assertEquals("1. Mark Easy 2. Mark Hard 3. Show Answer 4. Exit\n"
        + "An answer\n" + "1. Mark Easy 2. Mark Hard 3. Show Answer 4. Exit\n"
        + "This question is already easy\n", output);
  }

  /**
   * Test to see if an exception is thrown when the user enters an invalid choice
   */
  @Test
  public void testInvalidChoice() {
    ssm.generateRandomQuestions(source, hard, easy, 3);
    String input = "5";
    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
    standardIn = System.in;
    standardOut = System.out;
    System.setIn(inputStream);

    outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    Scanner scanner = new Scanner(System.in);
    assertThrows(IOException.class, () -> ssm.userChoices(hard1, scanner),
        "Please enter a valid choice");
  }

  /**
   * Test to see if the correct stats is being displayed when the user ends the session
   *
   * @throws IOException when there's issue generating the question
   */
  @Test
  public void testEndSession() throws IOException {
    ssm.generateRandomQuestions(source, hard, easy, 1);
    String input = "1";
    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
    standardIn = System.in;
    standardOut = System.out;
    System.setIn(inputStream);
    outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    Scanner scanner = new Scanner(System.in);
    ssm.userChoices(easy1, scanner);
    ssm.endSession();
    System.setIn(standardIn);
    System.setOut(standardOut);

    String output = outputStream.toString();
    assertEquals("1. Mark Easy 2. Mark Hard 3. Show Answer 4. Exit\n"
        + "This question is already easy\n"
        + "Congratulation! This is the end of the session!\n"
        + "You've reviewed 1 questions\n"
        + "0 has changed from easy to hard\n"
        + "0 has changed from hard to easy\n"
        + "There are currently 2 hard questions\n"
        + "There are currently 1 easy questions\n", output);
  }

  /**
   * Test to see if the question bank is being updated correctly
   *
   * @throws IOException when there's issue generating the question
   */
  @Test
  public void testUpdateQuestions() throws IOException {
    Files.deleteIfExists(source);
    ssm.generateRandomQuestions(source, hard, easy, 1);
    String input = "2";
    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
    standardIn = System.in;
    standardOut = System.out;
    System.setIn(inputStream);
    outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    Scanner scanner = new Scanner(System.in);
    ssm.userChoices(easy1, scanner);
    ssm.updateQuestions();
    System.setIn(standardIn);
    System.setOut(standardOut);
    assertTrue(Files.exists(source));
  }
}