package com.lxx.backend.exception;

public class JobNotFoundException extends RuntimeException{
    public JobNotFoundException(Long id){
        super("Could not found the user with id " + id);
    }
}
