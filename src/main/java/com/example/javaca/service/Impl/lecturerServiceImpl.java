package com.example.javaca.service.Impl;
// This is designed by SA56 Team2

import com.example.javaca.dto.LecturerDTO;
import com.example.javaca.pojo.Course;
import com.example.javaca.pojo.Enrollment;
import com.example.javaca.pojo.Grade;
import com.example.javaca.pojo.Student;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.example.javaca.repository.*;

import java.util.*;

@Service
public class lecturerServiceImpl implements lecturerService{
    @Resource
    private AdminLecturerRepository adminLecturerRepository;
    private lecturerRepository lecturerRepository;

    public lecturerServiceImpl(com.example.javaca.repository.lecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public List<Course> listAllCourseByLecturer(String lecturerid) {
        return lecturerRepository.listAllCourseByLecturer(lecturerid);
    }

    @Override
    public LinkedHashMap<Course,List<Student>> listAllStudentByCourseByLecturer(String lecturerid) {
        List<Course> wholeCourseList = lecturerRepository.listAllCourseByLecturer(lecturerid);
        LinkedHashMap<Course,List<Student>> courseListMap = new LinkedHashMap<>();

        for(Course data : wholeCourseList){
            Course course = data;
            courseListMap.put(course,lecturerRepository.listAllStudentByCourseByLecturer(course.getCousename()));
        }
        return courseListMap;
    }
    @Override
    public Course returnCourse(String coursename) {
        return lecturerRepository.returnCourse(coursename);
    }

    @Override
    public Student returnStudent(String studentname) {
        return lecturerRepository.returnStudent(studentname);
    }

    @Override
    public Grade saveGrade(Grade grade) {
        return lecturerRepository.save(grade);
    }

    @Override
    public Grade courseMarkEntityByStudent(String studentname, String coursename) {
        return lecturerRepository.courseMarkEntityByStudent(studentname,coursename);
    }

    @Override
    public List<Grade> getAllGrade() {
        return lecturerRepository.findAll();
    }

    @Override
    public Enrollment updateEnrollmentResult(String studentname, String coursename) {
        return lecturerRepository.updateEnrollmentResult(studentname,coursename);
    }

    @Override
    public LinkedHashMap<Course,List<Student>> listAllStudentByCourseByLecturerForStudent(String lecturerid) {
        List<Course> wholeCourseList = lecturerRepository.listAllCourseByLecturer(lecturerid);
        LinkedHashMap<Course,List<Student>> courseListMap = new LinkedHashMap<>();

        for(Course data : wholeCourseList){
            Course course = data;
            courseListMap.put(course,lecturerRepository.listAllStudentByCourseByLecturerForStudent(course.getCousename()));
        }
        return courseListMap;
    }
    @Override
    public List<LecturerDTO> getAllLecturers() {
        List<Object[]> lecturerDetails = lecturerRepository.findAllLecturerDetails();
        List<LecturerDTO> lecturers = new ArrayList<>();

        for (Object[] lecturer : lecturerDetails) {
            Long id = (Long) lecturer[0];
            String password = (String) lecturer[1];
            String name = (String) lecturer[2];
            String email = (String) lecturer[3];
            String studentId= (String) lecturer[4];
            String college_name = (String)  lecturer[5];
            Long college_id = (Long) lecturer[6];

            LecturerDTO lecturerDTO = new LecturerDTO(id, name, email, studentId, college_name, password,college_id);
            lecturers.add(lecturerDTO);
        }

        return lecturers;
    }

    @Override
    public Student insertLecturer(Student lecturer) {
        return adminLecturerRepository.save(lecturer);
    }

    @Override
    public void deleteLecturer(Long id) {
        adminLecturerRepository.deleteById(id);
    }

    @Override
    public Student updateLecturer(Student lecturer) {
        return adminLecturerRepository.save(lecturer);
    }
}
