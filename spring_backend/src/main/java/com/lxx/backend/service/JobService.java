package com.lxx.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxx.backend.exception.JobNotFoundException;
import com.lxx.backend.model.Job;
import com.lxx.backend.repository.JobRepository;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job createJob(Job newJob) {
        return jobRepository.save(newJob);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));
    }

    public String deleteJob(Long id) {
        if (!jobRepository.existsById(id)) {
            throw new JobNotFoundException(id);
        }
        jobRepository.deleteById(id);
        return "Job with id " + id + " has been deleted successfully.";
    }

    public Job updateJob(Job newJob, Long id) {
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
