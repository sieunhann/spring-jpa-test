package com.example.spring_test.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String email;

    public UserDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
