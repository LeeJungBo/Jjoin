package com.jung.Jjoin.timeline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jung.Jjoin.timeline.domain.Likes;
import com.jung.Jjoin.timeline.domain.Post;

public interface TimelineRepository extends JpaRepository<Post, Integer> {

	//ORDER BY `id` DESC
	public List<Post> findAllByOrderByIdDesc();
	
	
	
}
