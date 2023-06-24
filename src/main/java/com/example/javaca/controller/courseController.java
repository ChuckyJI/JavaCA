package com.example.javaca.controller;
// This is designed by SA56 Team2

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.javaca.pojo.Enrollment;
import com.example.javaca.pojo.Student;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.javaca.service.Impl.*;
import com.example.javaca.repository.*;

import java.io.IOException;
import java.util.*;

import static java.lang.System.out;

@Controller
@RequestMapping("/student")
public class courseController {
    @Autowired
    private courseService courseService;
    private lecturerService lecturerService;
    private enrollmentService enrollmentService;

    public courseController(com.example.javaca.service.Impl.courseService courseService,lecturerService lecturerService,enrollmentService enrollmentService) {
        this.courseService = courseService;
        this.lecturerService = lecturerService;
        this.enrollmentService=enrollmentService;
    }

    @Autowired
    private courseRepository courseRepository;


    @GetMapping("/viewCourse")
    public String getAllCourse(Model model, HttpSession session){
        Student student = (Student) session.getAttribute("sessionid");
        if(student == null) return "redirect:/";
        if(student.getRole().getId()==2L){
            return "redirect:/lecturer/viewCourselec";
        }
        if(student.getRole().getId()==3L){
            return "redirect:http://localhost:3000/admin";
        }
        model.addAttribute("course",courseService.findSpecificAllCourse(student.getStudentId()));
        model.addAttribute("studentLogin",student);
        return "course";
    }

    @GetMapping("/viewCourse/{studentname}/{coursename}")
    public String unenrollCourse(@PathVariable("studentname") String studentname,@PathVariable("coursename") String coursename){
        Enrollment enrollment = lecturerService.updateEnrollmentResult(studentname,coursename);
        enrollment.setIsEnroll(Boolean.FALSE);
        enrollmentService.saveEnrollment(enrollment);
        return "redirect:/student/viewCourse";
    }

    @GetMapping("/courseSchedule")
    public String viewCourseSchedule(Model model,HttpSession session){
        Student student = (Student) session.getAttribute("sessionid");
        if(student == null) return "redirect:/";
        if(student.getRole().getId()==2L){
            return "redirect:/lecturer/viewCourselec";
        }
        if(student.getRole().getId()==3L){
            return "redirect:http://localhost:3000/admin";
        }
        model.addAttribute("freeCourse",courseService.isFreeCourse(student.getStudentId()));
        model.addAttribute("studentLogin",student);
        return "courseSchedule";
    }

    @GetMapping("/getSchedulepdf")
    public String exportPDF(HttpSession session, HttpServletResponse response) throws IOException {

        List<String> rowtitle = CollUtil.newArrayList("*", "Monday", "Tuesday", "Wednesday","Thursday","Friday");
        List<List<String>> result = new ArrayList<>();
        result.add(rowtitle);

        Student student = (Student) session.getAttribute("sessionid");

        LinkedHashMap<String, LinkedHashMap<String,String>> hashMapLinkedHashMap = courseService.isFreeCourse(student.getStudentId());
        for(int i = 8;i<20;i++){
            String mondayString = "";
            String tuesdayString = "";
            String wednesdayString = "";
            String thursdayString = "";
            String fridayString = "";
            String startingtimeOutput = i + ".00~" + (i+1) + ".00";
                for(String time : hashMapLinkedHashMap.keySet()) {
                    String startingtime = time.substring(1);
                    String location = time.substring(0, 1);
                    if(i == Integer.parseInt(time.substring(1))){
                        LinkedHashMap<String, String> innerLinkedMap = hashMapLinkedHashMap.get(time);
                        for (String course : innerLinkedMap.keySet()) {
                            if (location.equals("1") && Objects.equals(innerLinkedMap.get(course), "1")) {
                                mondayString = course;
                                break;
                            }
                            if (location.equals("2") && Objects.equals(innerLinkedMap.get(course), "1")) { tuesdayString = course;break;}
                            if (location.equals("3") && Objects.equals(innerLinkedMap.get(course), "1")) { wednesdayString = course;break;}
                            if (location.equals("4") && Objects.equals(innerLinkedMap.get(course), "1")) { thursdayString = course;break;}
                            if (location.equals("5") && Objects.equals(innerLinkedMap.get(course), "1")) { fridayString = course;break;}
                        }
                    }
                }
            result.add(CollUtil.newArrayList(startingtimeOutput,mondayString,tuesdayString,wednesdayString,thursdayString,fridayString));
        }

        ExcelWriter writer = ExcelUtil.getWriter();

        writer.merge(5,"Course Schedule");
        writer.write(result, true);

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=Course_Schedule.xls");
        ServletOutputStream out=response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(out);

        return "courseSchedule";
    }
}
