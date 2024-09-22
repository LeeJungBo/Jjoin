package com.jung.Jjoin.timeline.service;

import org.springframework.stereotype.Service;

import com.jung.Jjoin.timeline.domain.Comment;
import com.jung.Jjoin.timeline.repository.CommentRepository;

@Service
public class CommentService {

	
	
	private CommentRepository commentRepository;
	
	public CommentService(CommentRepository commentRepository) {
		
		this.commentRepository = commentRepository;
		
	}
	
	public Comment addComment(int postId, int userId, String contents) {
		Comment comment= Comment.builder()
								.postId(postId)
								.userId(userId)
								.contents(contents)
								.build();
		return commentRepository.save(comment);
	}
	
	
	
	
	
	
}
