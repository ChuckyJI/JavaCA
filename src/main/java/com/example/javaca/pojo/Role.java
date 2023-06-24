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
@Table(name="role")
public class Role {
    //roles = 0 ,student; roles = 1 ,lecturer; roles = 2 administrator
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "role")
    private List<Student> student;
}
