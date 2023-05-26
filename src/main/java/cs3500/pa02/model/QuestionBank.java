package cs3500.pa02.model;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a QuestionBank that has all the Q&A blocks for a review session
 */
public class QuestionBank {
  // An ArrayList to store all the questions
  ArrayList<String> allQuestions = new ArrayList<>();
  // An ArrayList to store all the hard questions
  ArrayList<Question> hardQuestions = new ArrayList<>();
  // An ArrayList to store all the easy questions
  ArrayList<Question> easyQuestions = new ArrayList<>();
  // A scanner to read the Q&A blocks in the question bank
  Scanner scanner;

  /**
   * Constructor for QuestionBank class that initializes the scanner
   *
   * @param source path to the question bank file
   */
  public QuestionBank(Path source) throws IOException {
    try {
      scanner = new Scanner(source);
    } catch (IOException e) {
      throw e;
    }
  }

  /**
   * Get all the questions in the question bank
   *
   * @return allQuestions in the question bank
   */
  public ArrayList<String> getQuestions() {
    while (scanner.hasNext()) {
      String nextLine = scanner.nextLine();
      allQuestions.add(nextLine);
    }
    return allQuestions;
  }

  /**
   * Get all the hard questions from all the questions retrieved
   *
   * @return hardQuestions an ArrayList of all the hard questions
   */
  public ArrayList<Question> getHard() {
    for (String s : allQuestions) {
      if (s.startsWith("HARD")) {
        String question = s.substring(s.indexOf("Q:") + 3, s.indexOf("A:"));
        String answer = s.substring(s.indexOf("A:") + 3, s.length());
        hardQuestions.add(new Question(question, answer, Difficulty.HARD));
      }
    }
    return hardQuestions;
  }

  /**
   * Get all the easy questions from all the questions retrieved
   *
   * @return easyQuestions an ArrayList of all the easy questions
   */
  public ArrayList<Question> getEasy() {
    for (String s : allQuestions) {
      if (s.startsWith("EASY")) {
        String question = s.substring(s.indexOf("Q: ") + 3, s.indexOf("A: "));
        String answer = s.substring(s.indexOf("A: ") + 3, s.length());
        easyQuestions.add(new Question(question, answer, Difficulty.EASY));
      }
    }
    return easyQuestions;
  }
}
