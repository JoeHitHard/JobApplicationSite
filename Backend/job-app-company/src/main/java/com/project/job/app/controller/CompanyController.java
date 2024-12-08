package com.project.job.app.controller;

import com.project.job.app.commons.data.Company;
import com.project.job.app.commons.repo.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyRepo companyRepo;

    @GetMapping("/all")
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    @PostMapping("/add")
    public Company addCompany(@RequestBody Company company) {
        return companyRepo.save(company);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable String id, @RequestBody Company updatedCompany) {
        Optional<Company> companyData = companyRepo.findById(id);
        if (companyData.isPresent()) {
            Company company = companyData.get();
            company.setName(updatedCompany.getName());
            company.setIndustry(updatedCompany.getIndustry());
            company.setWebsite(updatedCompany.getWebsite());
            return ResponseEntity.ok(companyRepo.save(company));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable String id) {
        if (companyRepo.existsById(id)) {
            companyRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
