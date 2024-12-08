package com.project.job.app.controller;

import com.project.job.app.commons.data.Job;
import com.project.job.app.commons.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    private JobRepo jobRepo;

    @GetMapping("/all")
    public List<Job> getAllJobs() {
        return jobRepo.findAll();
    }

    @PostMapping("/add")
    public Job addJob(@RequestBody Job job) {
        return jobRepo.save(job);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable String id, @RequestBody Job updatedJob) {
        Optional<Job> jobData = jobRepo.findById(id);
        if (jobData.isPresent()) {
            Job job = jobData.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setCompanyName(updatedJob.getCompanyName());
            job.setLocation(updatedJob.getLocation());
            job.setCompany(updatedJob.getCompany());
            return ResponseEntity.ok(jobRepo.save(job));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable String id) {
        if (jobRepo.existsById(id)) {
            jobRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
