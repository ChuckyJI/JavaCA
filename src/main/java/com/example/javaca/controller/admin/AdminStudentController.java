package com.example.javaca.controller.admin;
// This is designed by SA56 Team2

import com.example.javaca.dto.LecturerDTO;
import com.example.javaca.dto.StudentDTO;
import com.example.javaca.pojo.Student;
import com.example.javaca.service.Impl.adminService;
import com.example.javaca.service.Impl.courseService;
import com.example.javaca.service.Impl.studentService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/student")
public class AdminStudentController {
    @Resource
    private studentService studentService;
    @Resource
    private adminService adminService;

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
        return studentService.insertStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") Long id){
        studentService.deleteEnrollmentbyStudentId(id);
        studentService.deleteGradebyStudentId(id);
        studentService.deleteStudent(id);
    }

    @PutMapping("")
    public Student updateStudent(@RequestBody Student student){
        if(student.getCollage().getId()!= null){
            return studentService.updateStudent(student);
        }
        else{
            Long collegeid = 1L;
            student.getCollage().setId(collegeid);
            return studentService.updateStudent(student);
        }
    }

    @GetMapping("")
    public List<StudentDTO> findAll(HttpSession session){
        Student student = (Student) session.getAttribute("sessionid");
        List<StudentDTO> studentDTOS = new ArrayList<>();
        if(student == null) return studentDTOS;
        else{
            if(student.getRole().getId()==1L){
                return studentDTOS;
            }
            if(student.getRole().getId()==2L){
                return studentDTOS;
            }
            else{
                return studentService.getAllStudents();
            }
        }
    }
}
