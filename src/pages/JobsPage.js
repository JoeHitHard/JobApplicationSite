// JobsPage.js
import React from 'react';
import DataPage from './DataPage';

const JobsPage = () => {
  return <DataPage
            title="Jobs"
            endpoint="http://localhost:8082/api/job"
            entityName="Job"
            idField="jobId"
            relatedEntities={[
              {
                name: "Company",
                field: "company",
                endpoint: "http://localhost:8082/api/company/all",
                idField: "companyId",
                displayField: "name", // Field to display in dropdown
              },
            ]}
          />;
};

export default JobsPage;
