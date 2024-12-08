// ApplicationsPage.js
import React from 'react';
import DataPage from './DataPage';

const ApplicationsPage = () => {
  return <DataPage
          title="Applications"
          endpoint="http://localhost:8081/api/application"
          entityName="Application"
          idField="applicationId"
          relatedEntities={[
            {
              name: "Job",
              field: "job",
              endpoint: "http://localhost:8082/api/job/all",
              idField: "jobId",
              displayField: "title", // Assuming jobs have a 'jobTitle' field
            },
            {
              name: "Candidate",
              field: "candidate",
              endpoint: "http://localhost:8081/api/candidate/all",
              idField: "candidateId",
              displayField: "name", // Assuming candidates have a 'candidateName' field
            },
          ]}
        />;
};

export default ApplicationsPage;
