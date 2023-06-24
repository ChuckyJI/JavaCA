package com.example.javaca.service.Impl;
// This is designed by SA56 Team2

import com.example.javaca.pojo.Course;
import org.springframework.stereotype.Service;
import com.example.javaca.repository.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class gradeServiceImpl implements gradeService{
    private gradeRepository gradeRepository;

    public gradeServiceImpl(com.example.javaca.repository.gradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public LinkedHashMap<Course,Double> getGradeByStudentId(String id) {
        List<Object[]> gradeList = gradeRepository.getGradeByStudentId(id);
        LinkedHashMap<Course,Double> gradeMap = new LinkedHashMap<>();

        for(Object[] grade : gradeList){
            Course course = (Course) grade[0];
            Double mark = (Double)grade[1];
            gradeMap.put(course,mark);
        }
        return gradeMap;
    }

    @Override
    public Double calaulateAvgGPA(Map<Course, Double> courseDoubleMap) {
        Double count = 0.;
        Double sum = 0.;
        for(Map.Entry<Course, Double> data:courseDoubleMap.entrySet()){
            Integer credit = data.getKey().getCredit();
            Double mark = data.getValue();
            Double gpa = transformGPA(mark);
            count += credit;
            sum += gpa*credit;
        }

        Double result = sum/count;
        return result;
    }

    public Double transformGPA(Double mark){
        Double gpa = 0.;
        if(mark>100) gpa = 1.5;
        if(mark>=85 && mark<=100) gpa = 5.0;
        if(mark>=80 && mark<85) gpa = 5.0;
        if(mark>=75 && mark<80) gpa = 4.5;
        if(mark>=70 && mark<75) gpa = 4.0;
        if(mark>=65 && mark<70) gpa = 3.5;
        if(mark>=60 && mark<65) gpa = 3.0;
        if(mark>=55 && mark<60) gpa = 2.5;
        if(mark>=50 && mark<55) gpa = 2.0;
        if(mark>=45 && mark<50) gpa = 1.5;
        if(mark>=40 && mark<45) gpa = 1.0;
        if(mark<40) gpa = 0.0;
        return gpa;
    }
}
