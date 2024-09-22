package com.jung.Jjoin.timeline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jung.Jjoin.timeline.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	
}
