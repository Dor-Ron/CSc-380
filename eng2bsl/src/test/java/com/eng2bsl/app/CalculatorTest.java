package com.eng2bsl.app;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class CalculatorTest {
  // Default
  @Test
  public void evaluatesExpression() {
    Calculator calculator = new Calculator();
    int sum = calculator.evaluate("1+2+3");
    assertEquals(6, sum);
  }

  // Should return false and crash program
  @Test
  public void evaluateFalse() {
    assertEquals('f', 'd');
  }

  // Should return true
  @Test
  public void evaluateTrue() {
    assertTrue(true);
  }
}
