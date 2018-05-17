
package com.dryoxapps.fortnite_rank.service.fortnite.api.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "curr_p2",
    "curr_p10",
    "curr_p9",
    "prior_p2",
    "prior_p10",
    "prior_p9"
})
public class GameModes {

  @JsonProperty("curr_p2")
  private GameMode soloStatistics;
  @JsonProperty("curr_p10")
  private GameMode duoStatistics;
  @JsonProperty("curr_p9")
  private GameMode squadStatistics;
  @JsonProperty("prior_p2")
  private GameMode pastSoloStats;
  @JsonProperty("prior_p10")
  private GameMode pastDuoStats;
  @JsonProperty("prior_p9")
  private GameMode pastSquadStats;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("curr_p2")
  public GameMode getSoloStatistics() {
    return soloStatistics;
  }

  @JsonProperty("curr_p2")
  public void setSoloStatistics(GameMode soloStatistics) {
    this.soloStatistics = soloStatistics;
  }

  @JsonProperty("curr_p10")
  public GameMode getDuoStatistics() {
    return duoStatistics;
  }

  @JsonProperty("curr_p10")
  public void setDuoStatistics(GameMode duoStatistics) {
    this.duoStatistics = duoStatistics;
  }

  @JsonProperty("curr_p9")
  public GameMode getSquadStatistics() {
    return squadStatistics;
  }

  @JsonProperty("curr_p9")
  public void setSquadStatistics(GameMode squadStatistics) {
    this.squadStatistics = squadStatistics;
  }

  @JsonProperty("prior_p2")
  public GameMode getPastSoloStats() {
    return pastSoloStats;
  }

  @JsonProperty("prior_p2")
  public void setPastSoloStats(GameMode pastSoloStats) {
    this.pastSoloStats = pastSoloStats;
  }

  @JsonProperty("prior_p10")
  public GameMode getPastDuoStats() {
    return pastDuoStats;
  }

  @JsonProperty("prior_p10")
  public void setPastDuoStats(GameMode pastDuoStats) {
    this.pastDuoStats = pastDuoStats;
  }

  @JsonProperty("prior_p9")
  public GameMode getPastSquadStats() {
    return pastSquadStats;
  }

  @JsonProperty("prior_p9")
  public void setPastSquadStats(GameMode pastSquadStats) {
    this.pastSquadStats = pastSquadStats;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  /**
   * Retrieves the current non-null statistic POJOs for a player.
   *
   * @return An array of hashmaps.
   */
  public Map<String, GameModeStats>[] GetMaps() {
    Map[] maps = GetNonNullMaps(RetrieveNonNullStats());

    return maps;
  }

  /**
   * Utility method that retrieves the hashmap of GameModeStats for every non-null GameMode POJO.
   *
   * @param gameModes The GameMode POJO.
   * @return An array of hashmaps.
   */
  protected Map[] GetNonNullMaps(ArrayList<GameMode> gameModes) {
    ArrayList<Map> maps = new ArrayList<>();

    for (GameMode gameMode : gameModes) {
      maps.add(gameMode.GetAttributeMap());
    }

    return maps.toArray(new Map[maps.size()]);
  }

  /**
   * Utility method that creates an array of of non-null statistic POJOs.
   *
   * @return An array of non-null POJOs.
   */
  protected ArrayList<GameMode> RetrieveNonNullStats() {
    ArrayList<GameMode> gameMode = new ArrayList<>();

    if (soloStatistics != null) {
      gameMode.add(soloStatistics);
    }
    if (duoStatistics != null) {
      gameMode.add(duoStatistics);
    }
    if (squadStatistics != null) {
      gameMode.add(squadStatistics);
    }

    return gameMode;
  }
}
