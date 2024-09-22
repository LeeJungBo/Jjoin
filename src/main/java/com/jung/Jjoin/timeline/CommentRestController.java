package com.jung.Jjoin.timeline;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jung.Jjoin.timeline.domain.Comment;
import com.jung.Jjoin.timeline.service.CommentService;

import jakarta.servlet.http.HttpSession;

@RestController
public class CommentRestController {
	 
	private CommentService commentService;
	
	
	public CommentRestController(CommentService commentService) {
		
		this.commentService = commentService;
		
	}
	
	@PostMapping("/comment/create")
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
	
	

}
