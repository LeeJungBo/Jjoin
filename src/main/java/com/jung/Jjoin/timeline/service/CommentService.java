package com.jung.Jjoin.timeline.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jung.Jjoin.timeline.domain.Comment;
import com.jung.Jjoin.timeline.domain.Post;
import com.jung.Jjoin.timeline.dto.CommentView;
import com.jung.Jjoin.timeline.repository.CommentRepository;
import com.jung.Jjoin.user.Service.UserService;
import com.jung.Jjoin.user.domain.User;

@Service
public class CommentService {

	
	
	private CommentRepository commentRepository;
	private UserService userService;
	
	public CommentService(CommentRepository commentRepository
			,UserService userService) {
		
		this.commentRepository = commentRepository;
		this.userService = userService;
		
	}
	
	
	
	public Comment addComment(int postId, int userId, String contents) {
		Comment comment= Comment.builder()
								.postId(postId)
								.userId(userId)
								.contents(contents)
								.build();
		
		return commentRepository.save(comment);
	
	}
	
	
	// List<Comment>를 바꿔준것
	public List<CommentView> getCommentListByPostId(int postId){
		
		List<Comment> commentList = commentRepository.findByPostIdOrderByIdDesc(postId);
		
		List<CommentView> commentViewList = new ArrayList<>();
		for(Comment comment:commentList) {
			
			int userId= comment.getUserId();
			User user = userService.getUserById(userId);
			
			CommentView commentView = CommentView.builder()
					   				  .commentId(comment.getId())
					   				  .userId(userId)
					   				  .contents(comment.getContents())
					   				  .loginEmail(user.getEmail())
					   				  .nickName(user.getNickName())
					   				  .build();
			
			commentViewList.add(commentView);
			
		}
		
		return commentViewList;
		
	 }
	
		public Comment updateComment(int id, String contents) {
			
			Optional<Comment> optionalComment = commentRepository.findById(id);
			
			Comment comment = optionalComment.orElse(null);
			
			if(comment != null) {
				Comment updateComment = comment.toBuilder()
											   .contents(contents)
											   .build();
				
				return commentRepository.save(updateComment);
			
			}else {
				return null;
			}
			
			 
			
		}
	
	
		public void deleteComment(int postId) {
		
			List<Comment> commentList = commentRepository.findByPostIdOrderByIdDesc(postId);
			
			commentRepository.deleteAll(commentList);
		}
		
		
		
		public int deleteCommentById(int id) {
			
			Optional<Comment> optionalComment = commentRepository.findById(id);
			
			int count = 0;
			if(optionalComment.isPresent()) {
				commentRepository.delete(optionalComment.get());
				count++;
			}
			
			return count;
		
		}
	
	
	
	
}
