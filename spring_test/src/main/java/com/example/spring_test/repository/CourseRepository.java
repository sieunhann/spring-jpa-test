package com.example.spring_test.repository;

import com.example.spring_test.dto.CourseDto;
import com.example.spring_test.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    // 14.a
    @Query(nativeQuery = true, name = "getCourseDto")
    List<CourseDto> getCourseDto();
}