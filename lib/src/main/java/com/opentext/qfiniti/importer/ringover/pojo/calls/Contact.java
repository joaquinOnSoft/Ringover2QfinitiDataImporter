
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
    "contact_id",
    "is_shared",
    "im_owner",
    "social_service",
    "social_service_id",
    "social_profile_url",
    "social_data",
    "firstname",
    "lastname",
    "company",
    "concat_name",
    "color",
    "initial",
    "profile_picture",
    "creation_date",
    "numbers"
})
public class Contact {

    @JsonProperty("contact_id")
    private Integer contactId;
    @JsonProperty("is_shared")
    private Boolean isShared;
    @JsonProperty("im_owner")
    private String imOwner;
    @JsonProperty("social_service")
    private Object socialService;
    @JsonProperty("social_service_id")
    private String socialServiceId;
    @JsonProperty("social_profile_url")
    private Object socialProfileUrl;
    @JsonProperty("social_data")
    private Object socialData;
    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("company")
    private String company;
    @JsonProperty("concat_name")
    private String concatName;
    @JsonProperty("color")
    private String color;
    @JsonProperty("initial")
    private String initial;
    @JsonProperty("profile_picture")
    private Object profilePicture;
    @JsonProperty("creation_date")
    private String creationDate;
    @JsonProperty("numbers")
    private List<Long> numbers;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("contact_id")
    public Integer getContactId() {
        return contactId;
    }

    @JsonProperty("contact_id")
    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    @JsonProperty("is_shared")
    public Boolean getIsShared() {
        return isShared;
    }

    @JsonProperty("is_shared")
    public void setIsShared(Boolean isShared) {
        this.isShared = isShared;
    }

    @JsonProperty("im_owner")
    public String getImOwner() {
        return imOwner;
    }

    @JsonProperty("im_owner")
    public void setImOwner(String imOwner) {
        this.imOwner = imOwner;
    }

    @JsonProperty("social_service")
    public Object getSocialService() {
        return socialService;
    }

    @JsonProperty("social_service")
    public void setSocialService(Object socialService) {
        this.socialService = socialService;
    }

    @JsonProperty("social_service_id")
    public String getSocialServiceId() {
        return socialServiceId;
    }

    @JsonProperty("social_service_id")
    public void setSocialServiceId(String socialServiceId) {
        this.socialServiceId = socialServiceId;
    }

    @JsonProperty("social_profile_url")
    public Object getSocialProfileUrl() {
        return socialProfileUrl;
    }

    @JsonProperty("social_profile_url")
    public void setSocialProfileUrl(Object socialProfileUrl) {
        this.socialProfileUrl = socialProfileUrl;
    }

    @JsonProperty("social_data")
    public Object getSocialData() {
        return socialData;
    }

    @JsonProperty("social_data")
    public void setSocialData(Object socialData) {
        this.socialData = socialData;
    }

    @JsonProperty("firstname")
    public String getFirstname() {
        return firstname;
    }

    @JsonProperty("firstname")
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @JsonProperty("lastname")
    public String getLastname() {
        return lastname;
    }

    @JsonProperty("lastname")
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @JsonProperty("company")
    public String getCompany() {
        return company;
    }

    @JsonProperty("company")
    public void setCompany(String company) {
        this.company = company;
    }

    @JsonProperty("concat_name")
    public String getConcatName() {
        return concatName;
    }

    @JsonProperty("concat_name")
    public void setConcatName(String concatName) {
        this.concatName = concatName;
    }

    @JsonProperty("color")
    public String getColor() {
        return color;
    }

    @JsonProperty("color")
    public void setColor(String color) {
        this.color = color;
    }

    @JsonProperty("initial")
    public String getInitial() {
        return initial;
    }

    @JsonProperty("initial")
    public void setInitial(String initial) {
        this.initial = initial;
    }

    @JsonProperty("profile_picture")
    public Object getProfilePicture() {
        return profilePicture;
    }

    @JsonProperty("profile_picture")
    public void setProfilePicture(Object profilePicture) {
        this.profilePicture = profilePicture;
    }

    @JsonProperty("creation_date")
    public String getCreationDate() {
        return creationDate;
    }

    @JsonProperty("creation_date")
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @JsonProperty("numbers")
    public List<Long> getNumbers() {
        return numbers;
    }

    @JsonProperty("numbers")
    public void setNumbers(List<Long> numbers) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Contact.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("contactId");
        sb.append('=');
        sb.append(((this.contactId == null)?"<null>":this.contactId));
        sb.append(',');
        sb.append("isShared");
        sb.append('=');
        sb.append(((this.isShared == null)?"<null>":this.isShared));
        sb.append(',');
        sb.append("imOwner");
        sb.append('=');
        sb.append(((this.imOwner == null)?"<null>":this.imOwner));
        sb.append(',');
        sb.append("socialService");
        sb.append('=');
        sb.append(((this.socialService == null)?"<null>":this.socialService));
        sb.append(',');
        sb.append("socialServiceId");
        sb.append('=');
        sb.append(((this.socialServiceId == null)?"<null>":this.socialServiceId));
        sb.append(',');
        sb.append("socialProfileUrl");
        sb.append('=');
        sb.append(((this.socialProfileUrl == null)?"<null>":this.socialProfileUrl));
        sb.append(',');
        sb.append("socialData");
        sb.append('=');
        sb.append(((this.socialData == null)?"<null>":this.socialData));
        sb.append(',');
        sb.append("firstname");
        sb.append('=');
        sb.append(((this.firstname == null)?"<null>":this.firstname));
        sb.append(',');
        sb.append("lastname");
        sb.append('=');
        sb.append(((this.lastname == null)?"<null>":this.lastname));
        sb.append(',');
        sb.append("company");
        sb.append('=');
        sb.append(((this.company == null)?"<null>":this.company));
        sb.append(',');
        sb.append("concatName");
        sb.append('=');
        sb.append(((this.concatName == null)?"<null>":this.concatName));
        sb.append(',');
        sb.append("color");
        sb.append('=');
        sb.append(((this.color == null)?"<null>":this.color));
        sb.append(',');
        sb.append("initial");
        sb.append('=');
        sb.append(((this.initial == null)?"<null>":this.initial));
        sb.append(',');
        sb.append("profilePicture");
        sb.append('=');
        sb.append(((this.profilePicture == null)?"<null>":this.profilePicture));
        sb.append(',');
        sb.append("creationDate");
        sb.append('=');
        sb.append(((this.creationDate == null)?"<null>":this.creationDate));
        sb.append(',');
        sb.append("numbers");
        sb.append('=');
        sb.append(((this.numbers == null)?"<null>":this.numbers));
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
