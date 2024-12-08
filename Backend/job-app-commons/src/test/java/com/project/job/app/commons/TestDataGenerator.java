package com.project.job.app.commons;

import com.project.job.app.commons.repo.CompanyRepo;
import com.project.job.app.commons.repo.JobRepo;
import com.project.job.app.commons.repo.CandidateRepo;
import com.project.job.app.commons.repo.ApplicationRepo;
import com.project.job.app.commons.data.Company;
import com.project.job.app.commons.data.Job;
import com.project.job.app.commons.data.Candidate;
import com.project.job.app.commons.data.Application;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class TestDataGenerator implements CommandLineRunner {

    @Autowired
    private CompanyRepo companyRepository;
    @Autowired
    private JobRepo jobRepository;
    @Autowired
    private CandidateRepo candidateRepository;
    @Autowired
    private ApplicationRepo applicationRepository;

    public static void main(String[] args) {
        SpringApplication.run(TestDataGenerator.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Clear existing data
        applicationRepository.deleteAll();
        candidateRepository.deleteAll();
        jobRepository.deleteAll();
        companyRepository.deleteAll();

        // Generate test data
        generateCompaniesAndJobs();
        generateCandidates();
        generateApplications();
    }

    private void generateCompaniesAndJobs() {
        Faker faker = new Faker();
        for (int i = 0; i < 5; i++) {
            Company company = new Company();
            company.setName(faker.company().name());
            company.setIndustry(faker.company().industry());
            company.setWebsite(faker.internet().url());

            companyRepository.save(company);

            // Generate jobs for each company
            for (int j = 0; j < 3; j++) {
                Job job = new Job();
                job.setTitle(faker.job().title());
                job.setDescription(faker.lorem().paragraph());
                job.setCompanyName(company.getName());
                job.setLocation(faker.address().city());
                job.setCompany(company);

                jobRepository.save(job);
            }
        }
    }

    private void generateCandidates() {
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            Candidate candidate = new Candidate();
            candidate.setName(faker.name().fullName());
            candidate.setEmail(faker.internet().emailAddress());
            candidate.setResumeLink(faker.internet().url());
            candidate.setPhoneNumber(faker.phoneNumber().phoneNumber());

            candidateRepository.save(candidate);
        }
    }

    private void generateApplications() {
        List<Job> jobs = jobRepository.findAll();
        List<Candidate> candidates = candidateRepository.findAll();
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            Job job = jobs.get(random.nextInt(jobs.size()));
            Candidate candidate = candidates.get(random.nextInt(candidates.size()));

            Application application = new Application();
            application.setJob(job);
            application.setCandidate(candidate);
            application.setApplicationDate(System.currentTimeMillis());
            application.setStatus(random.nextBoolean() ? Application.ApplicationStatus.ACCEPTED : Application.ApplicationStatus.PENDING);

            applicationRepository.save(application);
        }
    }
}
