package com.takehometest.TakeHomeTest.outbound.impl;

import com.takehometest.TakeHomeTest.libraries.configuration.DansApiConfiguration;
import com.takehometest.TakeHomeTest.libraries.configuration.DansEndpointService;
import com.takehometest.TakeHomeTest.models.JobDetails;
import com.takehometest.TakeHomeTest.models.JobPositions;
import com.takehometest.TakeHomeTest.outbound.api.DansOutboundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.takehometest.TakeHomeTest.libraries.constants.ApiPath.OUTBOUND_DETAIL;
import static com.takehometest.TakeHomeTest.libraries.constants.ApiPath.OUTBOUND_POSITIONS;

@Service
public class DansOutboundServiceImpl implements DansOutboundService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DansOutboundServiceImpl.class);

    @Autowired
    DansApiConfiguration dansApiConfiguration;

    @Autowired
    DansEndpointService dansEndpointService;

    private final String CONTENT_TYPE = "Content-Type";
    private final String APPLICATION_JSON = "application/json";
    private final String ACCEPT = "Accept";

    @Override
    public JobPositions getJobPositions() {
        LOGGER.info("getJobPositions");
        String url = dansApiConfiguration.getHost() + OUTBOUND_POSITIONS;
        try {
            Response<List<JobDetails>> response = dansEndpointService.getJobList(url).execute();
            JobPositions jobPositions = new JobPositions();
            jobPositions.setJobDetailsList(response.body());
            return jobPositions;
        } catch (IOException e) {
            LOGGER.error("Failed to get data to {}", url);
            throw new RuntimeException(e);
        }
    }

    @Override
    public JobDetails getJobDetails(String id) {
        LOGGER.info("getJobDetails. ID {}", id);
        String url = dansApiConfiguration.getHost() + OUTBOUND_DETAIL + id;
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put(CONTENT_TYPE, APPLICATION_JSON);
            headers.put(ACCEPT, APPLICATION_JSON);

            Response<JobDetails> response = dansEndpointService.getJobDetail(headers, url).execute();
            return response.body();
        } catch (IOException e) {
            LOGGER.error("Failed to get data to {}", url);
            throw new RuntimeException(e);
        }
    }
}
