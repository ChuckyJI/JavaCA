package com.example.javaca.controller.admin;
// This is designed by SA56 Team2

import com.example.javaca.dto.CourseDTO;
import com.example.javaca.dto.LecturerCourseDTO;
import com.example.javaca.pojo.Course;
import com.example.javaca.pojo.Grade;
import com.example.javaca.pojo.LecturerCourseEntity;
import com.example.javaca.pojo.Student;
import com.example.javaca.repository.courseRepository;
import com.example.javaca.repository.studentRepository;
import com.example.javaca.service.Impl.adminService;
import com.example.javaca.service.Impl.courseService;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/course")
public class AdminCourseController {
    @Resource
    private courseService courseService;
    @Resource
    private adminService adminService;
    @Resource
    private courseRepository courseRepository;
    @Resource
    private studentRepository studentRepository;

    @PostMapping("")
    public Course addModule(@RequestBody Course course){
        return courseService.insertCourse(course);
    }

    @DeleteMapping("/{id}")
    public void deleteModule(@PathVariable("id") Long id){
        courseService.deleteCourse(id);
        courseService.deleteLecturerbyCourseId(id);
        courseRepository.deleteById(id);
    }

    @PutMapping("")
    public Course updateModule(@RequestBody Course course){
        if(course.getCollage().getId()!= null){
            return courseService.updateCourse(course);
        }
        else{
            Long collegeid = 1L;
            course.getCollage().setId(collegeid);
            return courseService.updateCourse(course);
        }
    }

    @GetMapping("")
    public List<CourseDTO> findAll(HttpSession session){
        Student student = (Student) session.getAttribute("sessionid");
        List<CourseDTO> courseDTOList = new ArrayList<>();
        if(student == null) return courseDTOList;
        else{
            if(student.getRole().getId()==1L){
                return courseDTOList;
            }
            if(student.getRole().getId()==2L){
                return courseDTOList;
            }
            else{
                return courseService.findAllCourse();
            }
        }
    }

    @PostMapping("/addlec")
    public Course addLecturer(@RequestBody LecturerCourseDTO lecturerCourseDTO){
        List<String> stringList = lecturerCourseDTO.getLecturer();
        for(String studentname : stringList){
            Student student = adminService.getCoursebyStudentId(studentname);
            Course course = adminService.getCoursebyCourseId(lecturerCourseDTO.getCourseId());
            student.getCourseList().add(course);
            course.getStudentList().add(student);
            courseRepository.save(course);
            studentRepository.save(student);
        }
        return null;
    }

}
