package com.takehometest.TakeHomeTest.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({ "id", "type", "url", "created_at", "company", "company_url", "location", "title", "description", "how_to_apply", "company_logo" })
public class JobDetails {

    private String id;

    private String type;

    private String url;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE MMM dd HH:mm:ss zzz yyyy")
    private Date createdAt;

    private String company;

    @JsonProperty("company_url")
    private String companyUrl;

    private String location;

    private String title;

    private String description;

    @JsonProperty("how_to_apply")
    private String howToApply;

    @JsonProperty("company_logo")
    private String companyLogo;
}
