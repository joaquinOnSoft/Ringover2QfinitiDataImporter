
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
    "raw",
    "country_code",
    "country",
    "e164",
    "international",
    "international_alt",
    "national",
    "national_alt",
    "rfc3966",
    "is_short_code"
})
public class Format__1 {

    @JsonProperty("raw")
    private Long raw;
    @JsonProperty("country_code")
    private String countryCode;
    @JsonProperty("country")
    private String country;
    @JsonProperty("e164")
    private String e164;
    @JsonProperty("international")
    private String international;
    @JsonProperty("international_alt")
    private String internationalAlt;
    @JsonProperty("national")
    private String national;
    @JsonProperty("national_alt")
    private String nationalAlt;
    @JsonProperty("rfc3966")
    private String rfc3966;
    @JsonProperty("is_short_code")
    private Boolean isShortCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("raw")
    public Long getRaw() {
        return raw;
    }

    @JsonProperty("raw")
    public void setRaw(Long raw) {
        this.raw = raw;
    }

    @JsonProperty("country_code")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonProperty("country_code")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("e164")
    public String getE164() {
        return e164;
    }

    @JsonProperty("e164")
    public void setE164(String e164) {
        this.e164 = e164;
    }

    @JsonProperty("international")
    public String getInternational() {
        return international;
    }

    @JsonProperty("international")
    public void setInternational(String international) {
        this.international = international;
    }

    @JsonProperty("international_alt")
    public String getInternationalAlt() {
        return internationalAlt;
    }

    @JsonProperty("international_alt")
    public void setInternationalAlt(String internationalAlt) {
        this.internationalAlt = internationalAlt;
    }

    @JsonProperty("national")
    public String getNational() {
        return national;
    }

    @JsonProperty("national")
    public void setNational(String national) {
        this.national = national;
    }

    @JsonProperty("national_alt")
    public String getNationalAlt() {
        return nationalAlt;
    }

    @JsonProperty("national_alt")
    public void setNationalAlt(String nationalAlt) {
        this.nationalAlt = nationalAlt;
    }

    @JsonProperty("rfc3966")
    public String getRfc3966() {
        return rfc3966;
    }

    @JsonProperty("rfc3966")
    public void setRfc3966(String rfc3966) {
        this.rfc3966 = rfc3966;
    }

    @JsonProperty("is_short_code")
    public Boolean getIsShortCode() {
        return isShortCode;
    }

    @JsonProperty("is_short_code")
    public void setIsShortCode(Boolean isShortCode) {
        this.isShortCode = isShortCode;
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
