
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
public class P2 {

    @JsonProperty("trnRating")
    private TrnRating trnRating;
    @JsonProperty("score")
    private Score score;
    @JsonProperty("top1")
    private Top1 top1;
    @JsonProperty("top3")
    private Top3 top3;
    @JsonProperty("top5")
    private Top5 top5;
    @JsonProperty("top6")
    private Top6 top6;
    @JsonProperty("top10")
    private Top10 top10;
    @JsonProperty("top12")
    private Top12 top12;
    @JsonProperty("top25")
    private Top25 top25;
    @JsonProperty("kd")
    private Kd kd;
    @JsonProperty("winRatio")
    private WinRatio winRatio;
    @JsonProperty("matches")
    private Matches matches;
    @JsonProperty("kills")
    private Kills kills;
    @JsonProperty("kpg")
    private Kpg kpg;
    @JsonProperty("scorePerMatch")
    private ScorePerMatch scorePerMatch;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("trnRating")
    public TrnRating getTrnRating() {
        return trnRating;
    }

    @JsonProperty("trnRating")
    public void setTrnRating(TrnRating trnRating) {
        this.trnRating = trnRating;
    }

    @JsonProperty("score")
    public Score getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(Score score) {
        this.score = score;
    }

    @JsonProperty("top1")
    public Top1 getTop1() {
        return top1;
    }

    @JsonProperty("top1")
    public void setTop1(Top1 top1) {
        this.top1 = top1;
    }

    @JsonProperty("top3")
    public Top3 getTop3() {
        return top3;
    }

    @JsonProperty("top3")
    public void setTop3(Top3 top3) {
        this.top3 = top3;
    }

    @JsonProperty("top5")
    public Top5 getTop5() {
        return top5;
    }

    @JsonProperty("top5")
    public void setTop5(Top5 top5) {
        this.top5 = top5;
    }

    @JsonProperty("top6")
    public Top6 getTop6() {
        return top6;
    }

    @JsonProperty("top6")
    public void setTop6(Top6 top6) {
        this.top6 = top6;
    }

    @JsonProperty("top10")
    public Top10 getTop10() {
        return top10;
    }

    @JsonProperty("top10")
    public void setTop10(Top10 top10) {
        this.top10 = top10;
    }

    @JsonProperty("top12")
    public Top12 getTop12() {
        return top12;
    }

    @JsonProperty("top12")
    public void setTop12(Top12 top12) {
        this.top12 = top12;
    }

    @JsonProperty("top25")
    public Top25 getTop25() {
        return top25;
    }

    @JsonProperty("top25")
    public void setTop25(Top25 top25) {
        this.top25 = top25;
    }

    @JsonProperty("kd")
    public Kd getKd() {
        return kd;
    }

    @JsonProperty("kd")
    public void setKd(Kd kd) {
        this.kd = kd;
    }

    @JsonProperty("winRatio")
    public WinRatio getWinRatio() {
        return winRatio;
    }

    @JsonProperty("winRatio")
    public void setWinRatio(WinRatio winRatio) {
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
    public Kills getKills() {
        return kills;
    }

    @JsonProperty("kills")
    public void setKills(Kills kills) {
        this.kills = kills;
    }

    @JsonProperty("kpg")
    public Kpg getKpg() {
        return kpg;
    }

    @JsonProperty("kpg")
    public void setKpg(Kpg kpg) {
        this.kpg = kpg;
    }

    @JsonProperty("scorePerMatch")
    public ScorePerMatch getScorePerMatch() {
        return scorePerMatch;
    }

    @JsonProperty("scorePerMatch")
    public void setScorePerMatch(ScorePerMatch scorePerMatch) {
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
