package cs3500.pa02.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A test class for the Question class
 */
class QuestionTest {
  //the question
  private Question q1;

  /**
   * Initialize the question
   */
  @BeforeEach
  void setUp() {
    q1 = new Question("What is the name of this course?",
        "CS3500- Object Oriented Design", Difficulty.EASY);
  }

  /**
   * Test to see if the question of the block is being shown correctly
   */
  @Test
  void testGetQuestion() {
    assertEquals("What is the name of this course?", q1.getQuestion());
  }

  /**
   * Test to see if the answer of the block is being shown correctly
   */
  @Test
  void testGetAnswer() {
    assertEquals("CS3500- Object Oriented Design", q1.getAnswer());
  }

  /**
   * Test to see if the difficulty of the block is being set correctly
   */
  @Test
  void testSetDifficulty() {
    q1.setDifficulty(Difficulty.HARD);
    assertEquals(Difficulty.HARD, q1.getDifficulty());
  }

  /**
   * test to see if the whole question block is being displayed correctly
   */
  @Test
  void testToString() {
    assertEquals("EASY- Q: What is the name of this course? "
        + "A: CS3500- Object Oriented Design", q1.toString());
  }
}