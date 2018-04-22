
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
public class P9 {

    @JsonProperty("trnRating")
    private TrnRating__ trnRating;
    @JsonProperty("score")
    private Score__ score;
    @JsonProperty("top1")
    private Top1__ top1;
    @JsonProperty("top3")
    private Top3__ top3;
    @JsonProperty("top5")
    private Top5__ top5;
    @JsonProperty("top6")
    private Top6__ top6;
    @JsonProperty("top10")
    private Top10__ top10;
    @JsonProperty("top12")
    private Top12__ top12;
    @JsonProperty("top25")
    private Top25__ top25;
    @JsonProperty("kd")
    private Kd__ kd;
    @JsonProperty("winRatio")
    private WinRatio__ winRatio;
    @JsonProperty("matches")
    private Matches__ matches;
    @JsonProperty("kills")
    private Kills__ kills;
    @JsonProperty("kpg")
    private Kpg__ kpg;
    @JsonProperty("scorePerMatch")
    private ScorePerMatch__ scorePerMatch;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("trnRating")
    public TrnRating__ getTrnRating() {
        return trnRating;
    }

    @JsonProperty("trnRating")
    public void setTrnRating(TrnRating__ trnRating) {
        this.trnRating = trnRating;
    }

    @JsonProperty("score")
    public Score__ getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(Score__ score) {
        this.score = score;
    }

    @JsonProperty("top1")
    public Top1__ getTop1() {
        return top1;
    }

    @JsonProperty("top1")
    public void setTop1(Top1__ top1) {
        this.top1 = top1;
    }

    @JsonProperty("top3")
    public Top3__ getTop3() {
        return top3;
    }

    @JsonProperty("top3")
    public void setTop3(Top3__ top3) {
        this.top3 = top3;
    }

    @JsonProperty("top5")
    public Top5__ getTop5() {
        return top5;
    }

    @JsonProperty("top5")
    public void setTop5(Top5__ top5) {
        this.top5 = top5;
    }

    @JsonProperty("top6")
    public Top6__ getTop6() {
        return top6;
    }

    @JsonProperty("top6")
    public void setTop6(Top6__ top6) {
        this.top6 = top6;
    }

    @JsonProperty("top10")
    public Top10__ getTop10() {
        return top10;
    }

    @JsonProperty("top10")
    public void setTop10(Top10__ top10) {
        this.top10 = top10;
    }

    @JsonProperty("top12")
    public Top12__ getTop12() {
        return top12;
    }

    @JsonProperty("top12")
    public void setTop12(Top12__ top12) {
        this.top12 = top12;
    }

    @JsonProperty("top25")
    public Top25__ getTop25() {
        return top25;
    }

    @JsonProperty("top25")
    public void setTop25(Top25__ top25) {
        this.top25 = top25;
    }

    @JsonProperty("kd")
    public Kd__ getKd() {
        return kd;
    }

    @JsonProperty("kd")
    public void setKd(Kd__ kd) {
        this.kd = kd;
    }

    @JsonProperty("winRatio")
    public WinRatio__ getWinRatio() {
        return winRatio;
    }

    @JsonProperty("winRatio")
    public void setWinRatio(WinRatio__ winRatio) {
        this.winRatio = winRatio;
    }

    @JsonProperty("matches")
    public Matches__ getMatches() {
        return matches;
    }

    @JsonProperty("matches")
    public void setMatches(Matches__ matches) {
        this.matches = matches;
    }

    @JsonProperty("kills")
    public Kills__ getKills() {
        return kills;
    }

    @JsonProperty("kills")
    public void setKills(Kills__ kills) {
        this.kills = kills;
    }

    @JsonProperty("kpg")
    public Kpg__ getKpg() {
        return kpg;
    }

    @JsonProperty("kpg")
    public void setKpg(Kpg__ kpg) {
        this.kpg = kpg;
    }

    @JsonProperty("scorePerMatch")
    public ScorePerMatch__ getScorePerMatch() {
        return scorePerMatch;
    }

    @JsonProperty("scorePerMatch")
    public void setScorePerMatch(ScorePerMatch__ scorePerMatch) {
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
