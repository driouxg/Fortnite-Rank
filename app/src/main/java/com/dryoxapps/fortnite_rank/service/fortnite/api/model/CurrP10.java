
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
public class CurrP10 {

    @JsonProperty("trnRating")
    private TrnRating____ trnRating;
    @JsonProperty("score")
    private Score____ score;
    @JsonProperty("top1")
    private Top1____ top1;
    @JsonProperty("top3")
    private Top3____ top3;
    @JsonProperty("top5")
    private Top5____ top5;
    @JsonProperty("top6")
    private Top6____ top6;
    @JsonProperty("top10")
    private Top10____ top10;
    @JsonProperty("top12")
    private Top12____ top12;
    @JsonProperty("top25")
    private Top25____ top25;
    @JsonProperty("kd")
    private Kd____ kd;
    @JsonProperty("winRatio")
    private WinRatio____ winRatio;
    @JsonProperty("matches")
    private Matches____ matches;
    @JsonProperty("kills")
    private Kills____ kills;
    @JsonProperty("kpg")
    private Kpg____ kpg;
    @JsonProperty("scorePerMatch")
    private ScorePerMatch____ scorePerMatch;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("trnRating")
    public TrnRating____ getTrnRating() {
        return trnRating;
    }

    @JsonProperty("trnRating")
    public void setTrnRating(TrnRating____ trnRating) {
        this.trnRating = trnRating;
    }

    @JsonProperty("score")
    public Score____ getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(Score____ score) {
        this.score = score;
    }

    @JsonProperty("top1")
    public Top1____ getTop1() {
        return top1;
    }

    @JsonProperty("top1")
    public void setTop1(Top1____ top1) {
        this.top1 = top1;
    }

    @JsonProperty("top3")
    public Top3____ getTop3() {
        return top3;
    }

    @JsonProperty("top3")
    public void setTop3(Top3____ top3) {
        this.top3 = top3;
    }

    @JsonProperty("top5")
    public Top5____ getTop5() {
        return top5;
    }

    @JsonProperty("top5")
    public void setTop5(Top5____ top5) {
        this.top5 = top5;
    }

    @JsonProperty("top6")
    public Top6____ getTop6() {
        return top6;
    }

    @JsonProperty("top6")
    public void setTop6(Top6____ top6) {
        this.top6 = top6;
    }

    @JsonProperty("top10")
    public Top10____ getTop10() {
        return top10;
    }

    @JsonProperty("top10")
    public void setTop10(Top10____ top10) {
        this.top10 = top10;
    }

    @JsonProperty("top12")
    public Top12____ getTop12() {
        return top12;
    }

    @JsonProperty("top12")
    public void setTop12(Top12____ top12) {
        this.top12 = top12;
    }

    @JsonProperty("top25")
    public Top25____ getTop25() {
        return top25;
    }

    @JsonProperty("top25")
    public void setTop25(Top25____ top25) {
        this.top25 = top25;
    }

    @JsonProperty("kd")
    public Kd____ getKd() {
        return kd;
    }

    @JsonProperty("kd")
    public void setKd(Kd____ kd) {
        this.kd = kd;
    }

    @JsonProperty("winRatio")
    public WinRatio____ getWinRatio() {
        return winRatio;
    }

    @JsonProperty("winRatio")
    public void setWinRatio(WinRatio____ winRatio) {
        this.winRatio = winRatio;
    }

    @JsonProperty("matches")
    public Matches____ getMatches() {
        return matches;
    }

    @JsonProperty("matches")
    public void setMatches(Matches____ matches) {
        this.matches = matches;
    }

    @JsonProperty("kills")
    public Kills____ getKills() {
        return kills;
    }

    @JsonProperty("kills")
    public void setKills(Kills____ kills) {
        this.kills = kills;
    }

    @JsonProperty("kpg")
    public Kpg____ getKpg() {
        return kpg;
    }

    @JsonProperty("kpg")
    public void setKpg(Kpg____ kpg) {
        this.kpg = kpg;
    }

    @JsonProperty("scorePerMatch")
    public ScorePerMatch____ getScorePerMatch() {
        return scorePerMatch;
    }

    @JsonProperty("scorePerMatch")
    public void setScorePerMatch(ScorePerMatch____ scorePerMatch) {
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
