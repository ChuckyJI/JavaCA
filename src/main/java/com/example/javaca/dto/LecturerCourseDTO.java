package com.example.javaca.dto;
// This is designed by SA56 Team2

import com.example.javaca.pojo.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LecturerCourseDTO {
    private List<String> lecturer;
    private Long courseId;
}
