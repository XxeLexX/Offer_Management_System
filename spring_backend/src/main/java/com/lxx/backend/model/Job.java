package com.lxx.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Job {
    @Id
    @GeneratedValue
    private Long id;
    private String companyName;
    private String position;
    private String location;
    private String status;

    public Job() {}

    public Job(long id, String companyName, String position, String location, String status) {
        this.id = id;
        this.companyName = companyName;
        this.position = position;
        this.location = location;
        this.status = status;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPosition() {
        return position;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "{" + "id = " + id + ", companyName = " + companyName + ", position =" + position + ", location = " + location + "}";
    }
}
