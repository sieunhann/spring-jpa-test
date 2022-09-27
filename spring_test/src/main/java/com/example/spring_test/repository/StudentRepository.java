package com.example.spring_test.repository;

import com.example.spring_test.entity.Student;
import com.example.spring_test.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> getByStudentCoursesIn(List<StudentCourse> studentCourses);

    // 14.c
    @Query(nativeQuery = true, value = "SELECT s.id, s.name FROM student s " +
            "INNER JOIN student_course sc ON s.id = sc.student_id " +
            "WHERE sc.course_id = 1 " +
            "AND s.id NOT IN " +
            "(SELECT s.id FROM student s " +
            "INNER JOIN student_course sc ON s.id = sc.student_id " +
            "WHERE sc.course_id = 2);")
    List<Student> getStudentByCourse();
}