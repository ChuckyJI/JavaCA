package com.example.javaca.pojo;
// This is designed by SA56 Team2

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="courseId")
    private String courseId;
    @Column(name = "coursename")
    private String cousename;
    @Column(name = "credit")
    private Integer credit;
    @Column(name = "size")
    private Integer size;
    @Column(name="room")
    private String room;
    @Column(name="compulsory")
    private Boolean compulsory;
    @ManyToOne(fetch = FetchType.EAGER)
    private Collage collage;
    @Column(name="startingtime")
    private LocalTime startingtime;
    @Column(name="endingtime")
    private LocalTime endingtime;
    @Column(name="date")
    private String date;
    @ManyToMany(mappedBy = "courseList")
    private List<Student> studentList;
    @OneToMany(mappedBy = "course")
    private List<Grade> gradeList;
    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollmentList;

    public Course(String courseId, String cousename, Integer credit, Integer size, String room, Boolean compulsory, Collage collage, LocalTime startingtime, LocalTime endingtime, String date, List<Student> studentList, List<Grade> gradeList, List<Enrollment> enrollmentList) {
        this.courseId = courseId;
        this.cousename = cousename;
        this.credit = credit;
        this.size = size;
        this.room = room;
        this.compulsory = compulsory;
        this.collage = collage;
        this.startingtime = startingtime;
        this.endingtime = endingtime;
        this.date = date;
        this.studentList = studentList;
        this.gradeList = gradeList;
        this.enrollmentList = enrollmentList;
    }
}
