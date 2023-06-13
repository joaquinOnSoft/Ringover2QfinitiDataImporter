
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
    "cdr_id",
    "call_id",
    "channel_id",
    "type",
    "direction",
    "last_state",
    "start_time",
    "answered_time",
    "end_time",
    "incall_duration",
    "total_duration",
    "from_number",
    "to_number",
    "note",
    "star",
    "tags",
    "voicemail",
    "record",
    "fax",
    "user",
    "contact",
    "conference",
    "ivr",
    "amd",
    "is_archived",
    "scenario_name"
})

public class Call {

    @JsonProperty("cdr_id")
    private Integer cdrId;
    @JsonProperty("call_id")
    private String callId;
    @JsonProperty("channel_id")
    private String channelId;
    @JsonProperty("type")
    private String type;
    @JsonProperty("direction")
    private String direction;
    @JsonProperty("last_state")
    private String lastState;
    @JsonProperty("start_time")
    private String startTime;
    @JsonProperty("answered_time")
    private String answeredTime;
    @JsonProperty("end_time")
    private String endTime;
    @JsonProperty("incall_duration")
    private Integer incallDuration;
    @JsonProperty("total_duration")
    private Integer totalDuration;
    @JsonProperty("from_number")
    private String fromNumber;
    @JsonProperty("to_number")
    private String toNumber;
    @JsonProperty("note")
    private String note;
    @JsonProperty("star")
    private String star;
    @JsonProperty("tags")
    private List<Tag> tags;
    @JsonProperty("voicemail")
    private String voicemail;
    @JsonProperty("record")
    private String record;
    @JsonProperty("fax")
    private String fax;
    @JsonProperty("user")
    private User user;
    @JsonProperty("contact")
    private Contact contact;
    @JsonProperty("conference")
    private Conference conference;
    @JsonProperty("ivr")
    private Ivr ivr;
    @JsonProperty("amd")
    private Boolean amd;
    @JsonProperty("is_archived")
    private Boolean isArchived;
    @JsonProperty("scenario_name")
    private String scenarioName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("cdr_id")
    public Integer getCdrId() {
        return cdrId;
    }

    @JsonProperty("cdr_id")
    public void setCdrId(Integer cdrId) {
        this.cdrId = cdrId;
    }

    @JsonProperty("call_id")
    public String getCallId() {
        return callId;
    }

    @JsonProperty("call_id")
    public void setCallId(String callId) {
        this.callId = callId;
    }

    @JsonProperty("channel_id")
    public String getChannelId() {
        return channelId;
    }

    @JsonProperty("channel_id")
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("direction")
    public String getDirection() {
        return direction;
    }

    @JsonProperty("direction")
    public void setDirection(String direction) {
        this.direction = direction;
    }

    @JsonProperty("last_state")
    public String getLastState() {
        return lastState;
    }

    @JsonProperty("last_state")
    public void setLastState(String lastState) {
        this.lastState = lastState;
    }

    @JsonProperty("start_time")
    public String getStartTime() {
        return startTime;
    }

    @JsonProperty("start_time")
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @JsonProperty("answered_time")
    public String getAnsweredTime() {
        return answeredTime;
    }

    @JsonProperty("answered_time")
    public void setAnsweredTime(String answeredTime) {
        this.answeredTime = answeredTime;
    }

    @JsonProperty("end_time")
    public String getEndTime() {
        return endTime;
    }

    @JsonProperty("end_time")
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @JsonProperty("incall_duration")
    public Integer getIncallDuration() {
        return incallDuration;
    }

    @JsonProperty("incall_duration")
    public void setIncallDuration(Integer incallDuration) {
        this.incallDuration = incallDuration;
    }

    @JsonProperty("total_duration")
    public Integer getTotalDuration() {
        return totalDuration;
    }

    @JsonProperty("total_duration")
    public void setTotalDuration(Integer totalDuration) {
        this.totalDuration = totalDuration;
    }

    @JsonProperty("from_number")
    public String getFromNumber() {
        return fromNumber;
    }

