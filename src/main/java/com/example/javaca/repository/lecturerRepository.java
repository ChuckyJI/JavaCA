package com.example.javaca.repository;
// This is designed by SA56 Team2

import com.example.javaca.pojo.Course;
import com.example.javaca.pojo.Enrollment;
import com.example.javaca.pojo.Grade;
import com.example.javaca.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface lecturerRepository extends JpaRepository<Grade,Long> {
    @Query("select s.courseList from Student s where s.studentId= :lecturerid")
    List<Course> listAllCourseByLecturer(@Param("lecturerid") String lecturerid);
    @Query("select e.student from Enrollment e where e.course.cousename = :coursename")
    List<Student> listAllStudentByCourseByLecturer(@Param("coursename") String coursename);
    @Query("select e.student from Enrollment e where e.course.cousename = :coursename and e.isEnroll=true and e.isComplete=false")
    List<Student> listAllStudentByCourseByLecturerForStudent(@Param("coursename") String coursename);
    @Query("select c from Course c where c.cousename=:coursename")
    Course returnCourse(@Param("coursename") String coursename);
    @Query("select s from Student s where s.Name=:studentname")
    Student returnStudent(@Param("studentname") String studentname);

    @Query("select g from Grade g where g.course.cousename=:coursename and g.student.Name=:studentname")
    Grade courseMarkEntityByStudent(@Param("studentname") String studentname,@Param("coursename") String coursename);
    @Query("select e from Enrollment e where e.student.Name = :studentname and e.course.cousename=:coursename")
    Enrollment updateEnrollmentResult(@Param("studentname") String studentname, @Param("coursename") String coursename);

    @Query("SELECT s.ID,s.Password,s.Name,s.Email,s.studentId ,s.collage.name,s.collage.id FROM Student s where s.role.id=2")
    List<Object[]> findAllLecturerDetails();
}
