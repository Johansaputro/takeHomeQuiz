package com.takehometest.TakeHomeTest.libraries.configuration;

import com.takehometest.TakeHomeTest.models.JobDetails;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Url;

import java.util.List;
import java.util.Map;

public interface DansEndpointService {

    @GET
    Call<List<JobDetails>> getJobList(@Url String url);

    @GET
    Call<JobDetails> getJobDetail(@HeaderMap Map<String, String> headers, @Url String url);
}