    @JsonProperty("from_number")
    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber;
    }

    @JsonProperty("to_number")
    public String getToNumber() {
        return toNumber;
    }

    @JsonProperty("to_number")
    public void setToNumber(String toNumber) {
        this.toNumber = toNumber;
    }

    @JsonProperty("note")
    public String getNote() {
        return note;
    }

    @JsonProperty("note")
    public void setNote(String note) {
        this.note = note;
    }

    @JsonProperty("star")
    public String getStar() {
        return star;
    }

    @JsonProperty("star")
    public void setStar(String star) {
        this.star = star;
    }

    @JsonProperty("tags")
    public List<Tag> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @JsonProperty("voicemail")
    public String getVoicemail() {
        return voicemail;
    }

    @JsonProperty("voicemail")
    public void setVoicemail(String voicemail) {
        this.voicemail = voicemail;
    }

    @JsonProperty("record")
    public String getRecord() {
        return record;
    }

    @JsonProperty("record")
    public void setRecord(String record) {
        this.record = record;
    }

    @JsonProperty("fax")
    public String getFax() {
        return fax;
    }

    @JsonProperty("fax")
    public void setFax(String fax) {
        this.fax = fax;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
    }

    @JsonProperty("contact")
    public Contact getContact() {
        return contact;
    }

    @JsonProperty("contact")
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @JsonProperty("conference")
    public Conference getConference() {
        return conference;
    }

    @JsonProperty("conference")
    public void setConference(Conference conference) {
        this.conference = conference;
    }

    @JsonProperty("ivr")
    public Ivr getIvr() {
        return ivr;
    }

    @JsonProperty("ivr")
    public void setIvr(Ivr ivr) {
        this.ivr = ivr;
    }

    @JsonProperty("amd")
    public Boolean getAmd() {
        return amd;
    }

    @JsonProperty("amd")
    public void setAmd(Boolean amd) {
        this.amd = amd;
    }

    @JsonProperty("is_archived")
    public Boolean getIsArchived() {
        return isArchived;
    }

    @JsonProperty("is_archived")
    public void setIsArchived(Boolean isArchived) {
        this.isArchived = isArchived;
    }

    @JsonProperty("scenario_name")
    public String getScenarioName() {
        return scenarioName;
    }

    @JsonProperty("scenario_name")
    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Call.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("cdrId");
        sb.append('=');
        sb.append(((this.cdrId == null)?"<null>":this.cdrId));
        sb.append(',');
        sb.append("callId");
        sb.append('=');
        sb.append(((this.callId == null)?"<null>":this.callId));
        sb.append(',');
        sb.append("channelId");
        sb.append('=');
        sb.append(((this.channelId == null)?"<null>":this.channelId));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("direction");
        sb.append('=');
        sb.append(((this.direction == null)?"<null>":this.direction));
        sb.append(',');
        sb.append("lastState");
        sb.append('=');
        sb.append(((this.lastState == null)?"<null>":this.lastState));
        sb.append(',');
        sb.append("startTime");
        sb.append('=');
        sb.append(((this.startTime == null)?"<null>":this.startTime));
        sb.append(',');
        sb.append("answeredTime");
        sb.append('=');
        sb.append(((this.answeredTime == null)?"<null>":this.answeredTime));
        sb.append(',');
        sb.append("endTime");
        sb.append('=');
        sb.append(((this.endTime == null)?"<null>":this.endTime));
        sb.append(',');
        sb.append("incallDuration");
        sb.append('=');
        sb.append(((this.incallDuration == null)?"<null>":this.incallDuration));
        sb.append(',');
        sb.append("totalDuration");
        sb.append('=');
        sb.append(((this.totalDuration == null)?"<null>":this.totalDuration));
        sb.append(',');
        sb.append("fromNumber");
        sb.append('=');
        sb.append(((this.fromNumber == null)?"<null>":this.fromNumber));
        sb.append(',');
        sb.append("toNumber");
        sb.append('=');
        sb.append(((this.toNumber == null)?"<null>":this.toNumber));
        sb.append(',');
        sb.append("note");
        sb.append('=');
        sb.append(((this.note == null)?"<null>":this.note));
        sb.append(',');
        sb.append("star");
        sb.append('=');
        sb.append(((this.star == null)?"<null>":this.star));
        sb.append(',');
        sb.append("tags");
        sb.append('=');
        sb.append(((this.tags == null)?"<null>":this.tags));
        sb.append(',');
        sb.append("voicemail");
        sb.append('=');
        sb.append(((this.voicemail == null)?"<null>":this.voicemail));
        sb.append(',');
        sb.append("record");
        sb.append('=');
        sb.append(((this.record == null)?"<null>":this.record));
        sb.append(',');
        sb.append("fax");
        sb.append('=');
        sb.append(((this.fax == null)?"<null>":this.fax));
        sb.append(',');
        sb.append("user");
        sb.append('=');
        sb.append(((this.user == null)?"<null>":this.user));
        sb.append(',');
        sb.append("contact");
        sb.append('=');
        sb.append(((this.contact == null)?"<null>":this.contact));
        sb.append(',');
        sb.append("conference");
        sb.append('=');
        sb.append(((this.conference == null)?"<null>":this.conference));
        sb.append(',');
        sb.append("ivr");
        sb.append('=');
        sb.append(((this.ivr == null)?"<null>":this.ivr));
        sb.append(',');
        sb.append("amd");
        sb.append('=');
        sb.append(((this.amd == null)?"<null>":this.amd));
        sb.append(',');
        sb.append("isArchived");
        sb.append('=');
        sb.append(((this.isArchived == null)?"<null>":this.isArchived));
        sb.append(',');
        sb.append("scenarioName");
        sb.append('=');
        sb.append(((this.scenarioName == null)?"<null>":this.scenarioName));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
