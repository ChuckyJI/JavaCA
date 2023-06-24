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
  const [showMessage, setShowMessage] = useState("");

  useEffect(() => {
    fetchRejectedStudents();
  }, []);

  const showNotice = (msg) => {
    setShowMessage(msg);
    setTimeout(() => {
      setShowMessage("");
    }, 3000);
  };

  const fetchRejectedStudents = async () => {
    try {
      const response = await axios.get("/admin/block");
      setStudents(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  function rejectConfirmation(id) {
    const result = window.confirm(
      "Are you sure you want to reject the request from the lecturer?"
    );
    if (result) {
      handleReject(id);
    }
    showNotice("You have rejected the request from the lecturer.");
  }

  const handleReject = async (id) => {
    try {
      await axios.put(`/admin/block/${id}`);
      fetchRejectedStudents();
    } catch (error) {
      console.error(error);
    }
  };

  function confirmConfirmation(id, studentName) {
    const result = window.confirm(
      "Are you sure you want to confirm the request from the lecturer?"
    );
    if (result) {
      handleConfirm(id, studentName);
    }
    showNotice("You have confirmed the request from the lecturer.");
  }

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

      {showMessage && <p>{showMessage}</p>}

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
                    confirmConfirmation(student.id, [student.studentName])
                  }
                  className="btn btn-outline-primary"
                >
                  confirm
                </button>
                <button
                  onClick={() => rejectConfirmation(student.enrollmentId)}
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
