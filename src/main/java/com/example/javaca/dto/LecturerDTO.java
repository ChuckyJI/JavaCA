package com.example.javaca.dto;
// This is designed by SA56 Team2

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LecturerDTO {
        private Long id;
        private String name;
        private String email;
        private String studentId;
        private String college_name;
        private String password;
        private Long college_id;
}

