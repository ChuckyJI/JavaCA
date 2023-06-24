     import React, { useState, useEffect } from "react";
import axios from "axios";
import { Modal } from "reactstrap";
import { SaveAndCancelButton } from "../SaveAndCancelButton";

const initialFormState = {
  id: "",
  courseId: "",
  cousename: "",
  credit: "",
  size: "",
  room: "",
  compulsory: "",
  collage: {
    id: "",
    name: "",
    studentList: [],
    courseList: []
  },
  startingtime: "",
  endingtime: "",
  date: ""
};

export const CourseController = () => {
  const [courses, setCourses] = useState([]);
  const [course, setCourse] = useState(initialFormState);
  const [isLoading, setIsLoading] = useState(true);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [showMessage, setShowMessage] = useState("");
  const [isAddLecturerModalOpen, setIsAddLecturerModalOpen] = useState(false);
  const [studentNames, setStudentNames] = useState("");
  const [isChecked,setIsChecked] = useState(false)

  useEffect(() => {
    fetchCourses();
  }, []);

  const openModal = () => {
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setCourse(initialFormState);
    setIsModalOpen(false);
  };

  const openAddLecturerModal = () => {
    setIsAddLecturerModalOpen(true);
  };

  const closeAddLecturerModal = () => {
    setIsAddLecturerModalOpen(false);
  };

  const clearAddLecturerModal = () => {
    setIsAddLecturerModalOpen(true);
  };

  const fetchCourses = async () => {
    try {
      const response = await axios.get("/admin/course");
      setCourses(response.data);
      setIsLoading(false);
    } catch (error) {
      console.error(error);
    }
  };

  const addLecturer = async (newLecturer) => {
    try {
      newLecturer.courseId = course.id;
      const response = await axios.post("/admin/course//addlec", newLecturer);
      // 执行成功后的操作
    } catch (error) {
      console.error(error);
    }
  };

  const addCourse = async (newCourse) => {
    try {
      newCourse.compulsory = isChecked;
      const response = await axios.post("/admin/course", newCourse);
      setCourses((prevCourses) =>
          prevCourses.map((course) => (course.id === response.data.id ? response.data : course))
      );
      setCourse(initialFormState);
      fetchCourses();
      showNotice("Great! You have added the course successfully!");
    } catch (error) {
      console.error(error);
    }
  };

  const deleteCourse = async (id) => {
    try {
      await axios.delete(`/admin/course/${id}`);
      setCourses((prevCourses) => prevCourses.filter((course) => course.id !== id));
      showNotice("You have deleted the course successfully!");
    } catch (error) {
      console.error(error);
    }
  };

  const updateCourse = async (updatedCourse) => {
    try {
      const response = await axios.put("/admin/course", updatedCourse);
      setCourses((prevCourses) =>
          prevCourses.map((course) => (course.id === response.data.id ? response.data : course))
      );
      setCourse(initialFormState);
      showNotice("Great! You have updated the course successfully!");
    } catch (error) {
      console.error(error);
    }
  };

  const showNotice = (msg) => {
    setShowMessage(msg);
    setTimeout(() => {
      setShowMessage("");
    }, 3000);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;

    if (name === "collage") {
      setCourse((prevCourse) => ({
        ...prevCourse,
        collage: {
          ...(prevCourse.collage || {}),
          id: value
        }
      }));
    } else {
      setCourse((prevCourse) => ({
        ...prevCourse,
        [name]: value
      }));
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (course.id) {
      updateCourse(course);
    } else {
      addCourse(course);
    }
  };

  const editCourse = (selectedCourse) => {
    openModal();
    setCourse(selectedCourse);
  };
  const editLecturer = (selectedCourse) => {
    openAddLecturerModal();
    setCourse(selectedCourse);
  };

  const addLecturerToCourse = (e) => {
    e.preventDefault();
    const lecturer = studentNames.split('\n').map(name => name.trim());
    const newLecturer = { lecturer: lecturer };
    addLecturer(newLecturer);
  };

  function handleCheckboxChange() {
    setIsChecked(!isChecked);
  }

  return (
      <div>
        <h1>Course List</h1>
        <button type="button" className="btn btn-secondary mb-3" onClick={openModal}>
          Add Course
        </button>
        {showMessage && <p>{showMessage}</p>}

        <Modal
            isOpen={isAddLecturerModalOpen}
            onRequestClose={clearAddLecturerModal}
            contentLabel="Add Lecturer"
            className="modal-dialog modal-lg"
        >
          <h2>Course Lecturer(s)</h2>
          {showMessage && <p>{showMessage}</p>}
          <form onSubmit={addLecturerToCourse}>
            <div>
              <label>Lecturer Name(s):</label>
              <textarea
                  rows={5}
                  value={studentNames}
                  onChange={(e) => setStudentNames(e.target.value)}
              />
            </div>
            <SaveAndCancelButton closeModal={closeAddLecturerModal} />
          </form>
        </Modal>

        <Modal
            isOpen={isModalOpen}
            onRequestClose={closeModal}
            contentLabel="Add Course"
            className="modal-dialog modal-lg"
        >
          <h2>Add Course</h2>
          {showMessage && <p>{showMessage}</p>}
          <form onSubmit={handleSubmit}>
            <div className="form-group mt-2">
              <label>CourseId:</label>
              <input
                  type="text"
                  name="courseId"
                  value={course.courseId}
                  onChange={handleInputChange}
                  className="form-control"
              />
            </div>
            <div className="form-group mt-2">
              <label>Course Name:</label>
              <input
                  type="text"
                  name="cousename"
                  value={course.cousename}
                  onChange={handleInputChange}
                  className="form-control"
              />
            </div>
            <div className="form-group mt-1">
              <label>Course Credit:</label>
              <input
                  type="text"
                  name="credit"
                  value={course.credit}
                  onChange={handleInputChange}
                  className="form-control"
              />
            </div>
            <div className="form-group mt-1">
              <label>Course Size:</label>
              <input
                  type="text"
                  name="size"
                  value={course.size}
                  onChange={handleInputChange}
                  className="form-control"
              />
            </div>
            <div className="form-group mt-1">
              <label>Room:</label>
              <input
                  type="text"
                  name="room"
                  value={course.room}
                  onChange={handleInputChange}
                  className="form-control"
              />
            </div>
            <div className="form-group mt-1">
              <label>College:</label>
              <input
                  type="text"
                  name="collage"
                  value={course.collage?.id}
                  onChange={handleInputChange}
                  className="form-control"
              />
            </div>
            <div className="form-group mt-1">
              <label>Course Date:</label>
              <div className="password-container">
              <input
                  type="text"
                  name="date"
                  value={course.date}
                  onChange={handleInputChange}
                  className="form-control password-input"
              />
                <i className="toggle-password fas fa-eye"></i>
              </div>
            </div>
            <div className="form-group mt-1">
              <label>Start Time:</label>
              <div className="password-container">
                <input
                    type="time"
                    name="startingtime"
                    value={course.startingtime}
                    onChange={handleInputChange}
                    className="form-control password-input"
                />
                <i className="toggle-password fas fa-eye"></i>
              </div>
            </div>
            <div className="form-group mt-1">
              <label>End Time:</label>
              <div className="password-container">
                <input
                    type="time"
                    name="endingtime"
                    value={course.endingtime}
                    onChange={handleInputChange}
                    className="form-control password-input"
                />
                <i className="toggle-password fas fa-eye"></i>
              </div>
            </div>
            <div className="form-group mt-2">
              <label>Course Compulsory:</label>
              <input
                  type="checkbox"
                  name="compulsory"
                  checked={isChecked}
                  onChange={handleCheckboxChange}
              />
            </div>
            <SaveAndCancelButton closeModal={closeModal} />
          </form>
        </Modal>
        {isLoading ? (
            <p>Loading...</p>
        ) : (
            <table className="table">
              <thead>
              <tr>
                <th>CourseId</th>
                <th>Course Name</th>
                <th>Room</th>
                <th>Size</th>
                <th>Credits</th>
                <th>Date</th>
                <th>Course Compulsory</th>
                <th>Add Lecturer</th>
                <th>Edit</th>
                <th>Delete</th>
              </tr>
              </thead>
              <tbody>
              {courses.map((course) => (
                  <tr key={course.id}>
                    <td>{course.courseId}</td>
                    <td>{course.cousename}</td>
                    <td>{course.room}</td>
                    <td>{course.size}</td>
                    <td>{course.credit}</td>
                    <td>{course.date}</td>
                    <td>{course.compulsory ? "Yes" : "No"}</td>
                    <td>
                      <button onClick={() => editLecturer(course)}>AddLecturer</button>
                    </td>
                    <td>
                      <button onClick={() => editCourse(course)}>Edit</button>
                    </td>
                    <td>
                      <button onClick={() => deleteCourse(course.id)}>Delete</button>
                    </td>
                  </tr>
              ))}
              </tbody>
            </table>
        )}
      </div>
  );
};

export default CourseController;
