package com.lxx.backend.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lxx.backend.exception.JobNotFoundException;
import com.lxx.backend.model.Job;
import com.lxx.backend.repository.JobRepository;

public class JobServiceTest {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobService jobService;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateJob() {
        Job newJob = new Job();
        newJob.setId(1L);
        newJob.setCompanyName("Test Company");
        newJob.setPosition("Test Position");
        newJob.setLocation("Test Location");
        newJob.setStatus("Test Status");

        when(jobRepository.save(any(Job.class))).thenReturn(newJob);

        Job createdJob = jobService.createJob(newJob);

        assertEquals(1L, createdJob.getId());
        assertEquals("Test Company", createdJob.getCompanyName());
        assertEquals("Test Position", createdJob.getPosition());
        assertEquals("Test Location", createdJob.getLocation());
        assertEquals("Test Status", createdJob.getStatus());
    }

    @Test
    public void testGetAllJobs() {
        List<Job> jobList = new ArrayList<>();
        jobList.add(new Job(1L, "Company 1", "Position 1", "Location 1", "Status 1"));
        jobList.add(new Job(2L, "Company 2", "Position 2", "Location 2", "Status 2"));

        when(jobRepository.findAll()).thenReturn(jobList);

        List<Job> retrievedJobs = jobService.getAllJobs();

        assertEquals(2, retrievedJobs.size());
        assertEquals(1L, retrievedJobs.get(0).getId());
        assertEquals("Company 1", retrievedJobs.get(0).getCompanyName());
        assertEquals("Position 1", retrievedJobs.get(0).getPosition());
        assertEquals("Location 1", retrievedJobs.get(0).getLocation());
        assertEquals("Status 1", retrievedJobs.get(0).getStatus());
        assertEquals(2L, retrievedJobs.get(1).getId());
        assertEquals("Company 2", retrievedJobs.get(1).getCompanyName());
        assertEquals("Position 2", retrievedJobs.get(1).getPosition());
        assertEquals("Location 2", retrievedJobs.get(1).getLocation());
        assertEquals("Status 2", retrievedJobs.get(1).getStatus());
    }

    @Test
    public void testGetJobById() {
        Job job = new Job(1L, "Test Company", "Test Position", "Test Location", "Test Status");

        when(jobRepository.findById(1L)).thenReturn(Optional.of(job));

        Job retrievedJob = jobService.getJobById(1L);

        assertEquals(1L, retrievedJob.getId());
        assertEquals("Test Company", retrievedJob.getCompanyName());
        assertEquals("Test Position", retrievedJob.getPosition());
        assertEquals("Test Location", retrievedJob.getLocation());
        assertEquals("Test Status", retrievedJob.getStatus());
    }

    @Test
    public void testDeleteJob() {
        Long id = 1L;

        when(jobRepository.existsById(id)).thenReturn(true);

        String result = jobService.deleteJob(id);

        assertEquals("Job with id 1 has been deleted successfully.", result);
    }

    @Test
    public void testUpdateJob() {
        Long id = 1L;
        Job newJob = new Job();
        newJob.setId(id);
        newJob.setCompanyName("Updated Company");
        newJob.setPosition("Updated Position");
        newJob.setLocation("Updated Location");
        newJob.setStatus("Updated Status");

        Job existingJob = new Job(1L, "Test Company", "Test Position", "Test Location", "Test Status");

        when(jobRepository.findById(id)).thenReturn(Optional.of(existingJob));
        when(jobRepository.save(any(Job.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Job updatedJob = jobService.updateJob(newJob, id);

        assertEquals(id, updatedJob.getId());
        assertEquals("Updated Company", updatedJob.getCompanyName());
        assertEquals("Updated Position", updatedJob.getPosition());
        assertEquals("Updated Location", updatedJob.getLocation());
        assertEquals("Updated Status", updatedJob.getStatus());
    }

    @Test
    public void testUpdateJob_NotFound() {
        Long id = 1L;
        Job newJob = new Job();
        newJob.setId(id);
        newJob.setCompanyName("Updated Company");
        newJob.setPosition("Updated Position");
        newJob.setLocation("Updated Location");
        newJob.setStatus("Updated Status");

        when(jobRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(JobNotFoundException.class, () -> {
            jobService.updateJob(newJob, id);
        });
    }
}
