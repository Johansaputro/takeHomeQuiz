package com.takehometest.TakeHomeTest.controllers;

import com.takehometest.TakeHomeTest.libraries.constants.ApiPath;
import com.takehometest.TakeHomeTest.libraries.utils.CsvUtil;
import com.takehometest.TakeHomeTest.models.JobDetails;
import com.takehometest.TakeHomeTest.models.JobPositions;
import com.takehometest.TakeHomeTest.outbound.api.DansOutboundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiPath.JOB)
public class JobController {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobController.class);
    @Autowired
    DansOutboundService dansOutboundService;

    @GetMapping(ApiPath.POSITION)
    public ResponseEntity<JobPositions> getJobPositions() {
        LOGGER.info("getJobPositions");
        JobPositions response = dansOutboundService.getJobPositions();
        return ResponseEntity.ok(response);
    }

    @GetMapping(ApiPath.DETAILS)
    public ResponseEntity<JobDetails> getJobDetails(@RequestParam String id) {
        LOGGER.info("getJobDetails. ID {}", id);
        JobDetails response = dansOutboundService.getJobDetails(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping(ApiPath.DOWNLOAD)
    public ResponseEntity<ByteArrayResource> downloadPositionsCsv() {
        LOGGER.info("downloadPositionCsv");

        JobPositions response = dansOutboundService.getJobPositions();
        List<JobDetails> data = response.getJobDetailsList();

        ByteArrayResource csv = CsvUtil.convertToCSV(data);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data.csv");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(csv.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(csv);
    }
}
