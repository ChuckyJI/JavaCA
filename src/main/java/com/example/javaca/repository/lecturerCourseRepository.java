package com.example.javaca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.javaca.pojo.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface lecturerCourseRepository extends JpaRepository<LecturerCourseEntity,Long> {
    @Modifying
    @Query("delete from LecturerCourseEntity l where l.courseId=:courseId")
    int deleteLecturerbyCourseId(@Param("courseId") Long courseId);

    @Modifying
    @Query("delete from LecturerCourseEntity l where l.studentId=:studentId")
    int deleteLecturerbyStudentId(@Param("studentId") Long studentId);

}
