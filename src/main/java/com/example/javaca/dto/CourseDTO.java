package com.example.javaca.dto;
// This is designed by SA56 Team2

import com.example.javaca.pojo.Collage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Long id;
    private String courseId;
    private String cousename;
    private Integer credit;
    private Integer size;
    private String room;
    private Boolean compulsory;
    private Collage collage;
    private LocalTime startingtime;
    private LocalTime endingtime;
    private String date;
}

