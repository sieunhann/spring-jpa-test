package com.example.spring_test.repository;

import com.example.spring_test.entity.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

    List<Employee> findByEmailAddressContainingAndLastName(String email, String lastName);

    List<Employee> findDistinctByFirstNameOrLastName(String firstName, String lastName);

    List<Employee> findByLastNameOrderByFirstName(String lastName);

    List<Employee> findByFirstNameIgnoreCase(String firstName);

    List<Employee> findAllByFirstName(String firstName, Pageable pageable);

    List<Employee> findAllByFirstName(String firstName, Sort sort);
}