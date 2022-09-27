package com.example.spring_test.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name= "emailAddress", nullable = false, unique = true)
    private String emailAddress;

    @Column(name= "firstName", nullable = false)
    private String firstName;

    @Column(name= "lastName", nullable = false)
    private String lastName;

    @Override
    public String toString() {
        return this.id + " - " +
                this.emailAddress + " - " +
                this.firstName + " - " +
                this.lastName;
    }
}