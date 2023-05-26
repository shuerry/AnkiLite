package cs3500.pa02.model;

import cs3500.pa02.QuestionWriter;
import cs3500.pa02.view.StudySessionView;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * A StudySessionModel class that handles all the functionality
 */
public class StudySessionModel {
  // An ArrayList of Questions that stores all the Questions changed to hard
  ArrayList<Question> changeToHard = new ArrayList<>();
  // An ArrayList of Questions that stores all the Questions changed to easy
  ArrayList<Question> changeToEasy = new ArrayList<>();
  // An ArrayList of Questions with all the hard questions after the session
  ArrayList<Question> updatedHard;
  // An ArrayList of Questions with all the easy questions after the session
  ArrayList<Question> updatedEasy;
  // An ArrayList of Questions that store all the generated questions
  ArrayList<Question> allQuestionsGenerated = new ArrayList<>();
  // Keeping track of the number of questions reviewed
  int reviewed = 0;
  // The source to the question bank
  Path source;
  // A StudySessionView
  StudySessionView ssv = new StudySessionView();

  /**
   * Generates the questions in random order, hard questions always being generated first
   *
   * @param source a source to the question bank
   * @param hard an ArrayList of hard questions
   * @param easy an ArrayList of easy questions
   * @param numOfQuestions number of questions that needs to be generated
   * @return an ArrayList of all the generated questions
   */
  public ArrayList<Question> generateRandomQuestions(Path source, ArrayList<Question> hard,
                                                     ArrayList<Question> easy,
                                                     int numOfQuestions) {
    this.source = source;
    updatedHard = new ArrayList<>(hard);
    updatedEasy = new ArrayList<>(easy);
    Collections.shuffle(hard);
    Collections.shuffle(easy);
    if (numOfQuestions <= hard.size()) {
      for (int i = 0; i < numOfQuestions; i++) {
        allQuestionsGenerated.add(hard.get(i));
      }
    } else if (numOfQuestions > hard.size() + easy.size()) {
      for (int i = 0; i < hard.size(); i++) {
        allQuestionsGenerated.add(hard.get(i));
      }
      for (int i = 0; i < easy.size(); i++) {
        allQuestionsGenerated.add(easy.get(i));
      }
    } else {
      for (int i = 0; i < hard.size(); i++) {
        allQuestionsGenerated.add(hard.get(i));
      }
      for (int i = 0; i < numOfQuestions - hard.size(); i++) {
        allQuestionsGenerated.add(easy.get(i));
      }
    }
    return allQuestionsGenerated;
  }

  /**
   * Get the user's choice and either mark the currenty question hard, easy, or show answer or exit
   *
   * @param currentQuestion the currentQuestion in the session
   * @param scanner the scanner which reads the user's input
   * @throws IOException when the user enters an invalid choice
   */
  public void userChoices(Question currentQuestion, Scanner scanner) throws IOException {
    int choice = ssv.getChoice("1. Mark Easy 2. Mark Hard 3. Show Answer 4. Exit", scanner);
    if (choice == 3) {
      ssv.showLine(currentQuestion.getAnswer());
      userChoices(currentQuestion, scanner);
    } else if (choice == 1) {
      if (currentQuestion.difficulty == Difficulty.EASY) {
        ssv.showLine("This question is already easy");
      } else {
        currentQuestion.setDifficulty(Difficulty.EASY);
        updatedHard.remove(currentQuestion);
        changeToEasy.add(currentQuestion);
      }
      reviewed++;
    } else if (choice == 2) {
      if (currentQuestion.difficulty == Difficulty.HARD) {
        ssv.showLine("This question is already hard");
      } else {
        currentQuestion.setDifficulty(Difficulty.HARD);
        updatedEasy.remove(currentQuestion);
        changeToHard.add(currentQuestion);
      }
      reviewed++;
    } else if (choice == 4) {
      endSession();
      updateQuestions();
      System.exit(0);
    } else {
      throw new IOException("Please enter a valid choice");
    }
  }

  /**
   * End the study session and display the stats
   */
  public void endSession() {
    ssv.showLine("Congratulation! This is the end of the session!");
    ssv.showLine("You've reviewed " + reviewed + " questions");
    ssv.showLine(changeToHard.size() + " has changed from easy to hard");
    ssv.showLine(changeToEasy.size() + " has changed from hard to easy");
    ssv.showLine("There are currently " + (updatedHard.size() + changeToHard.size())
        + " hard questions");
    ssv.showLine("There are currently " + (updatedEasy.size() + changeToEasy.size())
        + " easy questions");
  }

  /**
   * Update the question bank
   */
  public void updateQuestions() {
    ArrayList<Question> newHard = new ArrayList<>();
    newHard.addAll(updatedHard);
    newHard.addAll(changeToHard);
    ArrayList<Question> newEasy = new ArrayList<>();
    newEasy.addAll(updatedEasy);
    newEasy.addAll(changeToEasy);
    ArrayList<Question> newAll = new ArrayList<>();
    newAll.addAll(newEasy);
    newAll.addAll(newHard);
    // update the bank with the updated difficulties
    QuestionWriter qw = new QuestionWriter();
    qw.updateBank(newAll, source);
  }
}
