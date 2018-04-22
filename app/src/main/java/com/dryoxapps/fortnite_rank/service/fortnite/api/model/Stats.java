
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
    "p2",
    "p10",
    "p9",
    "curr_p2",
    "curr_p10",
    "curr_p9"
})
public class Stats {

    @JsonProperty("p2")
    private P2 p2;
    @JsonProperty("p10")
    private P10 p10;
    @JsonProperty("p9")
    private P9 p9;
    @JsonProperty("curr_p2")
    private CurrP2 currP2;
    @JsonProperty("curr_p10")
    private CurrP10 currP10;
    @JsonProperty("curr_p9")
    private CurrP9 currP9;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("p2")
    public P2 getP2() {
        return p2;
    }

    @JsonProperty("p2")
    public void setP2(P2 p2) {
        this.p2 = p2;
    }

    @JsonProperty("p10")
    public P10 getP10() {
        return p10;
    }

    @JsonProperty("p10")
    public void setP10(P10 p10) {
        this.p10 = p10;
    }

    @JsonProperty("p9")
    public P9 getP9() {
        return p9;
    }

    @JsonProperty("p9")
    public void setP9(P9 p9) {
        this.p9 = p9;
    }

    @JsonProperty("curr_p2")
    public CurrP2 getCurrP2() {
        return currP2;
    }

    @JsonProperty("curr_p2")
    public void setCurrP2(CurrP2 currP2) {
        this.currP2 = currP2;
    }

    @JsonProperty("curr_p10")
    public CurrP10 getCurrP10() {
        return currP10;
    }

    @JsonProperty("curr_p10")
    public void setCurrP10(CurrP10 currP10) {
        this.currP10 = currP10;
    }

    @JsonProperty("curr_p9")
    public CurrP9 getCurrP9() {
        return currP9;
    }

    @JsonProperty("curr_p9")
    public void setCurrP9(CurrP9 currP9) {
        this.currP9 = currP9;
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
