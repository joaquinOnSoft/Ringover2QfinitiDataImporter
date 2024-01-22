
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
    "conference_id",
    "name",
    "numbers"
})
public class Conference {

    @JsonProperty("conference_id")
    private Integer conferenceId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("numbers")
    private List<Number> numbers;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("conference_id")
    public Integer getConferenceId() {
        return conferenceId;
    }

    @JsonProperty("conference_id")
    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("numbers")
    public List<Number> getNumbers() {
        return numbers;
    }

    @JsonProperty("numbers")
    public void setNumbers(List<Number> numbers) {
        this.numbers = numbers;
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
