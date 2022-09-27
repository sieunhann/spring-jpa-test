package com.example.spring_test.entity;

import com.example.spring_test.dto.UserDto;
import lombok.*;

import javax.persistence.*;

@SqlResultSetMapping(
        name = "UserDto",
        classes = @ConstructorResult(
                targetClass = UserDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "email", type = String.class)
                }
        )
)
@NamedNativeQuery(
        name = "getUserDto",
        query = "SELECT u.id, u.name, u.email from user u WHERE u.id = ?1",
        resultSetMapping = "UserDto"
)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}