package com.example.javaca.repository;
// This is designed by SA56 Team2

import com.example.javaca.pojo.Collage;
import com.example.javaca.pojo.Course;
import com.example.javaca.pojo.Enrollment;
import com.example.javaca.pojo.Student;
import org.apache.ibatis.annotations.Insert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface adminRepository extends JpaRepository<Student,Long> {
    @Query("select e.student,e.course from Student s, Enrollment e,Course c where e.isReject=True")
    List<Object[]> listStudentIsReject();

    @Query("select e.course.id, e.isReject, e.student.Name, e.course.cousename , e.id from Enrollment e where e.isReject=True")
    List<Object[]> listBlockedStudent();

    @Modifying
    @Query("UPDATE Enrollment e SET e.isReject = false WHERE e.id = :enrollmentId")
    void updateIsRejectToTrue(Long enrollmentId);

    @Query("select s from Student s where s.role.id=:rolenumnber and s.collage.id = :collegeid")
    List<Student> calculateTotalNumber(@Param("rolenumnber") Long rolenunmber, @Param("collegeid") Long collegeid);

    @Query("select s from Student s where s.Name=:studentname")
    Student getStudentbyName(@Param("studentname") String studentname);

    @Query("select c from Course c where c.id=:id")
    Course getCoursebyName(@Param("id") Long courseid);
}