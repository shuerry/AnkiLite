package cs3500.pa02;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;


/**
 * Represents a MdFinder class which implements FileVisitor
 */
public class MdFinder implements FileVisitor<Path> {

  /**
   * an ArrayList to store the markdown files found
   */
  private ArrayList<Path> mdFiles;

  /**
   * constructor for the class MdFinder
   *
   * @param myList an empty ArrayList
   */
  MdFinder(ArrayList<Path> myList) {
    mdFiles = myList;
  }

  /**
   * getter method to retrieve the ArrayList mdFiles
   *
   * @return mdFiles
   */
  public ArrayList<Path> getList() {
    return mdFiles;
  }

  /**
   * called everytime the file system walker encounters a file
   *
   * @param file
   *          a reference to the file
   * @param attr
   *          the file's basic attributes
   *
   * @return directive on how to process current item's siblings and children.
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {

    String fileName = file.getFileName().toString();
    if (fileName.endsWith(".md")) {
      mdFiles.add(file);
    }
    return CONTINUE;
  }

  /**
   * What to do after processing all items in a directory
   *
   * @param dir
   *          a reference to the directory
   * @param exec
   *          {@code null} if the iteration of the directory completes without
   *          an error; otherwise the I/O exception that caused the iteration
   *          of the directory to complete prematurely
   *
   * @return in all cases, continue processing the sibling and child items of current item
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exec) {
    System.out.format("Finishing Directory: %s%n", dir);
    return CONTINUE;
  }

  /**
   * What to do at the beginning of processing a directory
   *
   * @param dir
   *          a reference to the directory
   * @param attrs
   *          the directory's basic attributes
   *
   * @return a directive to continue processing siblings and children of current item.
   * @throws IOException if issue encountered when starting to visit the directory
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir,
                                           BasicFileAttributes attrs) throws IOException {
    System.out.format("Starting Directory: %s%n", dir);
    return CONTINUE;
  }

  /**
   * called when a file cannot be visited for some undetermined reason (perhaps
   * locked by another process or a access permissions issue)
   *
   * @param file
   *          a reference to the file
   * @param exc
   *          the I/O exception that prevented the file from being visited
   *
   * @return continue processing
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    visitFailed(exc);
    return FileVisitResult.CONTINUE;
  }

  /**
   * handler method for visitFileFailed that help print out error message
   *
   * @param exc exception message
   */
  public void visitFailed(IOException exc) {
    System.err.println(exc);
  }
}

