package cs3500.pa02;

import java.nio.file.Path;
import java.util.Comparator;

/**
 * Represents a Comparator that implements Comparator interface for file names
 */
public class FileNameComparator implements Comparator<Path> {
  /**
   * compare the file names in alphabetical order
   *
   * @param path1 path of the first file
   * @param path2 path of the second file
   * @return int 0, 1, or -1
   */
  @Override
  public int compare(Path path1, Path path2) {
    String name1 = path1.getFileName().toString();
    String name2 = path2.getFileName().toString();
    return name1.compareTo(name2);
  }
}
