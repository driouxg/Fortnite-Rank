package com.dryoxapps.fortnite_rank.service.fortnite;

import com.dryoxapps.fortnite_rank.service.fortnite.api.model.GameModeStats;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.GameModes;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.RecentMatch;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class calculates the ranks for overall statistics tab.
 *
 * @author Drioux.Guidry
 */
public class RankCalculator {

  private static double NO_VALUE = 0;
  private static int NO_VALUE_INT = 0;

  /**
   * Utility method that calculates the average percentile rank for GameModeStats
   *
   * @param gameModes The gameModes statistics for a player
   * @return The average percentile for GameModeStats
   */
  public static double CalculateAvgPercentile(GameModes gameModes, String statKey) {
    double sum = 0;
    double numberOfGameModes = CalculateNumberOfGameModes(gameModes);

    Map[] maps = gameModes.GetMaps();

    for (Map map : maps) {
      sum += TransformPercentile((GameModeStats) map.get(statKey));
    }

    return sum / numberOfGameModes;
  }

  /**
   * Utility method that calculates the average value for non-null GameModeStats.
   *
   * @param gameModes The gamemode statistics for an individual game mode.
   * @return The average value for GameModeStats.
   */
  public static double CalculateAvgValue(GameModes gameModes, String statKey) {
    double sum = 0;
    double numberOfGameModes = CalculateNumberOfGameModes(gameModes);

    Map[] maps = gameModes.GetMaps();

    for (Map map : maps) {
      sum += TransformValue((GameModeStats) map.get(statKey));
    }

    return sum / numberOfGameModes;
  }

  /**
   * Calculates the average rank value for non-null GameModeStats
   *
   * @param gameModes The gameMode statistics for an individual game mode.
   * @param statKey The key for a specific GameModeStats POJO
   * @return average rank value
   */
  public static int CalculateAvgRank(GameModes gameModes, String statKey) {
    int sum = 0;
    int numberOfGameModes = CalculateNumberOfGameModes(gameModes);

    Map[] maps = gameModes.GetMaps();

    for (Map map : maps) {
      sum += TransformRank((GameModeStats) map.get(statKey));
    }

    return sum / numberOfGameModes;
  }

  /**
   * Creates a GameModeStats POJO and fills it with data to be displayed in the player ranks.
   *
   * @param gameModes The GameModeStats POJO
   * @param statKey A key for a GameModeStats POJO in a hashmap
   * @return A GameModeStats POJO containing data to be displayed in the player ranks page.
   */
  public static GameModeStats CalculateAvgValues(GameModes gameModes, String statKey) {
    GameModeStats gameModeStats = new GameModeStats();

    gameModeStats.setPercentile(CalculateAvgPercentile(gameModes, statKey));
    gameModeStats.setValue(ToString(CalculateAvgValue(gameModes, statKey)));

    return gameModeStats;
  }

  /**
   * Returns an int value representing the total number of matches played, for the match history
   * table row.
   *
   * @param recentMatches A list of RecentMatch POJOs
   * @return Total number of matches played
   */
  public static int CalculateTotalNumberOfMatches(List<RecentMatch> recentMatches) {
    if (NonNull(recentMatches)) {
      ArrayList<RecentMatch> uniqueRecentMatches = GetRecentMatches(recentMatches);
      int sum = 0;

      for (RecentMatch recentMatch : uniqueRecentMatches) {
        sum += TransformNumberOfMatches(recentMatch);
      }

      return sum;
    } else {
      return NO_VALUE_INT;
    }
  }

  /**
   * Returns a double value representing the overall Player Rating change.
   *
   * @param recentMatches A list of RecentMatch POJOs.
   * @return Overall player rating change.
   */
  public static double CalculateTotalRatingDelta(List<RecentMatch> recentMatches) {
    if (NonNull(recentMatches)) {
      ArrayList<RecentMatch> uniqueRecentMatches = GetRecentMatches(recentMatches);
      double sum = 0;

      for (RecentMatch recentMatch : uniqueRecentMatches) {
        sum += TransformRatingChange(recentMatch);
      }

      return sum;
    } else {
      return NO_VALUE;
    }
  }

  /**
   * Gets the most recent match for each ranked playlist i.e (solo, duo, squad).
   *
   * @param recentMatches A List of RecentMatch POJOs
   * @return An arraylist of RecentMatch POJOs
   */
  protected static ArrayList<RecentMatch> GetRecentMatches(List<RecentMatch> recentMatches) {
    ArrayList<RecentMatch> uniqueRecentMatches = new ArrayList<>();

    uniqueRecentMatches.add(GetRecentMatch(recentMatches, "p2"));
    uniqueRecentMatches.add(GetRecentMatch(recentMatches, "p9"));
    uniqueRecentMatches.add(GetRecentMatch(recentMatches, "p10"));

    return uniqueRecentMatches;
  }

