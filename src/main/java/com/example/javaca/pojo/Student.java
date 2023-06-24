package com.example.javaca.pojo;
// This is designed by SA56 Team2

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(name="studentId")
    private String studentId;
    @Column(name = "name")
    private String Name;
    @Column(name="password")
    @NotEmpty
    @Size(min = 4, max = 20)
    private String Password;
    @Column(name="email")
    @NotEmpty
    private String Email;

    @ManyToOne
    private Role role;
    @ManyToOne
    private Collage collage;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name="lecturer_course",
            joinColumns = @JoinColumn(name="studentId",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="courseId",referencedColumnName = "id")
    )
    private List<Course> courseList;

    @OneToMany(mappedBy = "student")
    private List<Grade> grade;
    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollmentList;
}
