package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents the class to test the TimeComparator class
 */
class TimeComparatorTest {
  TimeComparator tc;

  /**
   * set the times
   */
  FileTime knownCreationTime1 = FileTime.from(Instant.parse("2023-05-14T12:00:00Z"));
  FileTime knownModifiedTime1 = FileTime.from(Instant.parse("2023-01-13T12:00:00Z"));
  FileTime knownCreationTime2 = FileTime.from(Instant.parse("2022-05-13T12:00:00Z"));
  FileTime knownModifiedTime2 = FileTime.from(Instant.parse("2023-02-13T12:00:00Z"));

  MdFileForTesting mdf1;
  MdFileForTesting mdf2;

  /**
   * initializes tc, mdf1, and mdf2 before each tests
   */
  @BeforeEach
  public void setup() {
    tc = new TimeComparator();
    mdf1 = new MdFileForTesting(knownCreationTime1, knownModifiedTime1);
    mdf2 = new MdFileForTesting(knownCreationTime2, knownModifiedTime2);
  }

  /**
   * test if the createTime and modifiedTime for the mock class is being retrieved properly
   */
  @Test
  public void testGetTime() {
    assertEquals(knownCreationTime1, mdf1.createdAt);
    assertEquals(knownCreationTime2, mdf2.createdAt);
    assertEquals(knownModifiedTime1, mdf1.modifiedAt);
    assertEquals(knownModifiedTime2, mdf2.modifiedAt);
  }

  /**
   * test if the compare method is working properly with the FileTime
   */
  @Test
  public void compare() {
    assertEquals(0, tc.compare(mdf1.createdAt, mdf1.createdAt));
    assertEquals(0, tc.compare(mdf2.modifiedAt, mdf2.modifiedAt));
    assertEquals(1, tc.compare(mdf1.createdAt, mdf2.createdAt));
    assertEquals(-1, tc.compare(mdf1.modifiedAt, mdf2.modifiedAt));
  }
}