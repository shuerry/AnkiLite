package cs3500.pa02;

public class Question {
  String question;
  String answer;
  Difficulty difficulty;

  Question(String question, String answer, Difficulty difficulty) {
    this.question = question;
    this.answer = answer;
    this.difficulty = difficulty;
  }

  public String getQuestion() {
    return this.question;
  }

  public String getAnswer() {
    return this.answer;
  }

  public void setDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
  }

  public String toString() {
    return this.difficulty + "- " + "Q: " + this.question + " A: " + this.answer;
  }
}
