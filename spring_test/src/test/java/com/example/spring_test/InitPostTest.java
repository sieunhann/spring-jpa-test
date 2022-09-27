package com.example.spring_test;

import com.example.spring_test.entity.Post;
import com.example.spring_test.entity.PostUUID;
import com.example.spring_test.repository.PostRepository;
import com.example.spring_test.repository.PostUUIDRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class InitPostTest {

    @Autowired
    private PostUUIDRepository postUUIDRepository;
    @Autowired
    private PostRepository postRepository;

    @Test
    void save_post(){
        Post post = Post.builder()
                .title("Superman").build();
        postRepository.save(post);
    }

    @Test
    void save_post_UUID(){
        PostUUID post = PostUUID.builder()
                .title("Batman").build();
        postUUIDRepository.save(post);
    }
}
