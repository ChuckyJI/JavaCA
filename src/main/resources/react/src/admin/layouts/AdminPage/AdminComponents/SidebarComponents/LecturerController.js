import React, { useState, useEffect } from "react";
import axios from "axios";
import { Modal } from "reactstrap";
import { SaveAndCancelButton } from "../SaveAndCancelButton";

export const LecturerController = () => {
  const [students, setStudents] = useState([]);
  const [student, setStudent] = useState({});
  const [isLoading, setIsLoading] = useState(true);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [showMessage, setShowMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    fetchStudents();

    if (errorMessage || showMessage) {
      const timeout = setTimeout(() => {
        setErrorMessage("");
        setShowMessage("");
      }, 3000);
      return () => {
        clearTimeout(timeout);
      };
    }
  }, [errorMessage, showMessage]);

  const openModal = () => {
    setIsModalOpen(true);
  };
  const closeModal = () => {
    setStudent(initialFormState);
    setIsModalOpen(false);
  };
  const clearModel = () => {
    setStudent(initialFormState);
  };

  const initialFormState = {
    id: "",
    name: "",
    nameError: "",
    email: "",
    emailError: "",
    studentId: "",
    college_name: "",
    password: "",
    passwordError: "",
    role: { id: "" },
    collage: {
      id: "",
    },
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    if (!student.name || student.name.trim() === "") {
      setShowMessage("Sorry, name cannot be empty!");
      return;
    }
    if (!student.email || student.email.trim() === "") {
      setShowMessage("Sorry, please enter your email!");
      return;
    }

    if (student.id) {
      updateStudent(student);
    } else {
      addStudent(student);
    }
    setStudent(initialFormState); // Reset the input fields
  };
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    let error = "";

    if (name === "name") {
      if (value.length > 0 && !/^[a-zA-Z ]+$/.test(value)) {
        error = "Please input a valid name!";
      }
    }

    if (name === "email") {
      let trimValue = value.trim();
      if (!trimValue.endsWith(".edu")) {
        error = "Please input a valid academic email!";
      }
    }

    if (name === "password") {
      if (value.length < 8 || value.length > 20) {
        error = "The valid length of password should be between 8 and 20.";
      }
    }

    const updatedStudent = {
      ...student,
      [name]: value,
      nameError: name === "name" ? error : student.nameError,
      emailError: name === "email" ? error : student.emailError,
      passwordError: name === "password" ? error : student.passwordError,
    };

    setErrorMessage(error);
    setStudent(updatedStudent);

    if (name === "collage") {
      setStudent((prevStudent) => ({
        ...prevStudent,
        collage: {
          ...(prevStudent.collage || {}),
          id: value,
        },
      }));
    } else {
      setStudent((prevStudent) => ({
        ...prevStudent,
        [name]: value,
      }));
    }
  };

  function deleteConfirmation(studentId) {
    const result = window.confirm(
      "Are you sure you want to delete this student?"
    );
    if (result) {
      deleteStudent(studentId);
    }
  }
  const showNotice = (msg) => {
    setShowMessage(msg);
    setTimeout(() => {
      setShowMessage("");
    }, 3000);
  };

  const addStudent = async (newStudent) => {
    try {
      newStudent.role = { id: 2 };
      const response = await axios.post("/admin/lecturer", newStudent);
      setStudents([...students, response.data]);
      setStudent({});
      showNotice(
        "Great! You have added this student to the list successfully!"
      );
    } catch (error) {
      console.error(error);
    }
  };

  const fetchStudents = async () => {
    try {
      const response = await axios.get("/admin/lecturer");
      setStudents(response.data);
      setIsLoading(false);
    } catch (error) {
      console.error(error);
    }
  };

  const updateStudent = async (updatedStudent) => {
    try {
      updatedStudent.role = { id: 2 };
      const response = await axios.put("/admin/lecturer", updatedStudent);
      setStudents(
        students.map((student) =>
          student.id === response.data.id ? response.data : student
        )
      );
      setStudent({});
      showNotice("Great! You have updated the list successfully!");
    } catch (error) {
      console.error(error);
    }
  };

  const deleteStudent = async (id) => {
    try {
      await axios.delete(`/admin/lecturer/${id}`);
      setStudents(students.filter((student) => student.id !== id));
      showNotice("You have deleted this student successfully!");
    } catch (error) {
      console.error(error);
    }
  };

  const editStudent = (selectedStudent) => {
    setStudent(selectedStudent);
    openModal();
  };

  return (
    <div>
      <h1>Lecturer List</h1>
      <button
        type="button"
        className="btn btn-secondary mb-3"
        onClick={openModal}
      >
        Add Lecturer
      </button>
      {showMessage && <p>{showMessage}</p>}

      <Modal
        isOpen={isModalOpen}
        onRequestClose={closeModal}
        contentLabel="Add Lecturer"
        className="modal-dialog modal-lg"
      >
        <h2>Add Lecturer</h2>
        {showMessage && <p>{showMessage}</p>}
        <form onSubmit={handleSubmit}>
          <div className="form-group mt-2">
            <label>Name:</label>
            <input
              type="text"
              name="name"
              value={student.name}
              onChange={handleInputChange}
              className="form-control"
            />
            {student.nameError && (
              <div className="error-message">{student.nameError}</div>
            )}
          </div>
          <div className="form-group mt-1">
            <label>Password</label>
            <input
              type="password"
              name="password"
              value={student.password}
              onChange={handleInputChange}
              className="form-control"
            />
            {student.passwordError && (
              <div className="error-message">{student.passwordError}</div>
            )}
          </div>
          <div className="form-group mt-1">
            <label>College_Id</label>
            <input
              type="text"
              name="collage"
              value={student.collage?.id || ""}
              onChange={handleInputChange}
              className="form-control"
            />
          </div>
          <div className="form-group mt-1">
            <label>Email:</label>
            <input
              type="email"
              name="email"
              value={student.email}
              onChange={handleInputChange}
              className="form-control"
            />
            {student.emailError && (
              <div className="error-message">{student.emailError}</div>
            )}
          </div>

          {/* Save and Cancel buttons */}
          <SaveAndCancelButton
            closeModal={closeModal}
            clearModel={clearModel}
          />
        </form>
      </Modal>

      {/* Display the table in the modal */}
      {isLoading ? (
        <p>Loading...</p>
      ) : (
        <table className="table mt-4">
          <thead>
            <tr>
              <th>Name</th>
              <th>LecturerId</th>
              <th>Email</th>
              <th>College</th>
              <th>Edit</th>
              <th>Delete</th>
            </tr>
          </thead>
          <tbody>
            {students.map((student) => (
              <tr key={student.id}>
                <td>{student.name}</td>
                <td>{student.studentId}</td>
                <td>{student.email}</td>
                <td>{student.college_name}</td>
                <td>
                  <button onClick={() => editStudent(student)}>Edit</button>
                </td>
                <td>
                  <button onClick={() => deleteConfirmation(student.id)}>
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};
