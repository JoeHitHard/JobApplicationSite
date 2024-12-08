package com.project.job.app.controller;

import com.project.job.app.commons.data.Application;
import com.project.job.app.commons.repo.ApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/application")
public class ApplicationController {

    @Autowired
    private ApplicationRepo applicationRepo;

    @GetMapping("/all")
    public List<Application> getAllApplications() {
        return applicationRepo.findAll();
    }

    @PostMapping("/add")
    public Application addApplication(@RequestBody Application application) {
        return applicationRepo.save(application);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Application> updateApplication(@PathVariable String id, @RequestBody Application updatedApplication) {
        Optional<Application> applicationData = applicationRepo.findById(id);
        if (applicationData.isPresent()) {
            Application application = applicationData.get();
            application.setStatus(updatedApplication.getStatus());
            application.setApplicationDate(updatedApplication.getApplicationDate());
            application.setJob(updatedApplication.getJob());
            application.setCandidate(updatedApplication.getCandidate());
            return ResponseEntity.ok(applicationRepo.save(application));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable String id) {
        if (applicationRepo.existsById(id)) {
            applicationRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

