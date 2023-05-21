package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuestionTest {
  Question q1;

  @BeforeEach
  void setUp() {
    q1 = new Question("What is the name of this course?",
        "CS3500- Object Oriented Design", Difficulty.EASY);
  }

  @Test
  void testGetQuestion() {
    assertEquals("What is the name of this course?", q1.getQuestion());
  }

  @Test
  void testGetAnswer() {
    assertEquals("CS3500- Object Oriented Design", q1.getAnswer());
  }

  @Test
  void testSetDifficulty() {
    q1.setDifficulty(Difficulty.HARD);
    assertEquals(Difficulty.HARD, q1.difficulty);
  }

  @Test
  void testToString() {
    assertEquals("EASY- Q: What is the name of this course? " +
        "A: CS3500- Object Oriented Design", q1.toString());
  }
}