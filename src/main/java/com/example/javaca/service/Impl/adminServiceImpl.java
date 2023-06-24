package com.example.javaca.service.Impl;
// This is designed by SA56 Team2

import com.example.javaca.dto.BlockDTO;
import com.example.javaca.pojo.Course;
import com.example.javaca.pojo.Student;
import com.example.javaca.repository.adminRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@Transactional
public class adminServiceImpl implements adminService{
    private adminRepository adminRepository;

    public adminServiceImpl(com.example.javaca.repository.adminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public LinkedHashMap<Course, Student> listStudentIsReject() {
        List<Object[]> courseStudentRejectList = adminRepository.listStudentIsReject();
        LinkedHashMap<Course,Student> courseStudentLinkedHashMap = new LinkedHashMap<>();
        for(Object[] data:courseStudentRejectList){
            Student student=(Student) data[0];
            Course course=(Course) data[1];
            courseStudentLinkedHashMap.put(course,student);
        }
        return courseStudentLinkedHashMap;
    }

    @Override
    public List<BlockDTO> getRejectedStudents() {
        List<Object[]> results = adminRepository.listBlockedStudent();
        List<BlockDTO> blockedStudents = new ArrayList<>();

        for (Object[] result : results) {
            Long courseId = (Long) result[0];
            boolean isReject = (boolean) result[1];
            String studentName = (String) result[2];
            String courseName = (String) result[3];
            Long enrollmentId = (Long)result[4];

            blockedStudents.add(new BlockDTO(courseId, enrollmentId,isReject, studentName, courseName));
        }

        return blockedStudents;
    }

    @Override
    public void updateIsRejectToTrue(Long enrollmentId) {
        adminRepository.updateIsRejectToTrue(enrollmentId);
    }

    @Override
    public int calculateTotalNumber(Long rolenunmber,Long collegeid) {
        List<Student> students = adminRepository.calculateTotalNumber(rolenunmber,collegeid);
        int max = 0;
        for(Student student: students){
            int a = Integer.parseInt(student.getStudentId().substring(2,8));
            if(a>max){
                max = a;
            }
        }
        max ++;
        return max;
    }

    @Override
    public Student getCoursebyStudentId(String studentname) {
        return adminRepository.getStudentbyName(studentname);
    }

    @Override
    public Course getCoursebyCourseId(Long courseid) {
        return adminRepository.getCoursebyName(courseid);
    }
}
