package com.example.javaca.controller;
// This is designed by SA56 Team2

import com.example.javaca.pojo.Student;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.javaca.service.Impl.*;
import com.example.javaca.repository.*;

@Controller
@RequestMapping("/student")
public class gradeController {
    @Autowired
    private gradeRepository gradeRepository;
    @Autowired
    private gradeService gradeService;

    public gradeController(com.example.javaca.service.Impl.gradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("finalGrade")
    public String showGrade(Model model, HttpSession session){
        Student student = (Student) session.getAttribute("sessionid");
        if(student == null) return "redirect:/";
        if(student.getRole().getId()==2L){
            return "redirect:/lecturer/viewCourselec";
        }
        if(student.getRole().getId()==3L){
            return "redirect:http://localhost:3000/admin";
        }
        model.addAttribute("grade",gradeService.getGradeByStudentId(student.getStudentId()));
        model.addAttribute("avgGPA",gradeService.calaulateAvgGPA(gradeService.getGradeByStudentId(student.getStudentId())));
        model.addAttribute("studentLogin",student);
        return "showGrade";
    }
}
