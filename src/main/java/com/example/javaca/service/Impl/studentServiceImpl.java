package com.example.javaca.service.Impl;
// This is designed by SA56 Team2

import com.example.javaca.dto.StudentDTO;
import com.example.javaca.pojo.Student;
import com.example.javaca.repository.studentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class studentServiceImpl implements studentService{
    private studentRepository studentRepository;

    public studentServiceImpl(com.example.javaca.repository.studentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student getStudentbyEmailAndPwd(String email, String pwd) {
        return studentRepository.getStudentbyEmailAndPwd(email,pwd);
    }
    @Override
    public List<StudentDTO> getAllStudents() {
        List<Object[]> studentDetails = studentRepository.findAllStudentDetails();
        List<StudentDTO> students = new ArrayList<>();

        for (Object[] student : studentDetails) {
            Long id = (Long) student[0];
            String password = (String) student[1];
            String name = (String) student[2];
            String email = (String) student[3];
            String studentId= (String) student[4];
            String college_name = (String)  student[5];
            Long college_id = (Long) student[6];

            StudentDTO studentDTO = new StudentDTO(id, name, email, studentId, college_name, password,college_id);
            students.add(studentDTO);
        }

        return students;
    }

    @Override
    public Student insertStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }
}
