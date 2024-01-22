
package com.opentext.qfiniti.importer.ringover.pojo.calls;

import java.util.LinkedHashMap;
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
    "user_id",
    "team_id",
    "limit_count_setted",
    "limit_offset_setted",
    "last_id_offset_setted",
    "filter",
    "call_list_count",
    "call_list",
    "total_call_count",
    "total_missed_call_count"
})

public class Calls {

    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("team_id")
    private Integer teamId;
    @JsonProperty("limit_count_setted")
    private Integer limitCountSetted;
    @JsonProperty("limit_offset_setted")
    private Integer limitOffsetSetted;
    @JsonProperty("last_id_offset_setted")
    private Integer lastIdOffsetSetted;
    @JsonProperty("filter")
    private String filter;
    @JsonProperty("call_list_count")
    private Integer callListCount;
    @JsonProperty("call_list")
    private List<Call> callList;
    @JsonProperty("total_call_count")
    private Integer totalCallCount;
    @JsonProperty("total_missed_call_count")
    private Integer totalMissedCallCount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("user_id")
    public Integer getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonProperty("team_id")
    public Integer getTeamId() {
        return teamId;
    }

    @JsonProperty("team_id")
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    @JsonProperty("limit_count_setted")
    public Integer getLimitCountSetted() {
        return limitCountSetted;
    }

    @JsonProperty("limit_count_setted")
    public void setLimitCountSetted(Integer limitCountSetted) {
        this.limitCountSetted = limitCountSetted;
    }

    @JsonProperty("limit_offset_setted")
    public Integer getLimitOffsetSetted() {
        return limitOffsetSetted;
    }

    @JsonProperty("limit_offset_setted")
    public void setLimitOffsetSetted(Integer limitOffsetSetted) {
        this.limitOffsetSetted = limitOffsetSetted;
    }

    @JsonProperty("last_id_offset_setted")
    public Integer getLastIdOffsetSetted() {
        return lastIdOffsetSetted;
    }

    @JsonProperty("last_id_offset_setted")
    public void setLastIdOffsetSetted(Integer lastIdOffsetSetted) {
        this.lastIdOffsetSetted = lastIdOffsetSetted;
    }

    @JsonProperty("filter")
    public String getFilter() {
        return filter;
    }

    @JsonProperty("filter")
    public void setFilter(String filter) {
        this.filter = filter;
    }

    @JsonProperty("call_list_count")
    public Integer getCallListCount() {
        return callListCount;
    }

    @JsonProperty("call_list_count")
    public void setCallListCount(Integer callListCount) {
        this.callListCount = callListCount;
    }

    @JsonProperty("call_list")
    public List<Call> getCallList() {
        return callList;
    }

    @JsonProperty("call_list")
    public void setCallList(List<Call> callList) {
        this.callList = callList;
    }

    @JsonProperty("total_call_count")
    public Integer getTotalCallCount() {
        return totalCallCount;
    }

    @JsonProperty("total_call_count")
    public void setTotalCallCount(Integer totalCallCount) {
        this.totalCallCount = totalCallCount;
    }

    @JsonProperty("total_missed_call_count")
    public Integer getTotalMissedCallCount() {
        return totalMissedCallCount;
    }

    @JsonProperty("total_missed_call_count")
    public void setTotalMissedCallCount(Integer totalMissedCallCount) {
        this.totalMissedCallCount = totalMissedCallCount;
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
