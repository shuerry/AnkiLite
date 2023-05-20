package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * represents the class to test the OrderingFlag enum
 */
class OrderingFlagTest {

  /**
   * check if the values in the OrderingFlag enum are being retrieved properly
   */
  @Test
  void values() {
    OrderingFlag[] expectedOrder = {OrderingFlag.FILENAME,
                                    OrderingFlag.CREATED,
                                    OrderingFlag.MODIFIED};
    OrderingFlag[] actualOrder = OrderingFlag.values();
    assertArrayEquals(expectedOrder, actualOrder);
  }

  /**
   * check if valueOf() is working properly for the OrderingFlag enum
   */
  @Test
  void valueOf() {
    assertEquals(OrderingFlag.FILENAME, OrderingFlag.valueOf("FILENAME"));
    assertEquals(OrderingFlag.CREATED, OrderingFlag.valueOf("CREATED"));
    assertEquals(OrderingFlag.MODIFIED, OrderingFlag.valueOf("MODIFIED"));
  }
}