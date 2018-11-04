package com.estafeta.test.ui.helper;

import java.util.Random;

/**
 * @author dnikiforov
 * @project estafeta
 */
public class Rundom {

  static Random randomGenerator = new Random();
  private static final int randomInt1 = randomGenerator.nextInt(99999 - 11111) + 11111;
  public static final String VIN = "1HGCR2570DA0" + String.valueOf(randomInt1);
  private static final int randomInt2 = randomGenerator.nextInt(99999 - 11111) + 11111;
  private static final int randomInt3 = randomGenerator.nextInt(99999 - 11111) + 11111;
}
