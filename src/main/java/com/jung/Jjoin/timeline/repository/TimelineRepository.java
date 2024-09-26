package com.jung.Jjoin.timeline.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jung.Jjoin.timeline.domain.Post;

public interface TimelineRepository extends JpaRepository<Post, Integer> {

	//ORDER BY `id` DESC
	public List<Post> findAllByOrderByIdDesc();
	
	public Optional<Post> findByIdAndUserId(int id, int userId); // 그 포스터를 작성한 작성자만 지워줄수 있게
	
}
