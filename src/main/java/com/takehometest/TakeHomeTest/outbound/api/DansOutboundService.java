package com.takehometest.TakeHomeTest.outbound.api;

import com.takehometest.TakeHomeTest.models.JobDetails;
import com.takehometest.TakeHomeTest.models.JobPositions;

public interface DansOutboundService {

    public JobPositions getJobPositions();
    public JobDetails getJobDetails(String id);
}
