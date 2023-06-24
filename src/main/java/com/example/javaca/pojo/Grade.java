package com.example.javaca.pojo;
// This is designed by SA56 Team2

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Dictionary;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="Grade")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="coursemark")
    private Double coursemark;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;
}
