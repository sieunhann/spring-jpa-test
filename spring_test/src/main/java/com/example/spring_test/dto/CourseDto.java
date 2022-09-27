package com.example.spring_test.dto;

import com.example.spring_test.entity.Student;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.io.IOException;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {
    private String courseName;
    private List<Student> studentList;

    public CourseDto(String courseName, String studentList) {
        this.courseName = courseName;
        if(studentList != null){
            ObjectMapper mapper = new ObjectMapper();
            try {
                this.studentList = mapper.readValue(studentList, new TypeReference<List<Student>>() {
                });
            } catch (IOException e){
                this.studentList = null;
            }
        }
    }
}
