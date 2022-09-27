package com.example.spring_test.repository;

import com.example.spring_test.entity.Course;
import com.example.spring_test.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
    List<StudentCourse> getAllByCourse(Course course);

    // 14.b
    @Query(nativeQuery = true, value = "SELECT AVG(sc.score) FROM student_course sc WHERE sc.course_id = ?")
    Double getAvgScoreByCourse(Long id);
}