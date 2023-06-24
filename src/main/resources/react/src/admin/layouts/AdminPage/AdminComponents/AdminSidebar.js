import React from "react";
import { Button } from "reactstrap";
import logoImage from "./static/logo.png";
import courseImage from "./static/course.png";
import studentImage from "./static/student.png";
import lecturerImage from "./static/grade.png";
import blockedlistImage from "./static/enrollment.png";

export const AdminSidebar = ({ onChangeStatus }) => {
  const handleClick1 = () => {
    onChangeStatus(1);
  };

  const handleClick2 = () => {
    onChangeStatus(2);
  };

  const handleClick3 = () => {
    onChangeStatus(3);
  };

  const handleClick4 = () => {
    onChangeStatus(4);
  };

  return (
    // <div className="d-flex flex-column">
    //   <nav className="d-flex flex-column align-items-center">
    //     <div className="m-3">
    //       <h3>Menu Here</h3>
    //     </div>
    //     <ul className="list-unstyled mt-3">
    //       <li className="mb-3">
    //         <Button onClick={handleClick1} className="sidebar-btn">
    //           Manage Students
    //         </Button>
    //       </li>
    //       <li className="mb-3">
    //         <Button onClick={handleClick2} className="sidebar-btn">
    //           Manage Lecturers
    //         </Button>
    //       </li>
    //       <li className="mb-3">
    //         <Button onClick={handleClick3} className="sidebar-btn">
    //           Manage Courses
    //         </Button>
    //       </li>
    //       <li className="mb-3">
    //         <Button onClick={handleClick4} className="sidebar-btn">
    //           Manage Blocks
    //         </Button>
    //       </li>
    //     </ul>
    //   </nav>
    // </div>
    <div className="row rowall">
      <div
        className="rowset text-center"
        style={{ backgroundColor: "#f8f8f8", width: "6%", padding: "0px" }}
      >
        <img src={logoImage} width="50%" style={{ marginBottom: "10px" }} />
        <div>
          <button
            className="btn btn-link"
            style={{ textDecoration: "none", padding: 0 }}
            onClick={handleClick1}
          >
            <img src={courseImage} width="25%" style={{ marginTop: "20px" }} />
            <div
              style={{
                color: "#18447e",
                fontWeight: "lighter",
              }}
            >
              <span
                data-toggle="tooltip"
                data-placement="top"
                title="Manage Courses"
              >
                Course
              </span>
            </div>
          </button>
        </div>
        <div>
          <button
            className="btn btn-link"
            style={{ textDecoration: "none", padding: 0 }}
            onClick={handleClick2}
          >
            <img src={studentImage} width="25%" style={{ marginTop: "20px" }} />
            <div
              style={{
                color: "#18447e",
                fontWeight: "lighter",
              }}
            >
              <span
                data-toggle="tooltip"
                data-placement="top"
                title="Manage Stuents"
              >
                Student
              </span>
            </div>
          </button>
        </div>
        <div>
          <button
            className="btn btn-link"
            style={{ textDecoration: "none", padding: 0 }}
            onClick={handleClick3}
          >
            <img
              src={lecturerImage}
              width="25%"
              style={{ marginTop: "20px" }}
            />
            <div
              style={{
                color: "#18447e",
                fontWeight: "lighter",
              }}
            >
              <span
                data-toggle="tooltip"
                data-placement="top"
                title="Manage Lecturers"
              >
                Lecturer
              </span>
            </div>
          </button>
        </div>
        <div>
          <button
            className="btn btn-link"
            style={{ textDecoration: "none", padding: 0 }}
            onClick={handleClick4}
          >
            <img
              src={blockedlistImage}
              width="25%"
              style={{ marginTop: "20px" }}
            />
            <div
              style={{
                color: "#18447e",
                fontWeight: "lighter",
              }}
            >
              <span
                data-toggle="tooltip"
                data-placement="top"
                title="Manage block list"
              >
                Blocked Students
              </span>
            </div>
          </button>
        </div>
      </div>
    </div>
  );
};
