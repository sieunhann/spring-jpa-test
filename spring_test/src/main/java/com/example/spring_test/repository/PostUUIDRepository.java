package com.example.spring_test.repository;

import com.example.spring_test.entity.PostUUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostUUIDRepository extends JpaRepository<PostUUID, UUID> {
}