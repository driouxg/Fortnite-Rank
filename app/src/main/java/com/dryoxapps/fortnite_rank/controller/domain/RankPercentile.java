package com.dryoxapps.fortnite_rank.controller.domain;

/**
 * Contains definitions of the percentile values which are the boundaries for each rank. I based my
 * ranking distribution on https://www.leagueofgraphs.com/rankings/rank-distribution.
 *
 * @author Drioux.Guidry
 */
public enum RankPercentile {

  CHAMPION(0.1),

  SOUL(0.13),

  DIAMOND1(0.17),

  DIAMOND2(0.21),

  DIAMOND3(0.24),

  DIAMOND4(0.31),

  DIAMOND5(0.41),

  PLATINUM1(.88),

  PLATINUM2(1.49),

  PLATINUM3(2.26),

  PLATINUM4(3.17),

  PLATINUM5(3.95),

  GOLD1(5.79),

  GOLD2(8.2),

  GOLD3(10.2),

  GOLD4(13.61),

  GOLD5(17.51),

  SILVER1(24.77),

  SILVER2(31.7),

  SILVER3(42.27),

  SILVER4(53.2),

  SILVER5(63.53),

  BRONZE1(74.38),

  BRONZE2(80.67),

  BRONZE3(86.67),

  BRONZE4(91.73),

  BRONZE5(95.48);

  private double percentile;

  /**
   * Private constructor stores the state.
   *
   * @param percentile The percentile rank.
   */
  private RankPercentile(double percentile) {
    this.percentile = percentile;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return Double.toString(percentile);
  }

  /**
   * Convert from a double to a {@link RankPercentile} object.
   *
   * @param percentile The percentile as a double.
   * @return The found {@link RankPercentile} object, or {@code null} if the name doesn't match.
   */
  public static RankPercentile fromDouble(double percentile) {
    for (RankPercentile rp : RankPercentile.values()) {
      if (rp.percentile >= percentile) {
        return rp;
      }
    }

    return null;
  }
}
