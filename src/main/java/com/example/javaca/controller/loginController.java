package com.example.javaca.controller;
// This is designed by SA56 Team2


import com.example.javaca.pojo.Student;
import com.example.javaca.repository.studentRepository;
import com.example.javaca.service.Impl.studentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class loginController {
    @Autowired
    private studentService studentService;

    public loginController(com.example.javaca.service.Impl.studentService studentService) {
        this.studentService = studentService;
    }

    @Autowired
    private studentRepository studentRepository;


    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model, HttpSession session){
        if(session.getAttribute("studentid") == null){
            model.addAttribute("student",new Student());
            model.addAttribute("message","");
        }
        else{
            Student student = (Student) session.getAttribute("studentid");
            if(student.getRole().getId() == 1L) return "redirect:/student/viewCourse";
            if(student.getRole().getId() == 2L) return "redirect:/lecturer/viewCourselec";
            if(student.getRole().getId() == 3L) return "redirect:/admin/doubleCheck";
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginStudent(@ModelAttribute("student") Student student, Model model, HttpSession session, BindingResult bindingResult){
        Student getStudent = studentService.getStudentbyEmailAndPwd(student.getEmail(),student.getPassword());
        if(getStudent != null){
            session.setAttribute("sessionid",getStudent);
            Long id = getStudent.getRole().getId();
            if(id == 1L){
                return "redirect:/student/viewCourse";
            }
            if(id == 2L){
                return "redirect:/lecturer/viewCourselec";
            }
            if(id == 3L){
                return "redirect:/admin/doubleCheck";
            }

        }
        else{
            model.addAttribute("message","Incorrect Email or Password");
            return "login";
        }
        return null;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
