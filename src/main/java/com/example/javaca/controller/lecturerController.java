package com.example.javaca.controller;
// This is designed by SA56 Team2

import com.example.javaca.pojo.Course;
import com.example.javaca.pojo.Enrollment;
import com.example.javaca.pojo.Grade;
import com.example.javaca.pojo.Student;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.javaca.service.Impl.*;
import com.example.javaca.repository.*;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping("/lecturer")
public class lecturerController {
    @Autowired
    public lecturerService lecturerService;
    public enrollmentService enrollmentService;

    public lecturerController(lecturerService lecturerService, enrollmentService enrollmentService) {
        this.lecturerService = lecturerService;
        this.enrollmentService=enrollmentService;
    }

    @Autowired
    private lecturerRepository lecturerRepository;

    @GetMapping("/viewCourselec")
    public String viewCourseByLec(Model model, HttpSession session){
        Student student = (Student) session.getAttribute("sessionid");
        if(student == null) return "redirect:/";
        if(student.getRole().getId()==1L){
            return "redirect:/student/viewCourse";
        }
        if(student.getRole().getId()==3L){
            return "redirect:http://localhost:3000/admin";
        }
        model.addAttribute("course",lecturerService.listAllCourseByLecturer(student.getStudentId()));
        model.addAttribute("studentLogin",student);
        return "viewCourselec";
    }

    @GetMapping("/viewStudentlec")
    public String viewStudentByLec(Model model, HttpSession session){
        Student student = (Student) session.getAttribute("sessionid");
        if(student == null) return "redirect:/";
        if(student.getRole().getId()==1L){
            return "redirect:/student/viewCourse";
        }
        if(student.getRole().getId()==3L){
            return "redirect:http://localhost:3000/admin";
        }
        model.addAttribute("courseStudent",lecturerService.listAllStudentByCourseByLecturerForStudent(student.getStudentId()));
        model.addAttribute("studentLogin",student);
        return "viewStudentlec";
    }

    @GetMapping("/viewStudentlec/{studentname}/{coursename}")
    public String rejectStudent(@PathVariable("studentname") String studentname ,@PathVariable("coursename") String coursename, Model model){
        Enrollment enrollmentShow= lecturerService.updateEnrollmentResult(studentname,coursename);
        enrollmentShow.setIsReject(Boolean.TRUE);
        enrollmentService.saveEnrollment(enrollmentShow);
        return "redirect:/lecturer/viewStudentlec";
    }

    @GetMapping("/viewGradelec")
    public String viewGrade(Model model, HttpSession session){
        Student student = (Student) session.getAttribute("sessionid");
        if(student == null) return "redirect:/";
        if(student.getRole().getId()==1L){
            return "redirect:/student/viewCourse";
        }
        if(student.getRole().getId()==3L){
            return "redirect:http://localhost:3000/admin";
        }
        model.addAttribute("courseStudent",lecturerService.listAllStudentByCourseByLecturer(student.getStudentId()));
        model.addAttribute("grade",lecturerService.getAllGrade());
        model.addAttribute("studentLogin",student);
        return "viewGradelec";
    }

    @RequestMapping("/getGradelec")
    public String viewGrade1(Model model, HttpSession session, @RequestParam("examinationPart") Double examinationPart,@RequestParam("examinationPercentage") Double examinationPercentage,@RequestParam("projectPart") Double projectPart,@RequestParam("projectPercentage") Double projectPercentage,@RequestParam("studentname") String studentname,@RequestParam("coursename") String coursename){
        Student student = (Student) session.getAttribute("sessionid");
        if(student == null) return "redirect:/";
        if(student.getRole().getId()==1L){
            return "redirect:/student/viewCourse";
        }
        if(student.getRole().getId()==3L){
            return "redirect:http://localhost:3000/admin";
        }

        Double finalmark = examinationPart*examinationPercentage/100+projectPart*projectPercentage/100;
        Grade gradetest = lecturerService.courseMarkEntityByStudent(studentname,coursename);
        if(gradetest == null){
            Grade grade = new Grade();
            grade.setCoursemark(finalmark);
            grade.setCourse(lecturerService.returnCourse(coursename));
            grade.setStudent(lecturerService.returnStudent(studentname));
            lecturerService.saveGrade(grade);
            if(finalmark>=40){
                Enrollment enrollment= lecturerService.updateEnrollmentResult(studentname,coursename);
                enrollment.setIsComplete(Boolean.TRUE);
                enrollment.setIsFailed(Boolean.FALSE);
                enrollmentService.saveEnrollment(enrollment);
            }
            else{
                Enrollment enrollment= lecturerService.updateEnrollmentResult(studentname,coursename);
                enrollment.setIsEnroll(Boolean.FALSE);
                enrollment.setIsFailed(Boolean.TRUE);
                enrollmentService.saveEnrollment(enrollment);
            }
        }
        else{
           if(finalmark>=50){
               gradetest.setCoursemark(finalmark*10);
           }
           else{
               gradetest.setCoursemark(finalmark);
           }
           gradetest.setCourse(lecturerService.returnCourse(coursename));
           gradetest.setStudent(lecturerService.returnStudent(studentname));
           gradetest.setStudent(lecturerService.returnStudent(studentname));
           lecturerService.saveGrade(gradetest);
           if(finalmark>=40){
               Enrollment enrollment= lecturerService.updateEnrollmentResult(studentname,coursename);
               enrollment.setIsComplete(Boolean.TRUE);
               enrollment.setIsFailed(Boolean.FALSE);
               enrollmentService.saveEnrollment(enrollment);
           }
           else{
               Enrollment enrollment= lecturerService.updateEnrollmentResult(studentname,coursename);
               enrollment.setIsComplete(Boolean.TRUE);
               enrollmentService.saveEnrollment(enrollment);
           }
        }
        model.addAttribute("courseStudent",lecturerService.listAllStudentByCourseByLecturer(student.getStudentId()));
        model.addAttribute("grade",lecturerService.getAllGrade());
        model.addAttribute("studentLogin",student);
        return "viewGradelec";
    }
}
