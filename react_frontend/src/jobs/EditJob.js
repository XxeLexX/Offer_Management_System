import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function EditJob() {
  let navigate = useNavigate();

  const { id } = useParams();

  const [job, setJob] = useState({
    companyName: "",
    position: "",
    location: "",
    status:"",
  });

  const { companyName, position, location, status } = job;

  const onInputChange = (e) => {
    setJob({ ...job, [e.target.name]: e.target.value });
  };

  useEffect(() => {
    loadJob();
  }, []);

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.put(`http://localhost:8080/job/${id}`, job);
    navigate("/");
  };

  const loadJob = async () => {
    const result = await axios.get(`http://localhost:8080/job/${id}`);
    setJob(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Edit Job</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="companyName" className="form-label">
                Name of Company
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="new company name"
                name="companyName"
                value={companyName}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="position" className="form-label">
                Position of the job
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="new position"
                name="position"
                value={position}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="location" className="form-label">
                Location
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="new location"
                name="location"
                value={location}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="status" className="form-label">
                Apply status
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="z.B. APPLYING, REJECTED or OFFER"
                name="status"
                value={status}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <button type="submit" className="btn btn-outline-primary">
              Submit
            </button>
            <Link className="btn btn-outline-danger mx-2" to="/">
              Cancel
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}