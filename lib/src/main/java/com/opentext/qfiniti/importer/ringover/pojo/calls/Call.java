
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
    "contact_number",
    "from_number",
    "to_number",
    "note",
    "star",
    "tags",
    "voicemail",
    "record",
    "fax",
    "user",
    "conference",
    "ivr",
    "contact",
    "amd",
    "is_archived",
    "scenario_name",
    "scenario_id"
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
    @JsonProperty("contact_number")
    private String contactNumber;
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
    @JsonProperty("conference")
    private Conference conference;
    @JsonProperty("ivr")
    private Ivr ivr;
    @JsonProperty("contact")
    private Contact contact;
    @JsonProperty("amd")
    private Boolean amd;
    @JsonProperty("is_archived")
    private Boolean isArchived;
    @JsonProperty("scenario_name")
    private String scenarioName;
    @JsonProperty("scenario_id")
    private String scenarioId;
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

    @JsonProperty("contact_number")
    public String getContactNumber() {
        return contactNumber;
    }

    @JsonProperty("contact_number")
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
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

    @JsonProperty("contact")
    public Contact getContact() {
        return contact;
    }

    @JsonProperty("contact")
    public void setContact(Contact contact) {
        this.contact = contact;
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

    @JsonProperty("scenario_id")
    public String getScenarioId() {
        return scenarioId;
    }

    @JsonProperty("scenario_id")
    public void setScenarioId(String scenarioId) {
        this.scenarioId = scenarioId;
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
