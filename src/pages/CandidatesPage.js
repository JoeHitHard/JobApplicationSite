import React from 'react';
import DataPage from './DataPage';

const CandidatesPage = () => {
  return (
    <DataPage
      title="Candidates"
      endpoint="http://localhost:8081/api/candidate"
      entityName="Candidate"
      idField="candidateId"
      relatedEntities={[]}
    />

  );
};

export default CandidatesPage;
