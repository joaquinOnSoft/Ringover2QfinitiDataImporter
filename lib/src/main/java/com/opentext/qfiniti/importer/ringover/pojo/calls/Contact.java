
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
    "firstname",
    "lastname",
    "concat_name",
    "company",
    "color",
    "initial",
    "profile_picture",
    "social_service",
    "social_service_id",
    "social_profile_url",
    "social_data",
    "creation_date",
    "emails",
    "numbers"
})
public class Contact {

    @JsonProperty("contact_id")
    private Integer contactId;
    @JsonProperty("is_shared")
    private Boolean isShared;
    @JsonProperty("im_owner")
    private String imOwner;
    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("concat_name")
    private String concatName;
    @JsonProperty("company")
    private String company;
    @JsonProperty("color")
    private String color;
    @JsonProperty("initial")
    private String initial;
    @JsonProperty("profile_picture")
    private Object profilePicture;
    @JsonProperty("social_service")
    private Object socialService;
    @JsonProperty("social_service_id")
    private String socialServiceId;
    @JsonProperty("social_profile_url")
    private Object socialProfileUrl;
    @JsonProperty("social_data")
    private Object socialData;
    @JsonProperty("creation_date")
    private String creationDate;
    @JsonProperty("emails")
    private List<Email> emails;
    @JsonProperty("numbers")
    private List<Number__1> numbers;
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

    @JsonProperty("concat_name")
    public String getConcatName() {
        return concatName;
    }

    @JsonProperty("concat_name")
    public void setConcatName(String concatName) {
        this.concatName = concatName;
    }

    @JsonProperty("company")
    public String getCompany() {
        return company;
    }

    @JsonProperty("company")
    public void setCompany(String company) {
        this.company = company;
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

    @JsonProperty("creation_date")
    public String getCreationDate() {
        return creationDate;
    }

    @JsonProperty("creation_date")
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @JsonProperty("emails")
    public List<Email> getEmails() {
        return emails;
    }

    @JsonProperty("emails")
    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    @JsonProperty("numbers")
    public List<Number__1> getNumbers() {
        return numbers;
    }

    @JsonProperty("numbers")
    public void setNumbers(List<Number__1> numbers) {
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
