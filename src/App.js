// src/App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './pages/HomePage';
import JobsPage from './pages/JobsPage'; // Placeholder for Jobs page
import CompaniesPage from './pages/CompaniesPage'; // Placeholder for Companies page
import CandidatesPage from './pages/CandidatesPage'; // Placeholder for Candidates page
import ApplicationsPage from './pages/ApplicationsPage'; // Placeholder for Applications page

function App() {
  return (

    <Router>
      <div className="app-container">
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/jobs" element={<JobsPage />} />
          <Route path="/companies" element={<CompaniesPage />} />
          <Route path="/candidates" element={<CandidatesPage />} />
          <Route path="/applications" element={<ApplicationsPage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
