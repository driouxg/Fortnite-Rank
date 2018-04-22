
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
    "id",
    "accountId",
    "playlist",
    "kills",
    "minutesPlayed",
    "top1",
    "top5",
    "top6",
    "top10",
    "top12",
    "top25",
    "matches",
    "top3",
    "dateCollected",
    "score",
    "platform",
    "trnRating",
    "trnRatingChange"
})
public class RecentMatch {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("accountId")
    private String accountId;
    @JsonProperty("playlist")
    private String playlist;
    @JsonProperty("kills")
    private Integer kills;
    @JsonProperty("minutesPlayed")
    private Integer minutesPlayed;
    @JsonProperty("top1")
    private Integer top1;
    @JsonProperty("top5")
    private Integer top5;
    @JsonProperty("top6")
    private Integer top6;
    @JsonProperty("top10")
    private Integer top10;
    @JsonProperty("top12")
    private Integer top12;
    @JsonProperty("top25")
    private Integer top25;
    @JsonProperty("matches")
    private Integer matches;
    @JsonProperty("top3")
    private Integer top3;
    @JsonProperty("dateCollected")
    private String dateCollected;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("platform")
    private Integer platform;
    @JsonProperty("trnRating")
    private Double trnRating;
    @JsonProperty("trnRatingChange")
    private Double trnRatingChange;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("accountId")
    public String getAccountId() {
        return accountId;
    }

    @JsonProperty("accountId")
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @JsonProperty("playlist")
    public String getPlaylist() {
        return playlist;
    }

    @JsonProperty("playlist")
    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }

    @JsonProperty("kills")
    public Integer getKills() {
        return kills;
    }

    @JsonProperty("kills")
    public void setKills(Integer kills) {
        this.kills = kills;
    }

    @JsonProperty("minutesPlayed")
    public Integer getMinutesPlayed() {
        return minutesPlayed;
    }

    @JsonProperty("minutesPlayed")
    public void setMinutesPlayed(Integer minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    @JsonProperty("top1")
    public Integer getTop1() {
        return top1;
    }

    @JsonProperty("top1")
    public void setTop1(Integer top1) {
        this.top1 = top1;
    }

    @JsonProperty("top5")
    public Integer getTop5() {
        return top5;
    }

    @JsonProperty("top5")
    public void setTop5(Integer top5) {
        this.top5 = top5;
    }

    @JsonProperty("top6")
    public Integer getTop6() {
        return top6;
    }

    @JsonProperty("top6")
    public void setTop6(Integer top6) {
        this.top6 = top6;
    }

    @JsonProperty("top10")
    public Integer getTop10() {
        return top10;
    }

    @JsonProperty("top10")
    public void setTop10(Integer top10) {
        this.top10 = top10;
    }

    @JsonProperty("top12")
    public Integer getTop12() {
        return top12;
    }

    @JsonProperty("top12")
    public void setTop12(Integer top12) {
        this.top12 = top12;
    }

    @JsonProperty("top25")
    public Integer getTop25() {
        return top25;
    }

    @JsonProperty("top25")
    public void setTop25(Integer top25) {
        this.top25 = top25;
    }

    @JsonProperty("matches")
    public Integer getMatches() {
        return matches;
    }

    @JsonProperty("matches")
    public void setMatches(Integer matches) {
        this.matches = matches;
    }

    @JsonProperty("top3")
    public Integer getTop3() {
        return top3;
    }

    @JsonProperty("top3")
    public void setTop3(Integer top3) {
        this.top3 = top3;
    }

    @JsonProperty("dateCollected")
    public String getDateCollected() {
        return dateCollected;
    }

    @JsonProperty("dateCollected")
    public void setDateCollected(String dateCollected) {
        this.dateCollected = dateCollected;
    }

    @JsonProperty("score")
    public Integer getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(Integer score) {
        this.score = score;
    }

    @JsonProperty("platform")
    public Integer getPlatform() {
        return platform;
    }

    @JsonProperty("platform")
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    @JsonProperty("trnRating")
    public Double getTrnRating() {
        return trnRating;
    }

    @JsonProperty("trnRating")
    public void setTrnRating(Double trnRating) {
        this.trnRating = trnRating;
    }

    @JsonProperty("trnRatingChange")
    public Double getTrnRatingChange() {
        return trnRatingChange;
    }

    @JsonProperty("trnRatingChange")
    public void setTrnRatingChange(Double trnRatingChange) {
        this.trnRatingChange = trnRatingChange;
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
