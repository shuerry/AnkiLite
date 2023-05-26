package cs3500.pa02;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represent a study guide writer that generates a study guide
 */
public class SgWriter {
  /**
   * String for storing the summarized content
   */
  String summarizedText = "";

  /**
   * Represents the ordering flag
   */
  OrderingFlag ordFlag;

  /**
   * an ArrayList of markdown file paths
   */
  ArrayList<Path> mdFiles;

  /**
   * constructor for the class SgWriter
   *
   * @param mdFiles an ArrayList of all the markdown files found
   */
  SgWriter(ArrayList<Path> mdFiles) {
    this.mdFiles = mdFiles;
  }

  /**
   * assign the corresponding OrderingFlag to ordFlag based on the given String
   *
   * @param of representing the ordering flag entered in command line argument
   * @throws IOException if the given string doesn't match any of the OrderingFlag
   */
  private void assignFlag(String of) throws IOException {
    if (of.equals("filename")) {
      ordFlag = OrderingFlag.FILENAME;
    } else if (of.equals("created")) {
      ordFlag = OrderingFlag.CREATED;
    } else if (of.equals("modified")) {
      ordFlag = OrderingFlag.MODIFIED;
    } else {
      throw new IOException("Not a valid ordering flag");
    }
  }

  /**
   * sort the markdown files in the MDFiles list based on the OrderingFlag
   *
   * @return the sorted ArrayList of markdown files
   * @throws IOException if attributes of a file cannot be accessed
   */
  private ArrayList<Path> sortFiles() throws IOException {
    if (ordFlag == OrderingFlag.FILENAME) {
      Collections.sort(mdFiles, new FileNameComparator());
    }
    if (ordFlag == OrderingFlag.CREATED) {
      ArrayList<FileTime> createdTimes = new ArrayList<>();
      for (Path path : mdFiles) {
        FileTime creationTime = Files.readAttributes(path,
            BasicFileAttributes.class).creationTime();
        createdTimes.add(creationTime);
      }
      Collections.sort(createdTimes, new TimeComparator());
      ArrayList<Path> sortedPaths = new ArrayList<>();
      for (FileTime creationTime : createdTimes) {
        for (Path path : mdFiles) {
          FileTime pathCreationTime = Files.readAttributes(path,
              BasicFileAttributes.class).creationTime();
          if (creationTime.equals(pathCreationTime)) {
            sortedPaths.add(path);
          }
        }
      }
      mdFiles = sortedPaths;
    }
    if (ordFlag == OrderingFlag.MODIFIED) {
      ArrayList<FileTime> modifiedTimes = new ArrayList<>();
      for (Path path : mdFiles) {
        FileTime modifiedTime = Files.readAttributes(path,
            BasicFileAttributes.class).lastModifiedTime();
        modifiedTimes.add(modifiedTime);
      }
      Collections.sort(modifiedTimes, new TimeComparator());
      ArrayList<Path> sortedPaths = new ArrayList<>();
      for (FileTime modifiedTime : modifiedTimes) {
        for (Path path : mdFiles) {
          FileTime pathModifiedTime = Files.readAttributes(path,
              BasicFileAttributes.class).lastModifiedTime();
          if (modifiedTime.equals(pathModifiedTime)) {
            sortedPaths.add(path);
          }
        }
      }
      mdFiles = sortedPaths;
    }
    return mdFiles;
  }

  /**
   * generate the content in sorted order and stored them in summarizedText
   *
   * @return summarized text
   * @throws IOException if problem encountered when sorting the files or initializing the MdReader
   */
  private String generateContent() throws IOException {
    sortFiles();
    for (Path p : mdFiles) {
      FileReader fr = new FileReader(p);
      summarizedText = summarizedText + fr.summarize() + "\n";
    }
    return summarizedText;
  }

  /**
   * write the summarizedText to the output path
   *
   * @param output the given output path that the study guide needs to be located
   * @param of the OrderingFlag
   * @return the written file
   * @throws IOException if problem encountered when assigning the flag or generating content
   */
  public File writeSg(String output, String of) throws IOException {
    assignFlag(of);
    String content = generateContent();
    File outputFile = new File(output);
    try (FileWriter writer = new FileWriter(outputFile)) {
      writer.write(content);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return outputFile;
  }
}
