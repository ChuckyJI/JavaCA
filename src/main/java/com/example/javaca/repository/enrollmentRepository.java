package com.example.javaca.repository;
// This is designed by SA56 Team2

import com.example.javaca.pojo.Course;
import com.example.javaca.pojo.Enrollment;
import com.example.javaca.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;

@Repository
public interface enrollmentRepository extends JpaRepository<Enrollment, Long> {
    @Query("select c from Course c, Enrollment e where c.courseId not in (select e.course.courseId from Enrollment e where (e.isEnroll=true or e.isReject=true) and e.student.studentId = :studentid)")
    List<Course> enrollAllAvailableCourse(@Param("studentid") String studentid);
    @Query("select c from Course c where c.id=:courseid")
    Course getCourseById(@Param("courseid") Long id);
    @Query("select s from Student s where s.studentId=:studentid")
    Student getCurrentStudent(@Param("studentid") String id);
    @Query("select e.course.id, count(e.course.id) from Enrollment e where e.isEnroll = true group by e.course")
    List<Object[]> totalNumForOneCourse();

    @Query("select e.course from Enrollment e where e.student.studentId = :studentid and e.isEnroll=true and e.isComplete=false")
    List<Course> findEnrolledCourse(@Param("studentid") String studentid);

    @Query("select c from Course c where (c.cousename like CONCAT('%', :coursename, '%') or c.courseId like CONCAT('%', :coursename, '%') or c.room like CONCAT('%', :coursename, '%')) and c.courseId not in (select e.course.courseId from Enrollment e where (e.isEnroll=true or e.isReject=true) and e.student.studentId = :studentid)")
    List<Course> searchByContent(@Param("coursename") String string,@Param("studentid") String id);
    @Query("select c , c.studentList from Course c where c.courseId not in (select e.course.courseId from Enrollment e where (e.isEnroll=true or e.isReject=true) and e.student.studentId = :studentid)")
    List<Object[]> getLecturerByCourse(@Param("studentid") String studentid);

    @Query("select e.course,e.isComplete,e.isFailed from Enrollment e where e.student.studentId = :studentid and e.isEnroll=true")
    List<Object[]> findSpecificAllCourseEN(@Param("studentid") String studentid);
}
