package com.example.javaca.service.Impl;
// This is designed by SA56 Team2

import com.example.javaca.dto.CourseDTO;
import com.example.javaca.pojo.Collage;
import com.example.javaca.pojo.Course;
import org.springframework.stereotype.Service;
import com.example.javaca.repository.*;

import java.time.LocalTime;
import java.util.*;

@Service
public class courseServiceImpl implements courseService{
    private courseRepository courseRepository;

    public courseServiceImpl(courseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public LinkedHashMap<Course,String> findSpecificAllCourse(String id) {
        List<Object[]> releaseCourse = courseRepository.findSpecificAllCourse(id);
        LinkedHashMap<Course,String> courseMap = new LinkedHashMap<>();

        for(Object[] data : releaseCourse){
            Course course = (Course) data[0];
            Boolean isComplete = (Boolean) data[1];
            Boolean isFailed = (Boolean) data[2];
            Boolean isReject = (Boolean)data[3];
            String status = "";
            if (!isComplete && !isFailed && !isReject) status = "00";
            if (isComplete && !isFailed && !isReject) status = "10";
            if (!isComplete && isFailed && !isReject) status = "01";
            if (isComplete && isFailed && !isReject) status = "11";
            if(isReject) status ="99";
            courseMap.put(course,status);
        }
        return courseMap;
    }

    @Override
    public LinkedHashMap<String,LinkedHashMap<String,String>> isFreeCourse(String id) {
        LinkedHashMap<Course,String> courseStringMap = findSpecificAllCourse(id);
        LinkedHashMap<String,LinkedHashMap<String,String>> courseSchedule = new LinkedHashMap<>();
        String result = "";
        String tempstring = "";
        int temp = 0;
        String mapKey = "";

        for(int j = 0; j<5;j++){
            for(int i = 8;i<20;i++){
                for(Map.Entry<Course,String> map:courseStringMap.entrySet()){
                    if(map.getKey().getDate().charAt(j) == '1' && (Objects.equals(map.getValue(), "00") || Objects.equals(map.getValue(), "01"))){
                        if(map.getKey().getStartingtime().getHour()<=i && map.getKey().getEndingtime().getHour()>=i+1){
                            temp++;
                            tempstring = map.getKey().getCousename();
                            break;
                        }
                        else{
                            tempstring = i+":00~"+(i+1)+":00";
                        }
                    }
                }
                if(temp!=0) result += "1";
                else result += "0";
                mapKey = Integer.toString(j+1)+Integer.toString(i);
                courseSchedule.put(mapKey,new LinkedHashMap<>());
                courseSchedule.get(mapKey).put(tempstring,result);
                temp =0;
                tempstring="";
                mapKey="";
                result="";
            }
        }
        return courseSchedule;
    }

    @Override
    public List<CourseDTO> findAllCourse() {
        List<Object[]> results = courseRepository.findAllCourseDetails();
        List<CourseDTO> dtos = new ArrayList<>();

        for (Object[] result : results) {
            CourseDTO dto = new CourseDTO();
            dto.setId((Long) result[0]);
            dto.setCourseId((String) result[1]);
            dto.setCousename((String) result[2]);
            dto.setCredit((Integer) result[3]);
            dto.setSize((Integer) result[4]);
            dto.setRoom((String) result[5]);
            dto.setCompulsory((Boolean) result[6]);

            Collage collage = new Collage();
            collage.setId((Long) result[7]);
            collage.setName((String) result[8]);
            dto.setCollage(collage);

            dto.setStartingtime((LocalTime) result[9]);
            dto.setEndingtime((LocalTime) result[10]);
            dto.setDate((String) result[11]);

            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    public Course insertCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Optional<Course> findCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return course;
    }
}
