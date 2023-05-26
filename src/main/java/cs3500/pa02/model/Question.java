package cs3500.pa02.model;

/**
 * A Question class that represents a QA block
 */
public class Question {
  // A question
  String question;
  // An answer
  String answer;
  // Difficulty of the block
  Difficulty difficulty;

  /**
   * Constructor for the Question Class, initializes question, answer, and difficulty
   *
   * @param question the question of the QA block
   * @param answer the answer of the QA block
   * @param difficulty the difficulty of the QA block
   */
  public Question(String question, String answer, Difficulty difficulty) {
    this.question = question;
    this.answer = answer;
    this.difficulty = difficulty;
  }

  /**
   * Get the question of this block
   *
   * @return the question of the block
   */
  public String getQuestion() {
    return this.question;
  }

  /**
   * Get all the answer of this block
   *
   * @return the answer of the block
   */
  public String getAnswer() {
    return this.answer;
  }

  /**
   * Get the difficulty of the block
   *
   * @return the difficulty of the block
   */
  public Difficulty getDifficulty() {
    return this.difficulty;
  }

  /**
   * Set the difficulty of the block
   *
   * @param difficulty the difficulty that the question is changing into
   */
  public void setDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
  }

  /**
   * Set the format for toString
   *
   * @return the question block displayed in the correct form
   */
  public String toString() {
    return this.difficulty + "- " + "Q: " + this.question + " A: " + this.answer;
  }
}
