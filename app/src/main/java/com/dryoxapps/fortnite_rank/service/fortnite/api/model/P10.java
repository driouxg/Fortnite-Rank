
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
public class P10 {

    @JsonProperty("trnRating")
    private TrnRating_ trnRating;
    @JsonProperty("score")
    private Score_ score;
    @JsonProperty("top1")
    private Top1_ top1;
    @JsonProperty("top3")
    private Top3_ top3;
    @JsonProperty("top5")
    private Top5_ top5;
    @JsonProperty("top6")
    private Top6_ top6;
    @JsonProperty("top10")
    private Top10_ top10;
    @JsonProperty("top12")
    private Top12_ top12;
    @JsonProperty("top25")
    private Top25_ top25;
    @JsonProperty("kd")
    private Kd_ kd;
    @JsonProperty("winRatio")
    private WinRatio_ winRatio;
    @JsonProperty("matches")
    private Matches_ matches;
    @JsonProperty("kills")
    private Kills_ kills;
    @JsonProperty("kpg")
    private Kpg_ kpg;
    @JsonProperty("scorePerMatch")
    private ScorePerMatch_ scorePerMatch;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("trnRating")
    public TrnRating_ getTrnRating() {
        return trnRating;
    }

    @JsonProperty("trnRating")
    public void setTrnRating(TrnRating_ trnRating) {
        this.trnRating = trnRating;
    }

    @JsonProperty("score")
    public Score_ getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(Score_ score) {
        this.score = score;
    }

    @JsonProperty("top1")
    public Top1_ getTop1() {
        return top1;
    }

    @JsonProperty("top1")
    public void setTop1(Top1_ top1) {
        this.top1 = top1;
    }

    @JsonProperty("top3")
    public Top3_ getTop3() {
        return top3;
    }

    @JsonProperty("top3")
    public void setTop3(Top3_ top3) {
        this.top3 = top3;
    }

    @JsonProperty("top5")
    public Top5_ getTop5() {
        return top5;
    }

    @JsonProperty("top5")
    public void setTop5(Top5_ top5) {
        this.top5 = top5;
    }

    @JsonProperty("top6")
    public Top6_ getTop6() {
        return top6;
    }

    @JsonProperty("top6")
    public void setTop6(Top6_ top6) {
        this.top6 = top6;
    }

    @JsonProperty("top10")
    public Top10_ getTop10() {
        return top10;
    }

    @JsonProperty("top10")
    public void setTop10(Top10_ top10) {
        this.top10 = top10;
    }

    @JsonProperty("top12")
    public Top12_ getTop12() {
        return top12;
    }

    @JsonProperty("top12")
    public void setTop12(Top12_ top12) {
        this.top12 = top12;
    }

    @JsonProperty("top25")
    public Top25_ getTop25() {
        return top25;
    }

    @JsonProperty("top25")
    public void setTop25(Top25_ top25) {
        this.top25 = top25;
    }

    @JsonProperty("kd")
    public Kd_ getKd() {
        return kd;
    }

    @JsonProperty("kd")
    public void setKd(Kd_ kd) {
        this.kd = kd;
    }

    @JsonProperty("winRatio")
    public WinRatio_ getWinRatio() {
        return winRatio;
    }

    @JsonProperty("winRatio")
    public void setWinRatio(WinRatio_ winRatio) {
        this.winRatio = winRatio;
    }

    @JsonProperty("matches")
    public Matches_ getMatches() {
        return matches;
    }

    @JsonProperty("matches")
    public void setMatches(Matches_ matches) {
        this.matches = matches;
    }

    @JsonProperty("kills")
    public Kills_ getKills() {
        return kills;
    }

    @JsonProperty("kills")
    public void setKills(Kills_ kills) {
        this.kills = kills;
    }

    @JsonProperty("kpg")
    public Kpg_ getKpg() {
        return kpg;
    }

    @JsonProperty("kpg")
    public void setKpg(Kpg_ kpg) {
        this.kpg = kpg;
    }

    @JsonProperty("scorePerMatch")
    public ScorePerMatch_ getScorePerMatch() {
        return scorePerMatch;
    }

    @JsonProperty("scorePerMatch")
    public void setScorePerMatch(ScorePerMatch_ scorePerMatch) {
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
