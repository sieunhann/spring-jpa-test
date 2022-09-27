package com.example.spring_test;

import com.example.spring_test.dto.UserDto;
import com.example.spring_test.dto.UserInfo;
import com.example.spring_test.entity.User;
import com.example.spring_test.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class InitUserTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void save_user(){
        User user = User.builder()
                .name("Cuong")
                .email("cuong@gmail.com")
                .password("111")
                .build();
        User user2 = User.builder()
                .name("Mai")
                .email("mai@gmail.com")
                .password("111")
                .build();
        User user3 = User.builder()
                .name("Tuan")
                .email("tuan@gmail.com")
                .password("111")
                .build();
        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);

    }

    @Test
    void get_user_dto(){
        // Method query + Convert to Dto
        User user = userRepository.findUserById(1L);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        System.out.println(userDto.getId() + " - " + userDto.getName() + " - " + userDto.getEmail());

        // Native Query
        UserDto userDto1 = userRepository.getUserDto(1L);
        System.out.println(userDto1.getId() + " - " + userDto1.getName() + " - " + userDto1.getEmail());

        // JPQL Query
        UserDto userDto2 = userRepository.getUserDtoByJPQL(1L);
        System.out.println(userDto2.getId() + " - " + userDto2.getName() + " - " + userDto2.getEmail());

        // Projection
        UserInfo userInfo = userRepository.getUserInfoById(1L);
        System.out.println(userInfo.getId() + " - " + userInfo.getName() + " - " + userInfo.getEmail());
    }
}
