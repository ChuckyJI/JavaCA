package com.example.javaca.dto;
// This is designed by SA56 Team2

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlockDTO {
    private Long Id;
    private Long enrollmentId;
    private boolean isReject;
    private String studentName;
    private String courseName;
}
