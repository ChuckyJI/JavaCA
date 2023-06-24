package com.example.javaca.repository;
// This is designed by SA56 Team2

import com.example.javaca.pojo.Course;
import com.example.javaca.pojo.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface gradeRepository extends JpaRepository<Grade,Long> {
    @Query("select g.course,g.coursemark from Grade g where g.student.studentId=:studentId")
    List<Object[]> getGradeByStudentId(@Param("studentId") String id);
}
