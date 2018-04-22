
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
public class CurrP2 {

    @JsonProperty("trnRating")
    private TrnRating___ trnRating;
    @JsonProperty("score")
    private Score___ score;
    @JsonProperty("top1")
    private Top1___ top1;
    @JsonProperty("top3")
    private Top3___ top3;
    @JsonProperty("top5")
    private Top5___ top5;
    @JsonProperty("top6")
    private Top6___ top6;
    @JsonProperty("top10")
    private Top10___ top10;
    @JsonProperty("top12")
    private Top12___ top12;
    @JsonProperty("top25")
    private Top25___ top25;
    @JsonProperty("kd")
    private Kd___ kd;
    @JsonProperty("winRatio")
    private WinRatio___ winRatio;
    @JsonProperty("matches")
    private Matches___ matches;
    @JsonProperty("kills")
    private Kills___ kills;
    @JsonProperty("kpg")
    private Kpg___ kpg;
    @JsonProperty("scorePerMatch")
    private ScorePerMatch___ scorePerMatch;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("trnRating")
    public TrnRating___ getTrnRating() {
        return trnRating;
    }

    @JsonProperty("trnRating")
    public void setTrnRating(TrnRating___ trnRating) {
        this.trnRating = trnRating;
    }

    @JsonProperty("score")
    public Score___ getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(Score___ score) {
        this.score = score;
    }

    @JsonProperty("top1")
    public Top1___ getTop1() {
        return top1;
    }

    @JsonProperty("top1")
    public void setTop1(Top1___ top1) {
        this.top1 = top1;
    }

    @JsonProperty("top3")
    public Top3___ getTop3() {
        return top3;
    }

    @JsonProperty("top3")
    public void setTop3(Top3___ top3) {
        this.top3 = top3;
    }

    @JsonProperty("top5")
    public Top5___ getTop5() {
        return top5;
    }

    @JsonProperty("top5")
    public void setTop5(Top5___ top5) {
        this.top5 = top5;
    }

    @JsonProperty("top6")
    public Top6___ getTop6() {
        return top6;
    }

    @JsonProperty("top6")
    public void setTop6(Top6___ top6) {
        this.top6 = top6;
    }

    @JsonProperty("top10")
    public Top10___ getTop10() {
        return top10;
    }

    @JsonProperty("top10")
    public void setTop10(Top10___ top10) {
        this.top10 = top10;
    }

    @JsonProperty("top12")
    public Top12___ getTop12() {
        return top12;
    }

    @JsonProperty("top12")
    public void setTop12(Top12___ top12) {
        this.top12 = top12;
    }

    @JsonProperty("top25")
    public Top25___ getTop25() {
        return top25;
    }

    @JsonProperty("top25")
    public void setTop25(Top25___ top25) {
        this.top25 = top25;
    }

    @JsonProperty("kd")
    public Kd___ getKd() {
        return kd;
    }

    @JsonProperty("kd")
    public void setKd(Kd___ kd) {
        this.kd = kd;
    }

    @JsonProperty("winRatio")
    public WinRatio___ getWinRatio() {
        return winRatio;
    }

    @JsonProperty("winRatio")
    public void setWinRatio(WinRatio___ winRatio) {
        this.winRatio = winRatio;
    }

    @JsonProperty("matches")
    public Matches___ getMatches() {
        return matches;
    }

    @JsonProperty("matches")
    public void setMatches(Matches___ matches) {
        this.matches = matches;
    }

    @JsonProperty("kills")
    public Kills___ getKills() {
        return kills;
    }

    @JsonProperty("kills")
    public void setKills(Kills___ kills) {
        this.kills = kills;
    }

    @JsonProperty("kpg")
    public Kpg___ getKpg() {
        return kpg;
    }

    @JsonProperty("kpg")
    public void setKpg(Kpg___ kpg) {
        this.kpg = kpg;
    }

    @JsonProperty("scorePerMatch")
    public ScorePerMatch___ getScorePerMatch() {
        return scorePerMatch;
    }

    @JsonProperty("scorePerMatch")
    public void setScorePerMatch(ScorePerMatch___ scorePerMatch) {
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
