package cs3500.pa02;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class QuestionWriter {
  String questions = "";
  ArrayList<Path> mdFiles;

  QuestionWriter(ArrayList<Path> mdFiles) {
    this.mdFiles = mdFiles;
  }

  public String generateQuestions() throws IOException {
    for (Path p : mdFiles) {
      FileReader fr = new FileReader(p);
      questions = questions + fr.allQuestions() + "\n";
    }
    return questions;
  }
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
  public void updateBank(ArrayList<Question> questions, String output) throws IOException {
    try (FileWriter writer = new FileWriter(output)) {
      for (Question qa : questions) {
        writer.write(qa.toString());
        writer.write(System.lineSeparator());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
