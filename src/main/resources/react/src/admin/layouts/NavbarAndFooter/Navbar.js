import React from "react";
import {Link} from "react-router-dom";

export const Navbar = () => {
  return (
    // <div>
    //   <nav className="navbar navbar-expand-lg navbar-dark main-color">
    //     <div className="container-fluid">
    //       <span className="navbar-brand">CAPS</span>
    //       <button
    //         className="navbar-toggler"
    //         type="button"
    //         data-bs-toggle="collapse"
    //         data-bs-target="#navbarNavDropdown"
    //         aria-controls="navbarNavDropdown"
    //         aria-expanded="false"
    //         aria-label="Toggle Navigation"
    //       >
    //         <span className="navbar-toggler-icon"></span>
    //       </button>
    //       <div className="collapse navbar-collapse" id="navbarNavDropdown">
    //         <ul className="navbar-nav">
    //           <li className="nav-item">
    //             <a href="#" className="nav-link">
    //               Home
    //             </a>
    //           </li>
    //           <li className="nav-item">
    //             <a href="#" className="nav-link">
    //               Student Page
    //             </a>
    //           </li>
    //           <li className="nav-item">
    //             <a href="#" className="nav-link">
    //               Lecturer Page
    //             </a>
    //           </li>
    //           <li className="nav-item">
    //             <a href="#" className="nav-link">
    //               Admin Page
    //             </a>
    //           </li>
    //         </ul>
    //         <ul className="navbar-nav ms-auto">
    //           <li className="nav-item m-1">
    //             <a
    //               href="/login"
    //               type="button"
    //               className="btn btn-outline-light"
    //             >
    //               Sign in
    //             </a>
    //           </li>
    //         </ul>
    //       </div>
    //     </div>
    //   </nav>
    // </div>
    <div className="text-end align-middle bg-light">
      <span className="fw-bold fs-5 text-dark">Admin Page</span>
      <span
        className="fw-bold fs-5 text-primary me-3"
        style={{ marginRight: "30px" }}
      ></span>
      <Link
        className="btn btn-sm btn-danger me-2"
        href="/logout"
        style={{ marginRight: "15px" }}
        to="http://localhost:8080/"
      >
        Logout
      </Link>
    </div>
  );
};
