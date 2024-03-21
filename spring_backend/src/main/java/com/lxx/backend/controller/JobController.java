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

import com.lxx.backend.exception.JobNotFoundException;
import com.lxx.backend.model.Job;
import com.lxx.backend.repository.JobRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class JobController {
    
    @Autowired
    private JobRepository jobRepository;

    // wirte the table to database
    @PostMapping("/job")
    public Job newJob(@RequestBody Job newJob) {
        return jobRepository.save(newJob);
    }

    // get all data from database
    @GetMapping("/get_all_jobs")
    public List<Job> getAlljobs() {
        return jobRepository.findAll();
    }

    @GetMapping("/job/{id}")
    Job getJobById(@PathVariable Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));
    }

    @DeleteMapping("/job/{id}")
    String deleteJob(@PathVariable Long id) {
        if(!jobRepository.existsById(id)){
            throw new JobNotFoundException(id);
        }
        jobRepository.deleteById(id);
        return "Job with id " + id + " has been deleted success.";
    }

    @PutMapping("/job/{id}")
    Job updateJob(@RequestBody Job newJob, @PathVariable Long id) {
        return jobRepository.findById(id)
                .map(job -> {
                    job.setCompanyName(newJob.getCompanyName());
                    job.setPosition(newJob.getPosition());
                    job.setLocation(newJob.getLocation());
                    job.setStatus(newJob.getStatus());
                    return jobRepository.save(job);
                }).orElseThrow(() -> new JobNotFoundException(id));
    }
}