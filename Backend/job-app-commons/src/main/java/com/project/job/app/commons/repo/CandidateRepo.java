package com.project.job.app.commons.repo;

import com.project.job.app.commons.data.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate, String> {
    // Custom query methods
    Optional<Candidate> findByEmail(String email);
}

