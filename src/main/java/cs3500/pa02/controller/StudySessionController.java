package cs3500.pa02.controller;

import cs3500.pa02.model.Question;
import cs3500.pa02.model.QuestionBank;
import cs3500.pa02.model.StudySessionModel;
import cs3500.pa02.view.StudySessionView;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a StudySessionController that starts the study session and manage user's choices
 */
public class StudySessionController {
  // A question bank
  QuestionBank qb;
  // An ArrayList to store all the hard questions
  ArrayList<Question> hard;
  // An ArrayList to store all the easy questions
  ArrayList<Question> easy;
  // Number of questions want to be reviewed in the study session
  int numOfQuestions;
  // A path to the question bank
  Path source;
  // A StudySessionModel
  StudySessionModel ssm = new StudySessionModel();
  // A StudySessionView
  StudySessionView ssv = new StudySessionView();
  // An ArrayList to store all the questions
  ArrayList<Question> allQuestions;

  /**
   * start the study session
   *
   * @param scanner to read the user's input
   * @throws IOException if there's issue when getting source, generating questions, ending session
   *        updating sessions, or initializing question bank
   */
  public void getStart(Scanner scanner) throws IOException {
    source = ssv.getSource("Please enter a source for the question bank: ",
        scanner);
    numOfQuestions = ssv.getNumOfQuestion("Please enter how many questions you want to study: ",
        scanner);
    // access the question bank
    qb = new QuestionBank(source);
    qb.getQuestions();
    // get all the hard and easy questions
    hard = qb.getHard();
    easy = qb.getEasy();
    // generate a random list of questions
    allQuestions = ssm.generateRandomQuestions(source, hard, easy, numOfQuestions);
    // display questions to the user and ask for user input
    for (Question qa : allQuestions) {
      ssv.showQuestion(qa);
      ssm.userChoices(qa, scanner);
    }
    // end the session and update the bank after all questions are being reviewed
    ssm.endSession();
    ssm.updateQuestions();
  }
}
