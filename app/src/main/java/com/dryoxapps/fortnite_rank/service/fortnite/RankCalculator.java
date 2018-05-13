package com.dryoxapps.fortnite_rank.service.fortnite;

import com.dryoxapps.fortnite_rank.service.fortnite.api.model.GameModeStats;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.GameModes;
import com.dryoxapps.fortnite_rank.service.fortnite.api.model.GameMode;
import java.util.HashMap;
import java.util.Map;

/**
 * This class calculates the ranks for overall statistics tab.
 *
 * @author Drioux.Guidry
 */
public class RankCalculator {

  private static double NO_VALUE = 0;
  private static int SOLO_STATS_INDEX = 0;
  private static int DUO_STATS_INDEX = 1;
  private static int SQUAD_STATS_INDEX = 2;

  /**
   * Utility method that calculates the average percentile rank for GameModeStats
   *
   * @param gameModes The gameModes statistics for a player
   * @return The average percentile for GameModeStats
   */
  public static double CalculateAvgPercentile(GameModes gameModes, String statKey) {
    double sum = 0;
    double numberOfGameModes = CalculateNumberOfGameModesDouble(gameModes);

    Map[] maps = gameModes.GetMaps();

    sum += TransformPercentile((GameModeStats) maps[SOLO_STATS_INDEX].get(statKey));
    sum += TransformPercentile((GameModeStats) maps[DUO_STATS_INDEX].get(statKey));
    sum += TransformPercentile((GameModeStats) maps[SQUAD_STATS_INDEX].get(statKey));

    return sum / numberOfGameModes;
  }

  /**
   * Utility method that calculates the average value for GameModeStats.
   *
   * @param gameModes The gamemode statistics for an individual game mode.
   * @return The average value for GameModeStats.
   */
  public static double CalculateAvgValue(GameModes gameModes, String statKey) {
    double sum = 0;
    double numberOfGameModes = CalculateNumberOfGameModesDouble(gameModes);

    Map[] maps = gameModes.GetMaps();

    sum += TransformValue((GameModeStats) maps[SOLO_STATS_INDEX].get(statKey));
    sum += TransformValue((GameModeStats) maps[DUO_STATS_INDEX].get(statKey));
    sum += TransformValue((GameModeStats) maps[SQUAD_STATS_INDEX].get(statKey));

    return sum / numberOfGameModes;
  }

  /**
   * Retrieves the double value for a percentile in gameModeStats
   *
   * @param gameModeStats The GameModeStats POJO
   * @return A double percentile value.
   */
  protected static double TransformPercentile(GameModeStats gameModeStats) {
    if (NonNull(gameModeStats)) {
      return gameModeStats.getPercentile();
    } else {
      return NO_VALUE;
    }
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
   * Checks if the gameMode object is null.
   *
   * @param gameMode A GameMode POJO
   * @return True if the object is not null
   */
  protected static boolean NonNull(GameMode gameMode) {
    if (gameMode != null) {
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
   * Calculates the number of game mode objects that are not null.
   *
   * @param gameModes A GameModes POJO.
   * @return The number of game mode objects.
   */
  static double CalculateNumberOfGameModesDouble(GameModes gameModes) {
    double numberOfGameModes = 0;

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
}