  /**
   * Gets the first RecentMatch POJO that has a playlist attribute with the same name as the
   * parameter 'key'.
   *
   * @param recentMatches A list of RecentMatch POJOs
   * @param key The playlist key.
   * @return The first RecentMatch object that has a playlist name matching the parameter 'key'.
   */
  public static RecentMatch GetRecentMatch(List<RecentMatch> recentMatches, String key) {
    boolean found = false;
    int i = 0;

    while (!found && i < recentMatches.size()) {
      if (recentMatches.get(i).getPlaylist().equals(key)) {
        found = true;
      } else {
        i++;
      }
    }

    if (found) {
      return recentMatches.get(i);
    } else {
      return null;
    }
  }

  /**
   * Retrieves the double value for a percentile in gameModeStats
   *
   * @param gameModeStats The GameModeStats POJO
   * @return A double percentile value.
   */
  protected static double TransformPercentile(GameModeStats gameModeStats) {
    if (NonNull(gameModeStats)) {
      if (NonNull(gameModeStats.getPercentile())) {
        return gameModeStats.getPercentile();
      }
    }
    return NO_VALUE;
  }

  /**
   * Retrieves the double value for a gameModeStats
   *
   * @param gameModeStats The gameModeStats POJO.
   * @return Double value.
   */
  protected static double TransformValue(GameModeStats gameModeStats) {
    if (NonNull(gameModeStats)) {
      return ToDouble(gameModeStats.getValue());
    } else {
      return NO_VALUE;
    }
  }

  /**
   * Retrieves the rank value for gameModeStats
   *
   * @param gameModeStats The gameModeStats POJO
   * @return Double value
   */
  protected static double TransformRank(GameModeStats gameModeStats) {
    if (NonNull(gameModeStats)) {
      return ToDouble(gameModeStats.getRank());
    } else {
      return NO_VALUE;
    }
  }

  /**
   * Retrieves the double value for a RecentMatch
   *
   * @param recentMatch A RecentMatch POJO.
   * @return A double value for a recent match.
   */
  protected static double TransformRatingChange(RecentMatch recentMatch) {
    if (NonNull(recentMatch)) {
      return recentMatch.getTrnRatingChange();
    } else {
      return NO_VALUE;
    }
  }

  protected static int TransformNumberOfMatches(RecentMatch recentMatch) {
    if (NonNull(recentMatch)) {
      return recentMatch.getMatches();
    } else {
      return NO_VALUE_INT;
    }
  }

  /**
   * Checks if the Double value is non null
   *
   * @param value A Double value
   * @return True if the the Double is nonnull
   */
  protected static boolean NonNull(Double value) {
    if (value != null) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks if the gameModeStats object is not null
   *
   * @param gameModeStats A GameModeStats POJO.
   * @return True if the object is not null.
   */
  protected static boolean NonNull(GameModeStats gameModeStats) {
    if (gameModeStats != null) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks if the RecentMatch POJO is not null.
   *
   * @param recentMatch A RecentMatch POJO.
   * @return True if the object is not null.
   */
  protected static boolean NonNull(RecentMatch recentMatch) {
    if (recentMatch != null) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks if the list of RecentMatch POJOs are not null.
   *
   * @param recentMatches List of RecentMatch POJOs
   * @return True if the object is not null
   */
  protected static boolean NonNull(List<RecentMatch> recentMatches) {
    if (recentMatches != null) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Calculates the number of game mode objects that are not null.
   *
   * @param gameModes A GameModes POJO.
   * @return The number of game mode objects.
   */
  public static int CalculateNumberOfGameModes(GameModes gameModes) {
    int numberOfGameModes = 0;

    if (gameModes.getSoloStatistics() != null) {
      numberOfGameModes++;
    }
    if (gameModes.getDuoStatistics() != null) {
      numberOfGameModes++;
    }
    if (gameModes.getSquadStatistics() != null) {
      numberOfGameModes++;
    }

    return numberOfGameModes;
  }

  /**
   * Converts numeric string to double.
   *
   * @return The parsed string
   */
  static double ToDouble(String string) {
    return Double.parseDouble(string);
  }

  /**
   * Converts int to double
   *
   * @param value Integer value
   * @return double value
   */
  static double ToDouble(int value) {
    return (double) value;
  }

  /**
   * Stringifies a double value to two decimal places, and removes any trailing zero's, if any.
   *
   * @param value The double value.
   * @return A stringified double value.
   */
  protected static String ToString(double value) {
    DecimalFormat format = new DecimalFormat("0.#");
    return format.format(value);
  }
}
