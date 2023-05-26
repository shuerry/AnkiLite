package cs3500.pa02.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A test class for StudySessionController class
 */

class StudySessionControllerTest {
  // A StudySessionController
  private StudySessionController ssc;
  private InputStream standardIn;

  /**
   * Initializes ssc
   */
  @BeforeEach
  void setUp() {
    ssc = new StudySessionController();
  }

  /**
   * Test to see if getStart works properly with every instance variable initialized
   * and study session flow successfully
   */
  @Test
  void testGetStart() throws IOException {
    String input = "src/test/resources/output/TestingUpdate.txt"
        + "\n1\n1";
    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
    standardIn = System.in;
    System.setIn(inputStream);

    Scanner scanner = new Scanner(System.in);
    ssc.getStart(scanner);

    System.setIn(standardIn);
    assertNotNull(ssc.source);
    assertNotNull(ssc.numOfQuestions);
    assertNotNull(ssc.qb);
    assertEquals(1, ssc.allQuestions.size());
    assertTrue(Files.exists(ssc.source));
  }
}