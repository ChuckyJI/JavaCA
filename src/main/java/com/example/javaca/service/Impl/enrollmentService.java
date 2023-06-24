package com.example.javaca.service.Impl;
// This is designed by SA56 Team2

import com.example.javaca.pojo.Course;
import com.example.javaca.pojo.Enrollment;
import com.example.javaca.pojo.Student;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.Dictionary;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface enrollmentService {
    List<Course> enrollAllAvailableCourse(String id);
    Enrollment getbyId(Long id);
    Enrollment saveEnrollment(Enrollment enrollment);
    Course getCourseById(Long id);
    Student getCurrentStudent(String id);
    Map<Long,Long> totalNumForOneCourse();
    List<Course> findEnrolledCourse(String studentid);
    LinkedHashMap<String,Integer> conflictCourse(String studentid);
    List<Course> searchByContent(String coursename,String studentid);
    LinkedHashMap<Course,List<Student>> getLecturerByCourse(String string);
    LinkedHashMap<Course,List<Student>> getLecturerByCourseKY(String string);
    Enrollment updateEnrollment(Enrollment enrollment);
    Page<Course> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection,String studentid);
    Page<Course> createPage(List<Course> courses, int pageNo, int pageSize, String sortField, String sortDir);
    void sendEmail(String fromEmail,String toEmail,String subject,String body);
}
