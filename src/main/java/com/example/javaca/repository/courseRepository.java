package com.example.javaca.repository;
// This is designed by SA56 Team2

import com.example.javaca.pojo.Course;
import com.example.javaca.pojo.Grade;
import com.example.javaca.pojo.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface courseRepository extends JpaRepository<Course,Long> {
    @Query("select e.course,e.isComplete,e.isFailed,e.isReject from Enrollment e where e.student.studentId = :studentid and e.isEnroll=true")
    List<Object[]> findSpecificAllCourse(@Param("studentid") String studentid);

    @Query("SELECT c.id, c.courseId, c.cousename, c.credit, c.size, c.room, c.compulsory, c.collage.id, c.collage.name, c.startingtime, c.endingtime, c.date FROM Course c")
    List<Object[]> findAllCourseDetails();

    @Modifying
    @Query("delete from Grade g where g.course.id = :courseId")
    int deleteGradebyCourseId(@Param("courseId") Long courseId);

    @Modifying
    @Query("delete from Enrollment e where e.course.id = :courseId")
    int deleteEnrollmentbyCourseId(@Param("courseId") Long courseId);

    @Query("select c from Course c where c.id=:id")
    Course findCoursebyId(@Param("id") Long id);

}
