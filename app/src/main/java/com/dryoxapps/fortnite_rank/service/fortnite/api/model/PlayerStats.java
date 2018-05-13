
package com.dryoxapps.fortnite_rank.service.fortnite.api.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "accountId",
    "platformId",
    "platformName",
    "platformNameLong",
    "epicUserHandle",
    "gameModes",
    "lifeTimeStats",
    "recentMatches"
})
public class PlayerStats {

    @JsonProperty("accountId")
    private String accountId;
    @JsonProperty("platformId")
    private Integer platformId;
    @JsonProperty("platformName")
    private String platformName;
    @JsonProperty("platformNameLong")
    private String platformNameLong;
    @JsonProperty("epicUserHandle")
    private String epicUserHandle;
    @JsonProperty("gameModes")
    private GameModes gameModes;
    @JsonProperty("lifeTimeStats")
    private List<LifeTimeStat> lifeTimeStats = null;
    @JsonProperty("recentMatches")
    private List<RecentMatch> recentMatches = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("accountId")
    public String getAccountId() {
        return accountId;
    }

    @JsonProperty("accountId")
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @JsonProperty("platformId")
    public Integer getPlatformId() {
        return platformId;
    }

    @JsonProperty("platformId")
    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    @JsonProperty("platformName")
    public String getPlatformName() {
        return platformName;
    }

    @JsonProperty("platformName")
    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    @JsonProperty("platformNameLong")
    public String getPlatformNameLong() {
        return platformNameLong;
    }

    @JsonProperty("platformNameLong")
    public void setPlatformNameLong(String platformNameLong) {
        this.platformNameLong = platformNameLong;
    }

    @JsonProperty("epicUserHandle")
    public String getEpicUserHandle() {
        return epicUserHandle;
    }

    @JsonProperty("epicUserHandle")
    public void setEpicUserHandle(String epicUserHandle) {
        this.epicUserHandle = epicUserHandle;
    }

    @JsonProperty("stats")
    public GameModes getGameModes() {
        return gameModes;
    }

    @JsonProperty("stats")
    public void setGameModes(GameModes gameModes) {
        this.gameModes = gameModes;
    }

    @JsonProperty("lifeTimeStats")
    public List<LifeTimeStat> getLifeTimeStats() {
        return lifeTimeStats;
    }

    @JsonProperty("lifeTimeStats")
    public void setLifeTimeStats(List<LifeTimeStat> lifeTimeStats) {
        this.lifeTimeStats = lifeTimeStats;
    }

    @JsonProperty("recentMatches")
    public List<RecentMatch> getRecentMatches() {
        return recentMatches;
    }

    @JsonProperty("recentMatches")
    public void setRecentMatches(List<RecentMatch> recentMatches) {
        this.recentMatches = recentMatches;
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
