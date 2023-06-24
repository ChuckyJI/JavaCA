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
}
