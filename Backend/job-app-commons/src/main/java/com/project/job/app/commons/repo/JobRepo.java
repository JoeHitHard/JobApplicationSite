package com.project.job.app.commons.repo;

import com.project.job.app.commons.data.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<Job, String> {
    // Custom query methods
    List<Job> findByTitleContainingIgnoreCase(String title);
    List<Job> findByCompanyName(String companyName);
}

