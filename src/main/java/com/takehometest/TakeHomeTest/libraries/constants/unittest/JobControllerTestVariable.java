package com.takehometest.TakeHomeTest.libraries.constants.unittest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.takehometest.TakeHomeTest.models.JobDetails;
import com.takehometest.TakeHomeTest.models.JobPositions;

import java.util.ArrayList;
import java.util.List;

public class JobControllerTestVariable {
    private static String JOB_DETAILS_JSON = "{" +
            "\"id\":\"32bf67e5-4971-47ce-985c-44b6b3860cdb\"," +
            "\"type\":\"FullTime\"," +
            "\"url\":\"https://jobs.github.com/positions/32bf67e5-4971-47ce-985c-44b6b3860cdb\"," +
            "\"created_at\":\"Wed May 19 00:49:17 UTC 2021\"," +
            "\"company\":\"SweetRush\"," +
            "\"company_url\":\"https://www.sweetrush.com/\"," +
            "\"location\":\"Remote\"" +
            "}";
    private static String JOB_DETAILS_JSON_2 = "{" +
            "\"id\":\"7638eee4-4e75-4c06-a920-ea7619a311b5\"," +
            "\"type\":\"FullTime\"," +
            "\"url\":\"https://jobs.github.com/positions/7638eee4-4e75-4c06-a920-ea7619a311b5\"," +
            "\"created_at\":\"Tue May 18 17:12:52 UTC 2021\"," +
            "\"company\":\"MANDARINMEDIENGesellschaftfürdigitaleLösungenmbH\"," +
            "\"company_url\":\"https://www.mandarin-medien.de/\"," +
            "\"location\":\"Schwerin\"" +
            "}";

    protected static JobDetails JOB_DETAILS;

    static {
        try {
            JOB_DETAILS = new ObjectMapper().readValue(JOB_DETAILS_JSON, JobDetails.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    protected static JobPositions createJobPositions() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JobDetails jobDetails1 = objectMapper.readValue(JOB_DETAILS_JSON, JobDetails.class);
        JobDetails jobDetails2 = objectMapper.readValue(JOB_DETAILS_JSON_2, JobDetails.class);

        List<JobDetails> listJob = new ArrayList<>();
        listJob.add(jobDetails1);
        listJob.add(jobDetails2);

        JobPositions jobPositions = new JobPositions();
        jobPositions.setJobDetailsList(listJob);

        return jobPositions;
    }

    protected JobPositions JOB_POSITIONS;

    {
        try {
            JOB_POSITIONS = createJobPositions();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
