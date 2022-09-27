package com.example.spring_test.entity;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", orphanRemoval = false)
    private Set<Product> products = new LinkedHashSet<>();

    @PreRemove
    private void preRemove(){
        products.forEach(product -> product.setCategory(null));
    }
}
