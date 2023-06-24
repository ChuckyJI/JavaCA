import React, { useState, useEffect } from "react";
import axios from "axios";

const initialFormState = {
  enrollmentId: "",
  studentName: "",
  courseName: "",
  id: "",
  reject: [],
};

export const BlockManage = () => {
  const [students, setStudents] = useState([]);

  useEffect(() => {
    fetchRejectedStudents();
  }, []);

  const fetchRejectedStudents = async () => {
    try {
      const response = await axios.get("/admin/block");
      setStudents(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  const handleReject = async (id) => {
    try {
      await axios.put(`/admin/block/${id}`);
      fetchRejectedStudents();
    } catch (error) {
      console.error(error);
    }
  };

  const handleConfirm = async (id, studentName) => {
    try {
      const data = {
        lecturer: studentName,
        courseId: id,
      };
      const response = await axios.put("/admin/block/sendemail", data);
      console.log(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  return (
      <div>
        <h1>Blocked Students</h1>
        <table className="table">
          <thead>
          <tr>
            <th>Student Name</th>
            <th>Course Name</th>
            <th>Action</th>
          </tr>
          </thead>
          <tbody>
          {students.map((student) => (
              <tr key={student.id}>
                <td>{student.studentName}</td>
                <td>{student.courseName}</td>
                <td>
                  <button
                      onClick={() =>
                          handleConfirm(student.id, [student.studentName])
                      }
                      className="btn btn-outline-primary"
                  >
                    confirm
                  </button>
                  <button
                      onClick={() => handleReject(student.enrollmentId)}
                      className="btn btn-outline-danger"
                  >
                    Reject
                  </button>
                </td>
              </tr>
          ))}
          </tbody>
        </table>
      </div>
  );
};
