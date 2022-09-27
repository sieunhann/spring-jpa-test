
package com.example.spring_test;

import com.example.spring_test.entity.Employee;
import com.example.spring_test.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class InitEmployeeTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void create_employee() {
        Employee employee = Employee.builder()
                .emailAddress("test7@gmail.com")
                .firstName("Hoang")
                .lastName("Long")
                .build();
        Employee employee2 = Employee.builder()
                .emailAddress("test8@gmail.com")
                .firstName("Minh")
                .lastName("Chien")
                .build();
        Employee employee3 = Employee.builder()
                .emailAddress("test9@gmail.com")
                .firstName("Ngoc")
                .lastName("Anh")
                .build();
        Employee employee4 = Employee.builder()
                .emailAddress("test10@gmail.com")
                .firstName("Van")
                .lastName("Chien")
                .build();
        employeeRepository.save(employee);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);
    }

    @Test
    void findByEmailAddressAndLastName(){
        List<Employee> employees = employeeRepository.findByEmailAddressContainingAndLastName("test", "Cuong");
        employees.forEach(System.out::println);
    }

    @Test
    void findDistinctByFirstNameOrLastName(){
        List<Employee> employees = employeeRepository.findDistinctByFirstNameOrLastName("Manh", "Cuong");
        employees.forEach(System.out::println);
    }

    @Test
    void findByLastNameOrderByFirstName(){
        List<Employee> employees = employeeRepository.findByLastNameOrderByFirstName("Long");
        employees.forEach(System.out::println);
    }

    @Test
    void findByFirstNameIgnoreCase(){
        List<Employee> employees = employeeRepository.findByFirstNameIgnoreCase("mAnh");
        employees.forEach(System.out::println);
    }
}
