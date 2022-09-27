package com.example.spring_test.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="post")
public class Post {
    // custom ID
    @Id
    @GeneratedValue(generator = "prod-generator")

    @GenericGenerator(name = "prod-generator",

            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "post"),

            strategy = "com.example.spring_test.model.CustomGenerateId")
    private String id;
    private String title;

    // Sử dụng UUID
    // @Id
    // @GeneratedValue
    // private UUID id;
}
