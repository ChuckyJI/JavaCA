package com.example.javaca.controller.admin;
// This is designed by SA56 Team2
import com.example.javaca.dto.BlockDTO;
import com.example.javaca.dto.LecturerCourseDTO;
import com.example.javaca.dto.StudentDTO;
import com.example.javaca.pojo.Course;
import com.example.javaca.pojo.Student;
import com.example.javaca.service.Impl.adminService;
import com.example.javaca.service.Impl.enrollmentService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/block")
public class AdminBlockController {

    @Resource
    private adminService adminService;
    @Resource
    private enrollmentService enrollmentService;


    @GetMapping("")
    public List<BlockDTO> getRejectedStudents(HttpSession session) {
        Student student = (Student) session.getAttribute("sessionid");
        List<BlockDTO> blockDTOList = new ArrayList<>();
        if(student == null) return blockDTOList;
        else{
            if(student.getRole().getId()==1L){
                return blockDTOList;
            }
            if(student.getRole().getId()==2L){
                return blockDTOList;
            }
            else{
                return adminService.getRejectedStudents();
            }
        }
    }

    @PutMapping("/{id}")
    public void rejectBlock(@PathVariable("id") Long id){
        adminService.updateIsRejectToTrue(id);
    }

    @PutMapping("/sendemail")
    public void sendEmail(@RequestBody LecturerCourseDTO lecturerCourseDTO){
        List<String> stringList = lecturerCourseDTO.getLecturer();
        for(String studentname : stringList) {
            Student student = adminService.getCoursebyStudentId(studentname);
            Course course = adminService.getCoursebyCourseId(lecturerCourseDTO.getCourseId());
            enrollmentService.sendEmail("javacamailsender@gmail.com", student.getEmail(), "NUS CAPS Notice [UnEnroll]", "Dear Student, \u200B\n" +
                    "\n" +
                    "Your enrollment to the course " + "\"" + course.getCousename() + "\"" + " has been rejected. \u200B\n" +
                    "\n" +
                    "Please try other courses.");
        }
    }
}
