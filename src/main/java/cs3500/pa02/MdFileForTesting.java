package cs3500.pa02;

import java.nio.file.attribute.FileTime;

/**
 * Represents a mock markdown file for testing TimeComparator purpose
 */
public class MdFileForTesting {
  FileTime createdAt;
  FileTime modifiedAt;

  /**
   * constructor for the class MdFileForTesting
   *
   * @param createdAt file created time
   * @param modifiedAt file last modified time
   */
  MdFileForTesting(FileTime createdAt, FileTime modifiedAt) {
    this.createdAt = createdAt;
    this.modifiedAt = modifiedAt;
  }
}
