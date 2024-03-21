import React from 'react'
import { Link } from 'react-router-dom'

export default function Navbar() {
  return (
    <div>
      <nav className="navbar navbar-expand-lg bg-body-tertiary">
        <div className="container-fluid">
          <Link className="btn btn-success" to="/add_new_job">ADD JOB</Link>
          <span class="navbar-brand mb-0 h1">Dummy Offer Management System</span>
          <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <a href="https://github.com/XxeLexX?tab=repositories" type="button" class="btn btn-link btn-sm">@Xiaoxi Li</a>
        </div>
      </nav>
    </div>
  )
}
