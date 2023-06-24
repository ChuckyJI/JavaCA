import React from "react";

export const Footer = () => {
  return (
    <div className="main-color">
      <footer className="container d-flex flex-wrap justify-content-between align-items-center py-5 main-color">
        <p className="col-md-4 mb-0 text-white">&copy; CAPS App, Inc</p>
        <ul className="nav navbar-dark col-md-4 justify-content-end">
          <li className="nav-item">
            <a href="/home" className="nav-link px-2 text-white">
              Student Page
            </a>
          </li>
          <li className="nav-item">
            <a href="/search" className="nav-link px-2 text-white">
              Lecturer Page
            </a>
          </li>
        </ul>
      </footer>
    </div>
  );
};