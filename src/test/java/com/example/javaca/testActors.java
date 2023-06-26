package com.example.javaca;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.example.javaca.pojo.Course;
import com.example.javaca.pojo.Student;
import com.example.javaca.repository.courseRepository;
import com.example.javaca.repository.studentRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testActors {
    @Autowired
    studentRepository studentRepo;

    @Autowired
    courseRepository courseRepo;

    //Expected success
    @Test
    @Order(1)
    @Rollback(value = false)
    public void testCreateStudent() {
        Student newStudent = new Student();
        newStudent.setEmail("TestEmail@stu.nus.edu");
        newStudent.setPassword("PasswordTest");
        newStudent.setName("Test Name");
        newStudent.setStudentId("S1000002B");
        studentRepo.save(newStudent);
        assertTrue(newStudent.getID() != null);
    }

    //Expected failures
    @Test
    @Order(2)
    @Rollback(value = false)
    public void testCreateCourse() {
        Course newCourse = new Course();
        newCourse.setCourseId("SA4109");
        newCourse.setCousename("CourseTest");
        newCourse.setCompulsory(false);
        newCourse.setCredit(6);
        newCourse.setRoom("Innovation");
        courseRepo.save(newCourse);
        assertTrue(newCourse.getId() == null);
    }


    //Expected success
    public Course GetCourse() {
        Course course = courseRepo.fineCoursebyName("CourseTest");
        return course;
    }

    @Test
    @Order(3)
    public void testGetCourse() {
        Course course = GetCourse();
        assertThat(course.getCousename()).isEqualTo("CourseTest");
    }

    //Expected failures
    public Student GetStudent() {
        Student student = studentRepo.getStudentsByName("Test Name");
        return student;
    }

    @Test
    @Order(4)
    public void testGetStudent() {
        Student student =GetStudent();
        assertThat(student.getID()).isEqualTo(1L);
    }

    //Expected success
    @Test
    @Order(5)
    public void testRetrieveListStudent() {
        List<Student> ListStudents = studentRepo.findAll();
        assertThat(ListStudents.size()).isGreaterThan(0);
    }

    //Expected success
    @Test
    @Order(6)
    @Rollback(value = false)
    public void testUpdateCourse() {
        Course course = courseRepo.fineCoursebyName(GetCourse().getCousename());
        course.setCompulsory(true);
        Course updateCourse = courseRepo.save(course);
        assertThat(updateCourse.getCompulsory()).isEqualTo(true);
    }

    //Expected success
    @Test
    @Order(7)
    @Rollback(value = false)
    @Transactional
    public void testDeleteCourse() {
        courseRepo.deleteCourseByName(GetCourse().getCousename());
        assertNull(courseRepo.fineCoursebyName("CourseTest"));
    }

    //Expected success
    @Test
    @Order(8)
    @Rollback(value = false)
    @Transactional
    public void testDeleteStudent() {
        studentRepo.deleteStudentByName(GetStudent().getName());
        assertNull(courseRepo.fineCoursebyName("Test Name"));
    }

    //Expected failures
    @Test
    @Order(9)
    @Rollback(value = false)
    public void testDeleteCourseWithForeignKeyConstraint() {
        studentRepo.deleteById(1L);
        assertNull(courseRepo.findCoursebyId(1L));
    }
}
