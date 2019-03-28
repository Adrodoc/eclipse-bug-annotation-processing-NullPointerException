package de.sanacorp.adas.testenv;

import de.sanacorp.abc.defg.BigDecimalBuilder;

public class TestDslBase {
  public static BigDecimalBuilder $BigDecimal(long start) {
    return new BigDecimalBuilder();
  }
}
