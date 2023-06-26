package com.example.javaca.controller.admin;
// This is designed by SA56 Team2

import com.example.javaca.dto.CourseDTO;
import com.example.javaca.dto.LecturerDTO;
import com.example.javaca.pojo.Student;
import com.example.javaca.service.Impl.adminService;
import com.example.javaca.service.Impl.lecturerService;
import com.example.javaca.service.Impl.studentService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/lecturer")
public class AdminLecturerController {
    @Resource
    private lecturerService lecturerService;
    @Resource
    private adminService adminService;
    private studentService studentService;

    @PostMapping("")
    public Student addStudent(@RequestBody Student student){
        int rolenumber = adminService.calculateTotalNumber(student.getRole().getId(),student.getCollage().getId());
        Long collegenumber = student.getCollage().getId();
        String prefix = "";
        if(student.getRole().getId() == 1L) prefix="S";
        if(student.getRole().getId() == 2L) prefix="L";
        if(student.getRole().getId() == 3L) prefix="A";
        String studentID = prefix+collegenumber.toString()+String.format("%0" + 6 + "d", rolenumber)+"K";
        student.setStudentId(studentID);
        return lecturerService.insertLecturer(student);
    }

    @DeleteMapping("/{id}")
    public void deleteLecturer(@PathVariable("id") Long id){
        lecturerService.deleteLecturerbyStudentId(id);
        lecturerService.deleteLecturer(id);
    }

    @PutMapping("")
    public Student updateLecturer(@RequestBody Student lecturer){
        if(lecturer.getCollage().getId()!= null){
            return lecturerService.updateLecturer(lecturer);
        }
        else{
            Long collegeid = 1L;
            lecturer.getCollage().setId(collegeid);
            return lecturerService.updateLecturer(lecturer);
        }
    }

    @GetMapping("")
    public List<LecturerDTO> findAll(HttpSession session){
        Student student = (Student) session.getAttribute("sessionid");
        List<LecturerDTO> lecturerDTOS = new ArrayList<>();
        if(student == null) return lecturerDTOS;
        else{
            if(student.getRole().getId()==1L){
                return lecturerDTOS;
            }
            if(student.getRole().getId()==2L){
                return lecturerDTOS;
            }
            else{
                return lecturerService.getAllLecturers();
            }
        }
    }


}
