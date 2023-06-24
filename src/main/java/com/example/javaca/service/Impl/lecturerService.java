package com.example.javaca.service.Impl;
// This is designed by SA56 Team2

import com.example.javaca.dto.LecturerDTO;
import com.example.javaca.pojo.Course;
import com.example.javaca.pojo.Enrollment;
import com.example.javaca.pojo.Grade;
import com.example.javaca.pojo.Student;
import org.springframework.data.repository.query.Param;

import java.util.LinkedHashMap;
import java.util.List;

public interface lecturerService {
    List<Course> listAllCourseByLecturer(String lecturerid);
    LinkedHashMap<Course,List<Student>> listAllStudentByCourseByLecturer(String lecturerid);
    Course returnCourse(@Param("coursename") String coursename);
    Student returnStudent(@Param("studentname") String studentname);
    Grade saveGrade(Grade grade);
    Grade courseMarkEntityByStudent(String studentname,String coursename);
    List<Grade> getAllGrade();
    Enrollment updateEnrollmentResult(String studentname, String coursename);
    LinkedHashMap<Course,List<Student>> listAllStudentByCourseByLecturerForStudent(String lecturerid);

    List<LecturerDTO> getAllLecturers();

    Student insertLecturer(Student lecturer);

    void deleteLecturer(Long id);

    Student updateLecturer(Student lecturer);
}
