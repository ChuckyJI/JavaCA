import { Navbar } from "../NavbarAndFooter/Navbar";
import { AdminSidebar } from "./AdminComponents/AdminSidebar";
import { React, useState } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { StudentController } from "./AdminComponents/SidebarComponents/StudentController";
import { LecturerController } from "./AdminComponents/SidebarComponents/LecturerController";
import { BlockManage } from "./AdminComponents/SidebarComponents/BlockManage";
import CourseController from "./AdminComponents/SidebarComponents/CourseController";
import { useEffect } from "react";

export const AdminPage = () => {
  const [contextStatus, setContextStatus] = useState(0);

  const handleChangeStatus = (newStatus) => {
    setContextStatus(newStatus);
    localStorage.setItem("contextStatus", newStatus);
  };

  let componentToRender = null;
  useEffect(() => {
    const storedStatus = localStorage.getItem("contextStatus");
    if (storedStatus) {
      setContextStatus(parseInt(storedStatus, 10));
    }
  }, []);

  useEffect(() => {
    document.title = "(SA56-T2) Admin Page";
  }, []);

  if (contextStatus === 1) {
    componentToRender = <CourseController />;
  } else if (contextStatus === 2) {
    componentToRender = <StudentController />;
  } else if (contextStatus === 3) {
    componentToRender = <LecturerController />;
  } else if (contextStatus === 4) {
    componentToRender = <BlockManage />;
  }

  return (
    <Router>
      <div className="min-vh-100 parent-container">
        <div className="navbar navbar-full-width row">
          <Navbar />
        </div>
        <div className="container-fluid">
          <div className="row">
            <div className="col-1">
              <AdminSidebar onChangeStatus={handleChangeStatus} />
            </div>
            <div className="content-wrapper pt-5 col-9">
              {componentToRender}
            </div>
          </div>
        </div>
      </div>
    </Router>
  );
};
