package com.example.javaca.controller;
// This is designed by SA56 Team2

import com.example.javaca.pojo.Course;
import com.example.javaca.pojo.Enrollment;
import com.example.javaca.pojo.Student;
import com.example.javaca.repository.enrollmentRepository;
import com.example.javaca.service.Impl.enrollmentService;
import com.example.javaca.service.Impl.lecturerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.javaca.repository.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.mysql.cj.MysqlType.JSON;

@Controller
@RequestMapping("/student")
public class enrollmentController {
    @Autowired
    private enrollmentService enrollmentService;
    private lecturerService lecturerService;

    public enrollmentController(enrollmentService enrollmentService,lecturerService lecturerService) {
        this.enrollmentService = enrollmentService;
        this.lecturerService=lecturerService;
    }

    @Autowired
    private enrollmentRepository enrollmentRepository;

    @GetMapping("/enrollCourse")
    public String enrollCourse(Model model,HttpSession session){
        Student student = (Student) session.getAttribute("sessionid");
        if(student == null) return "redirect:/";
        if(student.getRole().getId()==2L){
            return "redirect:/lecturer/viewCourselec";
        }
        if(student.getRole().getId()==3L){
            return "redirect:http://localhost:3000/admin";
        }
        return findPaginated(1, "cousename", "asc", model, session);
    }

    @GetMapping("/enrollCourse/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model,
                                HttpSession session) {

        int pageSize = 5;

        Student student = (Student) session.getAttribute("sessionid");
        if (student == null) return "redirect:/";
        if (student.getRole().getId() == 2L) {
            return "redirect:/lecturer/viewCourselec";
        }
        if(student.getRole().getId()==3L){
            return "redirect:http://localhost:3000/admin";
        }

        List<Course> availableCourses = enrollmentService.enrollAllAvailableCourse(student.getStudentId());

        Page<Course> page = enrollmentService.createPage(availableCourses, pageNo, pageSize, sortField, sortDir);
        List<Course> listCourse1 = page.getContent();

        model.addAttribute("listcourse1", listCourse1);
        model.addAttribute("course", enrollmentService.enrollAllAvailableCourse(student.getStudentId()));
        model.addAttribute("enrollNumber", enrollmentService.totalNumForOneCourse());
        model.addAttribute("enrolledCourse", enrollmentService.conflictCourse(student.getStudentId()));
        model.addAttribute("courseLecturer", enrollmentService.getLecturerByCourse(student.getStudentId()));
        model.addAttribute("studentLogin", student);

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverserSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "enrollment";
    }

    @GetMapping("/enrollment/{id}")
    public String comfirmEnrollcourse(@PathVariable Long id, Model model, HttpSession session){
        Student student = (Student) session.getAttribute("sessionid");
        if(student == null) return "redirect:/";
        if(student.getRole().getId()==2L){
            return "redirect:/lecturer/viewCourselec";
        }
        if(student.getRole().getId()==3L){
            return "redirect:http://localhost:3000/admin";
        }
        model.addAttribute("course",enrollmentService.getCourseById(id));
        model.addAttribute("courseLecturer",enrollmentService.getLecturerByCourse(student.getStudentId()));
        model.addAttribute("studentLogin",student);
        return "comfirmEnrollment";
    }

    @PostMapping (value ="/confirmation")
    public String enrollCourseB(@ModelAttribute("course") Course course,HttpSession session,Model model){
        Student student = (Student) session.getAttribute("sessionid");
        if(student == null) return "redirect:/";
        if(student.getRole().getId()==2L){
            return "redirect:/lecturer/viewCourselec";
        }
        if(student.getRole().getId()==3L){
            return "redirect:http://localhost:3000/admin";
        }
        Enrollment enrollNewCourse = lecturerService.updateEnrollmentResult(student.getName(),course.getCousename());
        if(enrollNewCourse == null){
            Enrollment enrollment = new Enrollment();
            enrollment.setIsEnroll(true);
            enrollment.setIsFailed(false);
            enrollment.setIsReject(false);
            enrollment.setIsComplete(false);
            enrollment.setCourse(course);
            enrollment.setStudent(enrollmentService.getCurrentStudent(student.getStudentId()));
            enrollmentService.saveEnrollment(enrollment);
        }
        else{
            enrollNewCourse.setIsEnroll(true);
            enrollmentService.updateEnrollment(enrollNewCourse);
        }
        enrollmentService.sendEmail("javacamailsender@gmail.com", student.getEmail(),"NUS CAPS Notice [Enroll]", "Enroll Successfully!"+" You have enrolled the "+course.getCousename());
        model.addAttribute("studentLogin",student);
        return "redirect:/student/enrollCourse";
    }

    @GetMapping("/enrollCourseSearch")
    public String searchLink(@RequestParam("contentInput") String name , Model model,HttpSession session){
        Student student = (Student) session.getAttribute("sessionid");
        if(student == null) return "redirect:/";
        if(student.getRole().getId()==2L){
            return "redirect:/lecturer/viewCourselec";
        }
        if(student.getRole().getId()==3L){
            return "redirect:http://localhost:3000/admin";
        }
        List<Course> courseList = enrollmentService.enrollAllAvailableCourse(student.getStudentId());
        List<Course> course = enrollmentService.searchByContent(name,student.getStudentId());
        Integer count = 0;
        if(name == "") count = -1;
        else{
            for(Course data: course){
                if(courseList.contains(data)) count++;
            }
        }
        if(count>=0){
            model.addAttribute("course", enrollmentService.searchByContent(name,student.getStudentId()));
            model.addAttribute("enrollNumber",enrollmentService.totalNumForOneCourse());
            model.addAttribute("courseLecturer",enrollmentService.getLecturerByCourse(student.getStudentId()));
            model.addAttribute("enrolledCourse",enrollmentService.conflictCourse(student.getStudentId()));
            model.addAttribute("studentLogin",student);
        }
        else if (count== -1) {
            model.addAttribute("course",enrollmentService.enrollAllAvailableCourse(student.getStudentId()));
            model.addAttribute("enrollNumber",enrollmentService.totalNumForOneCourse());
            model.addAttribute("courseLecturer",enrollmentService.getLecturerByCourse(student.getStudentId()));
            model.addAttribute("enrolledCourse",enrollmentService.conflictCourse(student.getStudentId()));
            model.addAttribute("studentLogin",student);
        }
        else{
        }
        return "searchPage";
    }

}
