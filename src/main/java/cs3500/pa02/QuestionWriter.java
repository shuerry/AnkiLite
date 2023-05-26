package cs3500.pa02;

import cs3500.pa02.model.Question;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Represents a Question writer that generates a file with all the Q&A blocks
 */
public class QuestionWriter {
  /**
   * String for storing the Q&A content
   */
  String questions = "";

  /**
   * An ArrayList of all the markdown files
   */
  ArrayList<Path> mdFiles;

  /**
   * Default constructor for QuestionWriter when updating the file
   */
  public QuestionWriter() {}

  /**
   * Constructor for QuestionWriter which generates a new question bank
   *
   * @param mdFiles list of markdown files in the directory
   */
  QuestionWriter(ArrayList<Path> mdFiles) {
    this.mdFiles = mdFiles;
  }

  /**
   * Get the Q&A blocks from all the mdFiles in String
   *
   * @return all the questions in String
   * @throws IOException if any markdown file path is invalid
   */
  private String generateQuestions() throws IOException {
    for (Path p : mdFiles) {
      FileReader fr = new FileReader(p);
      questions = questions + fr.allQuestions() + "\n";
    }
    return questions;
  }

  /**
   * generate a question bank with all the extracted Q&A blocks
   *
   * @param output the output path of where the question bank wants to be written
   * @return the question bank
   * @throws IOException when the output file path is invalid
   */
  public File writeQuestionBank(String output) throws IOException {
    String content = generateQuestions();
    File outputFile = new File(output);
    try (FileWriter writer = new FileWriter(outputFile)) {
      writer.write(content);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return outputFile;
  }

  /**
   * Update the bank with new questions
   *
   * @param questions list of questions that need to be written in the bank
   * @param output output path of the question bank file that needs to be overwritten
   */
  public void updateBank(ArrayList<Question> questions, Path output) {
    try (FileWriter writer = new FileWriter(output.toFile())) {
      for (Question qa : questions) {
        writer.write(qa.toString());
        writer.write(System.lineSeparator());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
