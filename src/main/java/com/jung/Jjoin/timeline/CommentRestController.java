package com.jung.Jjoin.timeline;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jung.Jjoin.timeline.domain.Comment;
import com.jung.Jjoin.timeline.service.CommentService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/comment")
public class CommentRestController {
	 
	private CommentService commentService;
	
	
	public CommentRestController(CommentService commentService) {
		
		this.commentService = commentService;
		
	}
	
	@PostMapping("/create")
	public Map<String, String> comment(
			@RequestParam("postId") int postId
			, @RequestParam("contents") String contents
			, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		Comment comment = commentService.addComment(postId, userId, contents);
		
		Map<String, String> resultMap = new HashMap<>();
		if( comment != null) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	@PutMapping("/update")
	public Map<String, String> updateComment(
			@RequestParam("id") int id
			, @RequestParam("contents") String contents){
		
		Comment comment = commentService.updateComment(id, contents);
		
		Map<String, String> resultMap = new HashMap<>();
		if(comment != null) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	
	
	@DeleteMapping("/delete")
	public Map<String, String> deleteComment(@RequestParam("id") int id){
		
		int count = commentService.deleteCommentById(id);
		
		Map<String, String> resultMap = new HashMap<>();
		if(count == 1) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	

}
