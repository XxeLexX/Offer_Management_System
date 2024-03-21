import React, {useEffect, useState} from 'react'
import axios from 'axios'
import { Link } from 'react-router-dom';

export default function Home() {

    const [jobs, setJobs] = useState([])
    
    useEffect(() => {
        loadJobs();
    },[]);

    const loadJobs = async() => {
        const result = await axios.get("http://localhost:8080/get_all_jobs");
        setJobs(result.data);
    }

    const deleteJob = async (id) => {
        await axios.delete(`http://localhost:8080/job/${id}`);
        loadJobs();
      };
    
    return (
    <div className='containder'>
        <div className='py-4'>
            <table className="table border shadow">
                <thead>
                    <tr>
                    <th scope="col">#</th>
                    <th scope="col">Company Name</th>
                    <th scope="col">Position</th>
                    <th scope="col">Location</th>
                    <th scope="col">Apply Status</th>
                    <th scope="col">Operate</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        jobs.map((job,index)=>(
                            <tr>
                            <th scope="row" key = {index}>{index+1}</th>
                            <td>{job.companyName}</td>
                            <td>{job.position}</td>
                            <td>{job.location}</td>
                            <td>{job.status}</td>
                            <td>
                                <div class="btn-group" role="group" aria-label="Mix buttons">
                                    <Link className="btn btn-warning btn-sm mx-1" to={`/edit_existed_job/${job.id}`}>Edit</Link>
                                    <button className="btn btn-danger btn-sm" onClick={() => deleteJob(job.id)}>Delete</button>
                                </div>
                            </td>
                            </tr>
                        ))
                    }
                </tbody>
            </table>
        </div>
    </div>
  )
}
