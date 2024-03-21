package com.lxx.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lxx.backend.model.Job;

public interface JobRepository extends JpaRepository<Job, Long>{
    
}
