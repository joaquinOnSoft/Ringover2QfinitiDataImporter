
package com.opentext.qfiniti.importer.ringover.pojo.calls;

import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "number",
    "label",
    "type",
    "user_id",
    "ivr_id",
    "conference_id",
    "is_sms",
    "is_sms_write",
    "is_callable",
    "format"
})
public class Number {

    @JsonProperty("number")
    private Long number;
    @JsonProperty("label")
    private String label;
    @JsonProperty("type")
    private String type;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("ivr_id")
    private Integer ivrId;
    @JsonProperty("conference_id")
    private Integer conferenceId;
    @JsonProperty("is_sms")
    private Boolean isSms;
    @JsonProperty("is_sms_write")
    private Boolean isSmsWrite;
    @JsonProperty("is_callable")
    private Boolean isCallable;
    @JsonProperty("format")
    private Format format;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("number")
    public Long getNumber() {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(Long number) {
        this.number = number;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("user_id")
    public Integer getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonProperty("ivr_id")
    public Integer getIvrId() {
        return ivrId;
    }

    @JsonProperty("ivr_id")
    public void setIvrId(Integer ivrId) {
        this.ivrId = ivrId;
    }

    @JsonProperty("conference_id")
    public Integer getConferenceId() {
        return conferenceId;
    }

    @JsonProperty("conference_id")
    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }

    @JsonProperty("is_sms")
    public Boolean getIsSms() {
        return isSms;
    }

    @JsonProperty("is_sms")
    public void setIsSms(Boolean isSms) {
        this.isSms = isSms;
    }

    @JsonProperty("is_sms_write")
    public Boolean getIsSmsWrite() {
        return isSmsWrite;
    }

    @JsonProperty("is_sms_write")
    public void setIsSmsWrite(Boolean isSmsWrite) {
        this.isSmsWrite = isSmsWrite;
    }

    @JsonProperty("is_callable")
    public Boolean getIsCallable() {
        return isCallable;
    }

    @JsonProperty("is_callable")
    public void setIsCallable(Boolean isCallable) {
        this.isCallable = isCallable;
    }

    @JsonProperty("format")
    public Format getFormat() {
        return format;
    }

    @JsonProperty("format")
    public void setFormat(Format format) {
        this.format = format;
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
