package com.example.javaca.controller.admin;
// This is designed by SA56 Team2

import com.example.javaca.dto.StudentDTO;
import com.example.javaca.pojo.Student;
import com.example.javaca.service.Impl.adminService;
import com.example.javaca.service.Impl.studentService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/student")
public class AdminStudentController {
    @Resource
    private studentService studentService;
    @Resource
    private adminService adminService;

    @PostMapping("")
    public Student addStudent(@RequestBody Student student, HttpSession session){
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
        studentService.deleteStudent(id);
    }

    @PutMapping("")
    public Student updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @GetMapping("")
    public List<StudentDTO> findAll(){
        return studentService.getAllStudents();
    }
}
