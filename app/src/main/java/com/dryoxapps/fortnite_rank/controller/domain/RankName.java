package com.dryoxapps.fortnite_rank.controller.domain;

/**
 * This enumeration contains the definitions of rank names.
 *
 * @author Drioux.Guidry
 */
public enum RankName {
  CHAMPION("Champion"),

  SOUL("Soul"),

  DIAMOND1("Diamond 1"),

  DIAMOND2("Diamond 2"),

  DIAMOND3("Diamond 3"),

  DIAMOND4("Diamond 4"),

  DIAMOND5("Diamond 5"),

  PLATINUM1("Platinum 1"),

  PLATINUM2("Platinum 2"),

  PLATINUM3("Platinum 3"),

  PLATINUM4("Platinum 4"),

  PLATINUM5("Platinum 5"),

  GOLD1("Gold 1"),

  GOLD2("Gold 2"),

  GOLD3("Gold 3"),

  GOLD4("Gold 4"),

  GOLD5("Gold 5"),

  SILVER1("Silver 1"),

  SILVER2("Silver 2"),

  SILVER3("Silver 3"),

  SILVER4("Silver 4"),

  SILVER5("Silver 5"),

  BRONZE1("Bronze 1"),

  BRONZE2("Bronze 2"),

  BRONZE3("Bronze 3"),

  BRONZE4("Bronze 4"),

  BRONZE5("Bronze 5");

  private String name;

  /**
   * Private constructor stores the state.
   *
   * @param name The mame of the rank.
   */
  private RankName(String name) {
    this.name = name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return name;
  }

  /**
   * Convert from a double to a {@link RankName} object.
   * Will return the first enumeration that has a double value >= percentile.
   *
   * @param name The percentile as a double.
   * @return The found {@link RankName} object, or {@code null} if the name doesn't match.
   */
  public static RankName fromDouble(String name) {
    for (RankName n : RankName.values()) {
      if (n.name.equals(name)) {
        return n;
      }
    }

    return null;
  }
}
