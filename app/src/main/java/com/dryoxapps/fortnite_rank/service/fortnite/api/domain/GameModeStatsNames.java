package com.dryoxapps.fortnite_rank.service.fortnite.api.domain;

/**
 * This enum contains definitions for statistic names.
 *
 * @author Drioux.Guidry
 */
public enum GameModeStatsNames {
  RATING("trnRating"),

  SCORE("score"),

  WINS("top1"),

  TOP3("top3"),

  TOP5("top5"),

  TOP6("top6"),

  TOP10("top10"),

  TOP12("top12"),

  TOP25("top25"),

  KD("kd"),

  WIN_RATIO("winRatio"),

  MATCHES("matches"),

  KILLS("kills"),

  KPG("kpg"),

  SCORE_PER_MATCH("scorePerMatch");

  private String name;

  /**
   * Private constructor stores the state.
   *
   * @param name The percentile rank.
   */
  private GameModeStatsNames(String name) {
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
   * Convert from a String to a {@link GameModeStatsNames} object.
   *
   * @param name The name of the game mode statistic.
   * @return The found {@link GameModeStatsNames} object, or {@code null} if the name doesn't match.
   */
  public static GameModeStatsNames fromString(String name) {
    for (GameModeStatsNames sn : GameModeStatsNames.values()) {
      if (sn.name.equals(name)) {
        return sn;
      }
    }

    return null;
  }
}
