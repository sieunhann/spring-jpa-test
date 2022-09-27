package com.example.spring_test.repository;

import com.example.spring_test.dto.UserDto;
import com.example.spring_test.dto.UserInfo;
import com.example.spring_test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);

    // Native Query
    @Query(nativeQuery = true, name = "getUserDto")
    UserDto getUserDto(Long id);

    // JPQL
    @Query("select new com.example.spring_test.dto.UserDto(u.id, u.name, u.email) from User u where u.id = ?1")
    UserDto getUserDtoByJPQL(Long id);

    // Projection
    UserInfo getUserInfoById(Long id);
}