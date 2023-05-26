package cs3500.pa02;

import cs3500.pa02.model.Difficulty;
import cs3500.pa02.model.Question;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a MdReader class for reading and summarizing texts in a markdown file
 */
public class FileReader {
  Scanner scanner = null;

  /**
   * an ArrayList to store the content in the file
   */
  ArrayList<String> content = new ArrayList<>();

  /**
   * an ArrayList to store the formatted content in the file
   */
  ArrayList<String> formattedContent = new ArrayList<>();

  /**
   * an ArrayList to store the Q&A blocks in the file
   */
  ArrayList<Question> questions = new ArrayList<>();

  /**
   * a StringBuilder store summarized and formatted content
   */
  StringBuilder sb = new StringBuilder();
  StringBuilder allQuestions = new StringBuilder();

  /**
   * constructor for the class MdReader
   *
   * @param p Path of the input directory
   * @throws IOException if scanner cannot be initialized properly
   */
  FileReader(Path p) throws IOException {
    try {
      scanner = new Scanner(p);
    } catch (IOException e) {
      throw e;
    }
  }

  /**
   * summarize the content in the markdown file with appropriate formatting and remove any Q&A
   *
   * @return the summarized and formatted content of the markdown file
   */
  public StringBuilder summarize() {
    this.format();
    ArrayList<String> removedQst = new ArrayList<>(formattedContent);
    for (String s : formattedContent) {
      if (s.contains(":::")) {
        removedQst.remove(s);
      }
    }
    formattedContent = removedQst;
    for (String s : formattedContent) {
      if (s.contains("#")) {
        s += "\n";
      }
      sb.append(s);
    }
    return sb;
  }

  /**
   * get all the questions in the markdown file with appropriate formatting
   *
   * @return the question and answer blocks of the markdown file in the form of String
   */
  public StringBuilder allQuestions() {
    this.getQuestions();
    if (questions.size() != 0) {
      for (Question qa : questions) {
        allQuestions.append(qa.toString());
      }
    } else {
      allQuestions.append("");
    }
    return allQuestions;
  }

  /**
   * extract the Q&A blocks in the markdown file with appropriate formatting
   *
   * @return the Q&A content from the markdown file in the form of an ArrayList of Questions
   */
  private ArrayList<Question> getQuestions() {
    this.format();
    for (String s : formattedContent) {
      if (s.contains(":::")) {
        String question = s.substring(2, s.indexOf(":::"));
        String answer = s.substring(s.indexOf(":::") + 3, s.length());
        questions.add(new Question(question, answer, Difficulty.HARD));
      }
    }
    return questions;
  }

  /**
   * retrieve the text in the markdown file by adding each line in content
   */
  private void getContent() {
    while (scanner.hasNext()) {
      String nextLine = scanner.nextLine();
      content.add(nextLine);
    }
  }

  /**
   * format the content retrieved from the markdown file
   * by adding each formatted lines into formattedContent
   */
  private void format() {
    this.getContent();
    for (String s : content) {
      if (s.contains("#")) {
        formattedContent.add(s);
      }
      if (s.contains("##")) {
        formattedContent.add("\n" + s);
        formattedContent.remove(s);
      }
      if (s.contains("[[")) {
        StringBuilder merge = new StringBuilder();
        Pattern pattern = Pattern.compile("\\[\\[(.*?)\\]\\]");
        if (s.endsWith("]]") || s.endsWith("]].")) {
          merge.append(s);
        }
        if (!s.endsWith("]]") && !s.endsWith("]].")) {
          merge.append(s);
          merge.append(content.get(content.indexOf(s) + 1).substring(1));
        }
        Matcher matcher = pattern.matcher(merge);
        while (matcher.find()) {
          String extractedPhrase = matcher.group(1);
          formattedContent.add("- " + extractedPhrase + "\n");
        }
      }
    }
  }
}
