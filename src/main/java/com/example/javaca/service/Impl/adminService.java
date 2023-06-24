package com.example.javaca.service.Impl;
// This is designed by SA56 Team2

import com.example.javaca.dto.BlockDTO;
import com.example.javaca.pojo.Course;
import com.example.javaca.pojo.Student;

import java.util.LinkedHashMap;
import java.util.List;

public interface adminService {
    LinkedHashMap<Course, Student> listStudentIsReject();

    List<BlockDTO> getRejectedStudents();

    void updateIsRejectToTrue(Long enrollmentId);

    int calculateTotalNumber(Long rolenunmber,Long collegeid);

    Student getCoursebyStudentId(String studentname);
    Course getCoursebyCourseId(Long courseid);
}
