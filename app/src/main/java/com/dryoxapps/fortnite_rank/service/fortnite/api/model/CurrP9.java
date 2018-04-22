
package com.dryoxapps.fortnite_rank.service.fortnite.api.model;

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
public class CurrP9 {

    @JsonProperty("trnRating")
    private TrnRating_____ trnRating;
    @JsonProperty("score")
    private Score_____ score;
    @JsonProperty("top1")
    private Top1_____ top1;
    @JsonProperty("top3")
    private Top3_____ top3;
    @JsonProperty("top5")
    private Top5_____ top5;
    @JsonProperty("top6")
    private Top6_____ top6;
    @JsonProperty("top10")
    private Top10_____ top10;
    @JsonProperty("top12")
    private Top12_____ top12;
    @JsonProperty("top25")
    private Top25_____ top25;
    @JsonProperty("kd")
    private Kd_____ kd;
    @JsonProperty("winRatio")
    private WinRatio_____ winRatio;
    @JsonProperty("matches")
    private Matches_____ matches;
    @JsonProperty("kills")
    private Kills_____ kills;
    @JsonProperty("kpg")
    private Kpg_____ kpg;
    @JsonProperty("scorePerMatch")
    private ScorePerMatch_____ scorePerMatch;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("trnRating")
    public TrnRating_____ getTrnRating() {
        return trnRating;
    }

    @JsonProperty("trnRating")
    public void setTrnRating(TrnRating_____ trnRating) {
        this.trnRating = trnRating;
    }

    @JsonProperty("score")
    public Score_____ getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(Score_____ score) {
        this.score = score;
    }

    @JsonProperty("top1")
    public Top1_____ getTop1() {
        return top1;
    }

    @JsonProperty("top1")
    public void setTop1(Top1_____ top1) {
        this.top1 = top1;
    }

    @JsonProperty("top3")
    public Top3_____ getTop3() {
        return top3;
    }

    @JsonProperty("top3")
    public void setTop3(Top3_____ top3) {
        this.top3 = top3;
    }

    @JsonProperty("top5")
    public Top5_____ getTop5() {
        return top5;
    }

    @JsonProperty("top5")
    public void setTop5(Top5_____ top5) {
        this.top5 = top5;
    }

    @JsonProperty("top6")
    public Top6_____ getTop6() {
        return top6;
    }

    @JsonProperty("top6")
    public void setTop6(Top6_____ top6) {
        this.top6 = top6;
    }

    @JsonProperty("top10")
    public Top10_____ getTop10() {
        return top10;
    }

    @JsonProperty("top10")
    public void setTop10(Top10_____ top10) {
        this.top10 = top10;
    }

    @JsonProperty("top12")
    public Top12_____ getTop12() {
        return top12;
    }

    @JsonProperty("top12")
    public void setTop12(Top12_____ top12) {
        this.top12 = top12;
    }

    @JsonProperty("top25")
    public Top25_____ getTop25() {
        return top25;
    }

    @JsonProperty("top25")
    public void setTop25(Top25_____ top25) {
        this.top25 = top25;
    }

    @JsonProperty("kd")
    public Kd_____ getKd() {
        return kd;
    }

    @JsonProperty("kd")
    public void setKd(Kd_____ kd) {
        this.kd = kd;
    }

    @JsonProperty("winRatio")
    public WinRatio_____ getWinRatio() {
        return winRatio;
    }

    @JsonProperty("winRatio")
    public void setWinRatio(WinRatio_____ winRatio) {
        this.winRatio = winRatio;
    }

    @JsonProperty("matches")
    public Matches_____ getMatches() {
        return matches;
    }

    @JsonProperty("matches")
    public void setMatches(Matches_____ matches) {
        this.matches = matches;
    }

    @JsonProperty("kills")
    public Kills_____ getKills() {
        return kills;
    }

    @JsonProperty("kills")
    public void setKills(Kills_____ kills) {
        this.kills = kills;
    }

    @JsonProperty("kpg")
    public Kpg_____ getKpg() {
        return kpg;
    }

    @JsonProperty("kpg")
    public void setKpg(Kpg_____ kpg) {
        this.kpg = kpg;
    }

    @JsonProperty("scorePerMatch")
    public ScorePerMatch_____ getScorePerMatch() {
        return scorePerMatch;
    }

    @JsonProperty("scorePerMatch")
    public void setScorePerMatch(ScorePerMatch_____ scorePerMatch) {
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

}
