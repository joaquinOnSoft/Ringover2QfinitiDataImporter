
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
public class Format {

    @JsonProperty("raw")
    private Integer raw;
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
    public Integer getRaw() {
        return raw;
    }

    @JsonProperty("raw")
    public void setRaw(Integer raw) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Format.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("raw");
        sb.append('=');
        sb.append(((this.raw == null)?"<null>":this.raw));
        sb.append(',');
        sb.append("countryCode");
        sb.append('=');
        sb.append(((this.countryCode == null)?"<null>":this.countryCode));
        sb.append(',');
        sb.append("country");
        sb.append('=');
        sb.append(((this.country == null)?"<null>":this.country));
        sb.append(',');
        sb.append("e164");
        sb.append('=');
        sb.append(((this.e164 == null)?"<null>":this.e164));
        sb.append(',');
        sb.append("international");
        sb.append('=');
        sb.append(((this.international == null)?"<null>":this.international));
        sb.append(',');
        sb.append("internationalAlt");
        sb.append('=');
        sb.append(((this.internationalAlt == null)?"<null>":this.internationalAlt));
        sb.append(',');
        sb.append("national");
        sb.append('=');
        sb.append(((this.national == null)?"<null>":this.national));
        sb.append(',');
        sb.append("nationalAlt");
        sb.append('=');
        sb.append(((this.nationalAlt == null)?"<null>":this.nationalAlt));
        sb.append(',');
        sb.append("rfc3966");
        sb.append('=');
        sb.append(((this.rfc3966 == null)?"<null>":this.rfc3966));
        sb.append(',');
        sb.append("isShortCode");
        sb.append('=');
        sb.append(((this.isShortCode == null)?"<null>":this.isShortCode));
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
