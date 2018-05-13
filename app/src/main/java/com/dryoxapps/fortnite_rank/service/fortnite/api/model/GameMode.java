
package com.dryoxapps.fortnite_rank.service.fortnite.api.model;

import com.dryoxapps.fortnite_rank.service.fortnite.api.domain.GameModeStatsNames;
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
    "trnRating",
    "score",
    "top1",
    "top3",
    "top5",
    "top6",
    "top10",
    "top12",
    "top25",
    "kd",
    "winRatio",
    "matches",
    "kills",
    "kpg",
    "scorePerMatch"
})
public class GameMode {

  @JsonProperty("trnRating")
  private GameModeStats trnRating;
  @JsonProperty("score")
  private GameModeStats score;
  @JsonProperty("top1")
  private GameModeStats wins;
  @JsonProperty("top3")
  private GameModeStats top3;
  @JsonProperty("top5")
  private GameModeStats top5;
  @JsonProperty("top6")
  private GameModeStats top6;
  @JsonProperty("top10")
  private GameModeStats top10;
  @JsonProperty("top12")
  private GameModeStats top12;
  @JsonProperty("top25")
  private GameModeStats top25;
  @JsonProperty("kd")
  private GameModeStats kd;
  @JsonProperty("winRatio")
  private GameModeStats winRatio;
  @JsonProperty("matches")
  private Matches matches;
  @JsonProperty("kills")
  private GameModeStats kills;
  @JsonProperty("kpg")
  private GameModeStats kpg;
  @JsonProperty("scorePerMatch")
  private GameModeStats scorePerMatch;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("trnRating")
  public GameModeStats getTrnRating() {
    return trnRating;
  }

  @JsonProperty("trnRating")
  public void setTrnRating(GameModeStats trnRating) {
    this.trnRating = trnRating;
  }

  @JsonProperty("score")
  public GameModeStats getScore() {
    return score;
  }

  @JsonProperty("score")
  public void setScore(GameModeStats score) {
    this.score = score;
  }

  @JsonProperty("wins")
  public GameModeStats getWins() {
    return wins;
  }

  @JsonProperty("wins")
  public void setWins(GameModeStats wins) {
    this.wins = wins;
  }

  @JsonProperty("top3")
  public GameModeStats getTop3() {
    return top3;
  }

  @JsonProperty("top3")
  public void setTop3(GameModeStats top3) {
    this.top3 = top3;
  }

  @JsonProperty("top5")
  public GameModeStats getTop5() {
    return top5;
  }

  @JsonProperty("top5")
  public void setTop5(GameModeStats top5) {
    this.top5 = top5;
  }

  @JsonProperty("top6")
  public GameModeStats getTop6() {
    return top6;
  }

  @JsonProperty("top6")
  public void setTop6(GameModeStats top6) {
    this.top6 = top6;
  }

  @JsonProperty("top10")
  public GameModeStats getTop10() {
    return top10;
  }

  @JsonProperty("top10")
  public void setTop10(GameModeStats top10) {
    this.top10 = top10;
  }

  @JsonProperty("top12")
  public GameModeStats getTop12() {
    return top12;
  }

  @JsonProperty("top12")
  public void setTop12(GameModeStats top12) {
    this.top12 = top12;
  }

  @JsonProperty("top25")
  public GameModeStats getTop25() {
    return top25;
  }

  @JsonProperty("top25")
  public void setTop25(GameModeStats top25) {
    this.top25 = top25;
  }

  @JsonProperty("kd")
  public GameModeStats getKd() {
    return kd;
  }

  @JsonProperty("kd")
  public void setKd(GameModeStats kd) {
    this.kd = kd;
  }

  @JsonProperty("winRatio")
  public GameModeStats getWinRatio() {
    return winRatio;
  }

  @JsonProperty("winRatio")
  public void setWinRatio(GameModeStats winRatio) {
    this.winRatio = winRatio;
  }

  @JsonProperty("matches")
  public Matches getMatches() {
    return matches;
  }

  @JsonProperty("matches")
  public void setMatches(Matches matches) {
    this.matches = matches;
  }

  @JsonProperty("kills")
  public GameModeStats getKills() {
    return kills;
  }

  @JsonProperty("kills")
  public void setKills(GameModeStats kills) {
    this.kills = kills;
  }

  @JsonProperty("kpg")
  public GameModeStats getKpg() {
    return kpg;
  }

  @JsonProperty("kpg")
  public void setKpg(GameModeStats kpg) {
    this.kpg = kpg;
  }

  @JsonProperty("scorePerMatch")
  public GameModeStats getScorePerMatch() {
    return scorePerMatch;
  }

  @JsonProperty("scorePerMatch")
  public void setScorePerMatch(GameModeStats scorePerMatch) {
    this.scorePerMatch = scorePerMatch;
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
   * Generates a hashmap of the gameModeStatistic objects
   *
   * @return Hashmap of gamemode statistic objects.
   */
  public Map<String, GameModeStats> GetAttributeMap() {
    Map<String, GameModeStats> map = new HashMap<>();

    map.put("trnRating", trnRating);
    map.put("score", score);
    map.put("wins", wins);
    map.put("top3", top3);
    map.put("top5", top5);
    map.put("top6", top6);
    map.put("top10", top10);
    map.put("top12", top12);
    map.put("top25", top25);
    map.put("kd", kd);
    map.put("winRatio", winRatio);
    map.put("kills", kills);
    map.put("kpg", kpg);

    return map;
  }
}
