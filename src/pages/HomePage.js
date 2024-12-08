// src/pages/HomePage.js
import React from 'react';
import { useNavigate } from 'react-router-dom';
import './HomePage.css'; // Import your custom styles

const HomePage = () => {
  const navigate = useNavigate();

  const handleNavigation = (path) => {
    navigate(path);
  };

  return (
    <div className="home-page">
      <div className="welcome-section">
        <h1>Welcome, Recruiter!</h1>
        <p>Manage job postings, candidates, and applications seamlessly.</p>
      </div>

      <div className="options-ruby-layout">
        <div className="option-card" onClick={() => handleNavigation('/companies')}>
          <h3>Companies</h3>
          <p>View and manage companies</p>
        </div>
        <div className="option-card" onClick={() => handleNavigation('/jobs')}>
          <h3>Jobs</h3>
          <p>View and manage job postings</p>
        </div>
        <div className="option-card" onClick={() => handleNavigation('/candidates')}>
          <h3>Candidates</h3>
          <p>View and manage candidates</p>
        </div>
        <div className="option-card" onClick={() => handleNavigation('/applications')}>
          <h3>Applications</h3>
          <p>Track and manage applications</p>
        </div>
      </div>
    </div>
  );
};

export default HomePage;
