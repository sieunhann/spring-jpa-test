package com.example.spring_test;

import com.example.spring_test.entity.Category;
import com.example.spring_test.entity.Product;
import com.example.spring_test.repository.CategoryRepository;
import com.example.spring_test.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class InitQuestion12Test {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void save_category(){
        Category category = Category.builder()
                .name("Toys")
                .build();
        categoryRepository.save(category);
    }

    @Test
    void delete_category(){
        categoryRepository.deleteById(1L);
    }

    @Test
    void save_Product(){
        Category category = categoryRepository.findById(1L).get();
        Product product = Product.builder()
                .name("Ball")
                .category(category)
                .build();
        System.out.println(category.getProducts());
        productRepository.save(product);
    }
}
