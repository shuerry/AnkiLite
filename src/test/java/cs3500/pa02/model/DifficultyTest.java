package cs3500.pa02.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * represents the class to test the Difficulty enum
 */
class DifficultyTest {

  /**
   * check if the values in the Difficulty enum are being retrieved properly
   */
  @Test
  void values() {
    Difficulty[] expectedOrder = {Difficulty.EASY, Difficulty.HARD};
    Difficulty[] actualOrder = Difficulty.values();
    assertArrayEquals(expectedOrder, actualOrder);
  }

  /**
   * check if valueOf() is working properly for the Difficulty enum
   */
  @Test
  void valueOf() {
    assertEquals(Difficulty.EASY, Difficulty.valueOf("EASY"));
    assertEquals(Difficulty.HARD, Difficulty.valueOf("HARD"));
  }
}