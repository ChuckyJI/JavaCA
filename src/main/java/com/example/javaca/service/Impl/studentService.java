package com.example.javaca.service.Impl;
// This is designed by SA56 Team2

import com.example.javaca.dto.StudentDTO;
import com.example.javaca.pojo.Student;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface studentService {
    Student getStudentbyEmailAndPwd(String email, String pwd);

    List<StudentDTO> getAllStudents();

    Student insertStudent(Student student);

    void deleteStudent(Long id);

    Student updateStudent(Student student);
    void deleteGradebyStudentId(Long studentid);
    void deleteEnrollmentbyStudentId(Long studentid);
}
