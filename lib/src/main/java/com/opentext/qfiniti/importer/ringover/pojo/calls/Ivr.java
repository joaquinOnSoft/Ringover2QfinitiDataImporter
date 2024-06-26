
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
    "ivr_id",
    "name",
    "color"
})
public class Ivr {

    @JsonProperty("ivr_id")
    private Integer ivrId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("color")
    private String color;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("ivr_id")
    public Integer getIvrId() {
        return ivrId;
    }

    @JsonProperty("ivr_id")
    public void setIvrId(Integer ivrId) {
        this.ivrId = ivrId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("color")
    public String getColor() {
        return color;
    }

    @JsonProperty("color")
    public void setColor(String color) {
        this.color = color;
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
