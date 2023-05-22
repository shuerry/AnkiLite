package cs3500.pa02;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionBank {
  ArrayList<String> allQuestions = new ArrayList<String>();
  ArrayList<Question> hardQuestions = new ArrayList<>();
  ArrayList<Question> easyQuestions = new ArrayList<>();
  Scanner scanner = null;
  Path source;

  QuestionBank(Path source) throws IOException {
    try {
      scanner = new Scanner(source);
    } catch (IOException e) {
      throw e;
    }
  }

  public ArrayList<String> getQuestions() {
    while (scanner.hasNext()) {
      String nextLine = scanner.nextLine();
      allQuestions.add(nextLine);
    }
    return allQuestions;
  }
  public ArrayList<Question> getHard() {
    for (String s: allQuestions) {
      if (s.startsWith("HARD")) {
        String question = s.substring(s.indexOf("Q:") + 2, s.indexOf("A:"));
        String answer = s.substring(s.indexOf("A:") + 2, s.length());
        hardQuestions.add(new Question(question, answer, Difficulty.HARD));
      }
    }
    return hardQuestions;
  }
  public ArrayList<Question> getEasy() {
    for (String s: allQuestions) {
      if (s.startsWith("EASY")) {
        String question = s.substring(s.indexOf("Q: "), s.indexOf("A: "));
        String answer = s.substring(s.indexOf("A: "), s.length());
        easyQuestions.add(new Question(question, answer, Difficulty.EASY));
      }
    }
    return easyQuestions;
  }
}
