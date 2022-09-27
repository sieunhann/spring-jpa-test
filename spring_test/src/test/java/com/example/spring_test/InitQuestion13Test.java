package com.example.spring_test;

import com.example.spring_test.dto.CourseDto;
import com.example.spring_test.entity.Course;
import com.example.spring_test.entity.Student;
import com.example.spring_test.entity.StudentCourse;
import com.example.spring_test.repository.CourseRepository;
import com.example.spring_test.repository.StudentCourseRepository;
import com.example.spring_test.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class InitQuestion13Test {

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void save_student_course(){
        StudentCourse studentCourse = StudentCourse.builder()
                .score(11).build();
        studentCourseRepository.save(studentCourse);
    }

    // 14a
    @Test
    void get_map_by_course(){
        List<CourseDto> courses = courseRepository.getCourseDto();
        Map<String, List<Student>> myMap = new HashMap<>();
        courses.forEach(course -> {
            myMap.put(course.getCourseName(), course.getStudentList());
        });
        for(Map.Entry<String, List<Student>> entry : myMap.entrySet()){
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    // 14b
    @Test
    void get_avg_score_by_course(){
        System.out.println(studentCourseRepository.getAvgScoreByCourse(2L));
    }

    // 14c
    @Test
    void get_student_by_course(){
        List<Student> students = studentRepository.getStudentByCourse();
        students.forEach(System.out::println);
    }
}
