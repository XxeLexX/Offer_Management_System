package com.lxx.backend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lxx.backend.model.Job;
import com.lxx.backend.service.JobService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class JobControllerTest {

    private MockMvc mockMvc;

    @Mock
    private JobService jobService;

    @InjectMocks
    private JobController jobController;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(jobController).build();
    }

    @Test
    public void testNewJob() throws Exception {
        Job newJob = new Job();
        newJob.setId(1L);
        newJob.setCompanyName("Test Company");
        newJob.setPosition("Test Position");
        newJob.setLocation("Test Location");
        newJob.setStatus("Test Status");

        when(jobService.createJob(any(Job.class))).thenReturn(newJob);

        mockMvc.perform(post("/job")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(newJob)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.companyName").value("Test Company"))
            .andExpect(jsonPath("$.position").value("Test Position"))
            .andExpect(jsonPath("$.location").value("Test Location"))
            .andExpect(jsonPath("$.status").value("Test Status"));
    }

    @Test
    public void testGetAllJobs() throws Exception {
        List<Job> jobList = new ArrayList<>();
        jobList.add(new Job(1L, "Company 1", "Position 1", "Location 1", "Status 1"));
        jobList.add(new Job(2L, "Company 2", "Position 2", "Location 2", "Status 2"));

        when(jobService.getAllJobs()).thenReturn(jobList);

        mockMvc.perform(get("/get_all_jobs")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].companyName").value("Company 1"))
            .andExpect(jsonPath("$[0].position").value("Position 1"))
            .andExpect(jsonPath("$[0].location").value("Location 1"))
            .andExpect(jsonPath("$[0].status").value("Status 1"))
            .andExpect(jsonPath("$[1].companyName").value("Company 2"))
            .andExpect(jsonPath("$[1].position").value("Position 2"))
            .andExpect(jsonPath("$[1].location").value("Location 2"))
            .andExpect(jsonPath("$[1].status").value("Status 2"));
    }

    @Test
    public void testGetJobById() throws Exception {
        Job job = new Job(1L, "Test Company", "Test Position", "Test Location", "Test Status");

        when(jobService.getJobById(1L)).thenReturn(job);

        mockMvc.perform(get("/job/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.companyName").value("Test Company"))
            .andExpect(jsonPath("$.position").value("Test Position"))
            .andExpect(jsonPath("$.location").value("Test Location"))
            .andExpect(jsonPath("$.status").value("Test Status"));
    }

    // Utility method to convert object to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
