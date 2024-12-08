package com.project.job.app.commons.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "VARCHAR(36)")
    private String applicationId;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    private Long applicationEpoch;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    public enum ApplicationStatus {
        PENDING, ACCEPTED, REJECTED;
    }

    public Application() {
    }

    public Application(Job job, Candidate candidate, Long applicationEpoch, ApplicationStatus status) {
        this.job = job;
        this.candidate = candidate;
        this.applicationEpoch = applicationEpoch;
        this.status = status;
    }

    // Getters and Setters
    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Long getApplicationDate() {
        return applicationEpoch;
    }

    public void setApplicationDate(Long applicationEpoch) {
        this.applicationEpoch = applicationEpoch;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Application[applicationId='" + applicationId + "', job='" + job + "', candidate='" + candidate + "', applicationEpoch='" + applicationEpoch + "', status='" + status + "']";
    }
}