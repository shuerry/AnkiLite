package cs3500.pa02.view;

import cs3500.pa02.model.Question;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * A StudySessionView class that is responsible for displaying messages for the user and take inputs
 */
public class StudySessionView {

  /**
   * Get the source to the question bank
   *
   * @param prompt the prompt being displayed to the user
   * @param scanner for reading the user's input
   */
  public static Path getSource(String prompt, Scanner scanner) {
    System.out.println(prompt);
    Path source = Path.of(scanner.nextLine());
    return source;
  }

  /**
   * Get the number of questions the user wants to review for the session
   *
   * @param prompt the prompt being displayed to the user
   * @param scanner for reading the user's input
   */
  public static int getNumOfQuestion(String prompt, Scanner scanner) {
    System.out.println(prompt);
    int numOfQuestion = scanner.nextInt();
    return numOfQuestion;
  }

  /**
   * Diplay a message to the user
   *
   * @param prompt the prompt being displayed to the user
   */
  public void showLine(String prompt) {
    System.out.println(prompt);
  }

  /**
   * Get the choice from the user
   *
   * @param prompt the prompt being displayed to the user
   * @param scanner for reading the user's input
   */
  public static int getChoice(String prompt, Scanner scanner) {
    System.out.println(prompt);
    int choice = scanner.nextInt();
    return choice;
  }

  /**
   * Show the question of the Q&A block
   *
   * @param question the question that the user wants the answer to be shown
   */
  public void showQuestion(Question question) {
    System.out.println(question.getQuestion());
  }
}
