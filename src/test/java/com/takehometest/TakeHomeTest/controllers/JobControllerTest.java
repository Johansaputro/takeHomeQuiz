package com.takehometest.TakeHomeTest.controllers;

import com.takehometest.TakeHomeTest.libraries.constants.ApiPath;
import com.takehometest.TakeHomeTest.libraries.constants.unittest.JobControllerTestVariable;
import com.takehometest.TakeHomeTest.outbound.api.DansOutboundService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class JobControllerTest extends JobControllerTestVariable {
    @InjectMocks
    private JobController jobController;

    @Mock
    private DansOutboundService dansOutboundService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(jobController).build();
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(dansOutboundService);
    }

    @Test
    public void testGetJobPositions() throws Exception {
        when(this.dansOutboundService.getJobPositions()).thenReturn(JOB_POSITIONS);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get(ApiPath.JOB + ApiPath.POSITION)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.jobDetailsList").isNotEmpty());

        verify(this.dansOutboundService).getJobPositions();
    }

    @Test
    public void testGetJobDetails() throws Exception {
        when(this.dansOutboundService.getJobDetails(anyString())).thenReturn(JOB_DETAILS);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get(ApiPath.JOB + ApiPath.DETAILS)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", anyString());

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        verify(this.dansOutboundService).getJobDetails(anyString());
    }
}
