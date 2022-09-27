package com.example.spring_test.entity;

import com.example.spring_test.dto.CourseDto;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

//SELECT c.name, json_arrayagg(json_object("id", s.id, "name", s.name))
//AS student_info
//FROM course c
//INNER JOIN student_course sc ON c.id = sc.course_id
//INNER JOIN student s ON s.id = sc.student_id
//GROUP BY c.id

@SqlResultSetMapping(
        name = "CourseDto",
        classes = @ConstructorResult(
                targetClass = CourseDto.class,
                columns = {
                        @ColumnResult(name = "course_name", type = String.class),
                        @ColumnResult(name = "student_list", type = String.class)
                }
        )
)
@NamedNativeQuery(
        name = "getCourseDto",
        query = " SELECT c.name as course_name, json_arrayagg(json_object(\"id\", s.id, \"name\", s.name)) as student_list\n" +
                "FROM course c\n" +
                "INNER JOIN student_course sc ON c.id = sc.course_id\n" +
                "INNER JOIN student s ON s.id = sc.student_id\n" +
                "GROUP BY c.id",
        resultSetMapping = "CourseDto"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentCourse> studentCourses = new LinkedHashSet<>();

}