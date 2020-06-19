package com.example.spring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.domain.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
}
