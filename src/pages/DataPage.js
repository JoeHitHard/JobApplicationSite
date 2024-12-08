import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './DataPage.css';

const capitalizeWords = (str) => {
  return str
    .replace(/_/g, ' ')
    .replace(/\b\w/g, (match) => match.toUpperCase())
    .replace(/\s+/g, ' ');
};

const DataPage = ({ title, endpoint, entityName, idField, relatedEntities = [] }) => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [formData, setFormData] = useState({});
  const [isEditing, setIsEditing] = useState(false);
  const [editingId, setEditingId] = useState(null);
  const [relatedData, setRelatedData] = useState({});

  const fetchData = async () => {
    try {
      const response = await axios.get(`${endpoint}/all`);
      setData(response.data);
      setLoading(false);
    } catch (error) {
      console.error(`Error fetching data from ${endpoint}:`, error);
      setLoading(false);
    }
  };

  const fetchRelatedData = async () => {
    const relatedPromises = relatedEntities.map(async (entity) => {
      try {
        const response = await axios.get(entity.endpoint);
        return { [entity.name]: response.data };
      } catch (error) {
        console.error(`Error fetching related data for ${entity.name}:`, error);
        return { [entity.name]: [] };
      }
    });

    const results = await Promise.all(relatedPromises);
    const relatedDataMap = results.reduce((acc, curr) => ({ ...acc, ...curr }), {});
    setRelatedData(relatedDataMap);
  };

  useEffect(() => {
    fetchData();
    if (relatedEntities.length > 0) {
      fetchRelatedData();
    }
  }, [endpoint]);

  const handleInputChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleRelatedFieldChange = (e, relatedEntity) => {
    const selectedValue = e.target.value;
    const selectedObject = relatedData[relatedEntity.name]?.find(
      (item) => item[relatedEntity.idField] === selectedValue
    );

    setFormData({
      ...formData,
      [e.target.name]: selectedObject || null,
    });
  };

  const handleAdd = async () => {
    try {
      await axios.post(`${endpoint}/add`, formData);
      fetchData();
      setFormData({});
    } catch (error) {
      console.error(`Error adding ${entityName.toLowerCase()}:`, error);
    }
  };

  const handleEdit = (item) => {
    setIsEditing(true);
    setEditingId(item[idField]);
    setFormData(item);
  };

  const handleUpdate = async () => {
    try {
      await axios.put(`${endpoint}/update/${editingId}`, formData);
      fetchData();
      setFormData({});
      setIsEditing(false);
      setEditingId(null);
    } catch (error) {
      console.error(`Error updating ${entityName.toLowerCase()}:`, error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await axios.delete(`${endpoint}/delete/${id}`);
      fetchData();
    } catch (error) {
      console.error(`Error deleting ${entityName.toLowerCase()}:`, error);
    }
  };

  if (loading) {
    return <div className="loading">Loading...</div>;
  }

  if (data.length === 0) {
    return <div className="no-data">No data available.</div>;
  }

  const renderFields = (item) => {
    return Object.keys(item).map((key, idx) => {
      if (key.endsWith('Id')) return null;

      const value = item[key];

      if (typeof value === 'object' && value !== null) {
        return (
          <div key={idx} className="nested">
            <strong>{capitalizeWords(key)}:</strong>
            <div className="nested-content">{renderFields(value)}</div>
          </div>
        );
      }

      return (
        <p key={idx}>
          <strong>{capitalizeWords(key)}:</strong> {value}
        </p>
      );
    });
  };

  return (
    <div className="data-page">
      <h2>{title}</h2>

      <div className="form">
        <h3>{isEditing ? `Edit ${entityName}` : `Add New ${entityName}`}</h3>
        {Object.keys(data[0] || {}).map((key) => {
          if (key.endsWith('Id')) return null;

          // Render a dropdown for related entities
          const relatedEntity = relatedEntities.find((entity) => entity.field === key);
          if (relatedEntity) {
            return (
              <div key={key} className="form-group">
                <label>{capitalizeWords(key)}</label>
                <select
                  name={key}
                  value={formData[key]?.[relatedEntity.idField] || ''}
                  onChange={(e) => handleRelatedFieldChange(e, relatedEntity)}
                >
                  <option value="">Select {capitalizeWords(relatedEntity.name)}</option>
                  {relatedData[relatedEntity.name]?.map((option) => (
                    <option key={option[relatedEntity.idField]} value={option[relatedEntity.idField]}>
                      {option[relatedEntity.displayField]}
                    </option>
                  ))}
                </select>
              </div>
            );
          }

          return (
            <div key={key} className="form-group">
              <label>{capitalizeWords(key)}</label>
              <input
                type="text"
                name={key}
                value={formData[key] || ''}
                onChange={handleInputChange}
              />
            </div>
          );
        })}
        <button onClick={isEditing ? handleUpdate : handleAdd}>
          {isEditing ? 'Update' : 'Add'}
        </button>
      </div>

      <div className="card-container">
        {data.map((item, index) => (
          <div className="card" key={index}>
            <div className="card-content">{renderFields(item)}</div>
            <div className="card-actions">
              <button onClick={() => handleEdit(item)}>Edit</button>
              <button onClick={() => handleDelete(item[idField])}>Delete</button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default DataPage;
