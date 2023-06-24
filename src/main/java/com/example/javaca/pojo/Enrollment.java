package com.example.javaca.pojo;
// This is designed by SA56 Team2

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="enrollment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="isReject")
    private Boolean isReject;
    @Column(name="isEnroll")
    private Boolean isEnroll;
    @Column(name="isFailed")
    private Boolean isFailed;
    @Column(name="isComplete")
    private Boolean isComplete;
    @ManyToOne
    private Student student;
    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;
}
