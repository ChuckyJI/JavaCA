package com.example.javaca.controller;
// This is designed by SA56 Team2

import com.example.javaca.pojo.Enrollment;
import com.example.javaca.pojo.Student;
import com.example.javaca.service.Impl.adminService;
import com.example.javaca.service.Impl.enrollmentService;
import com.example.javaca.service.Impl.lecturerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class adminController {
    @Autowired
    public adminService adminService;
    public lecturerService lecturerService;
    public enrollmentService enrollmentService;

    public adminController(com.example.javaca.service.Impl.adminService adminService,lecturerService lecturerService,enrollmentService enrollmentService) {
        this.adminService = adminService;
        this.lecturerService=lecturerService;
        this.enrollmentService=enrollmentService;
    }

    @GetMapping("/doubleCheck")
    public String doubleCheckReject(Model model, HttpSession session){
        Student student = (Student) session.getAttribute("sessionid");
        if(student == null) return "redirect:/";
        if(student.getRole().getId()==2L){
            return "redirect:/lecturer/viewCourselec";
        }
        if(student.getRole().getId()==1L){
            return "redirect:/student/viewCourse";
        }
        model.addAttribute("courseStudentReject",adminService.listStudentIsReject());
        model.addAttribute("studentLogin",student);
        return "redirect:http://localhost:3000/admin";
    }

    @GetMapping("/doubleCheck/{studentname}/{coursename}")
    public String rejectStudent(@PathVariable("studentname") String studentname , @PathVariable("coursename") String coursename, Model model,HttpSession session){
        Student student = (Student) session.getAttribute("sessionid");
        if(student == null) return "redirect:/";
        if(student.getRole().getId()==2L){
            return "redirect:/lecturer/viewCourselec";
        }
        if(student.getRole().getId()==1L){
            return "redirect:/student/viewCourse";
        }
        Enrollment enrollmentShow= lecturerService.updateEnrollmentResult(studentname,coursename);
        enrollmentShow.setIsReject(Boolean.FALSE);
        enrollmentService.saveEnrollment(enrollmentShow);
        model.addAttribute("studentLogin",student);
        return "redirect:/admin/doubleCheck";
    }
}
