package com.project.job.app.commons.repo;

import com.project.job.app.commons.data.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company, String> {
    // Additional query methods (if needed) can be added here
}

