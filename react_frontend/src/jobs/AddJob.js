import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function AddJob() {
  let navigate = useNavigate();

  const [job, setJob] = useState({
    companyName: "",
    position: "",
    location: "",
    status: "",
  });

  const { companyName, position, location, status } = job;

  const onInputChange = (e) => {
    setJob({ ...job, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.post("http://localhost:8080/job", job);
    navigate("/");
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Add new job</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="companyName" className="form-label">
                Company Name
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="z.B. xxx AG"
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
                placeholder="z.B. Java Developer"
                name="position"
                value={position}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="location" className="form-label">
                Location of the office
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="z.B. Berlin"
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