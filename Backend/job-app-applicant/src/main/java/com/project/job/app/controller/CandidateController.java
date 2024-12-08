package com.project.job.app.controller;

import com.project.job.app.commons.data.Candidate;
import com.project.job.app.commons.repo.CandidateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/candidate")
public class CandidateController {

    @Autowired
    private CandidateRepo candidateRepo;

    @GetMapping("/all")
    public List<Candidate> getAllCandidates() {
        return candidateRepo.findAll();
    }

    @PostMapping("/add")
    public Candidate addCandidate(@RequestBody Candidate candidate) {
        return candidateRepo.save(candidate);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable String id, @RequestBody Candidate updatedCandidate) {
        Optional<Candidate> candidateData = candidateRepo.findById(id);
        if (candidateData.isPresent()) {
            Candidate candidate = candidateData.get();
            candidate.setName(updatedCandidate.getName());
            candidate.setEmail(updatedCandidate.getEmail());
            candidate.setResumeLink(updatedCandidate.getResumeLink());
            candidate.setPhoneNumber(updatedCandidate.getPhoneNumber());
            return ResponseEntity.ok(candidateRepo.save(candidate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable String id) {
        if (candidateRepo.existsById(id)) {
            candidateRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

