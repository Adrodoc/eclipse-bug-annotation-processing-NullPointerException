package de.sanacorp.adas.testenv;

import java.math.BigDecimal;

import net.karneim.pojobuilder.GeneratePojoBuilder;

public class CommonPojoFactory {
  @GeneratePojoBuilder(intoPackage = "de.sanacorp.abc.defg")
  public static BigDecimal createBigDecimal(long nextNumber) {
    return new BigDecimal(nextNumber);
  }
}
