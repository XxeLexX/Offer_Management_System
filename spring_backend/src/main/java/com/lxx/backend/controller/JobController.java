package com.lxx.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lxx.backend.model.Job;
import com.lxx.backend.service.JobService;

@RestController
@CrossOrigin("http://localhost:3000")
public class JobController {
    
    @Autowired
    private JobService jobService;

    @PostMapping("/job")
    public Job newJob(@RequestBody Job newJob) {
        return jobService.createJob(newJob);
    }

    @GetMapping("/get_all_jobs")
    public List<Job> getAlljobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/job/{id}")
    Job getJobById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    @DeleteMapping("/job/{id}")
    String deleteJob(@PathVariable Long id) {
        return jobService.deleteJob(id);
    }

    @PutMapping("/job/{id}")
    Job updateJob(@RequestBody Job newJob, @PathVariable Long id) {
        return jobService.updateJob(newJob, id);
    }
}
