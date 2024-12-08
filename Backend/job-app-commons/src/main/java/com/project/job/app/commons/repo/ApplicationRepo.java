package com.project.job.app.commons.repo;

import com.project.job.app.commons.data.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, String> {
    // Custom query methods
    List<Application> findByStatus(String status);
    List<Application> findByCandidate_CandidateId(String candidateId);
    List<Application> findByJob_JobId(String jobId);
}
