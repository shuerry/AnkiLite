package cs3500.pa02;

import java.nio.file.attribute.FileTime;
import java.util.Comparator;

/**
 * Represents a Comparator that implements Comparator interface for FileTime
 */
public class TimeComparator implements Comparator<FileTime> {
  /**
   * compare the file names in alphabetical order
   *
   * @param time1 time created or modified for the first file
   * @param time2 time created or modified for the second file
   * @return int 0, 1, or -1
   */
  public int compare(FileTime time1, FileTime time2) {
    return time1.compareTo(time2);
  }
}

