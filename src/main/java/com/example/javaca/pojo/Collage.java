package com.example.javaca.pojo;
// This is designed by SA56 Team2


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="collage")
public class Collage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "collage")
    private List<Student> studentList;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "collage")
    private List<Course> courseList;
}
