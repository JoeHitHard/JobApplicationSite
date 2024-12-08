// CompaniesPage.js
import React from 'react';
import DataPage from './DataPage';

const CompaniesPage = () => {
  return <DataPage title="Companies" endpoint="http://localhost:8082/api/company" entityName="Company"   idField="companyId"/>;
};

export default CompaniesPage;
