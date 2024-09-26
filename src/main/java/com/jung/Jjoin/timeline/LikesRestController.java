package com.jung.Jjoin.timeline;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jung.Jjoin.timeline.domain.Likes;
import com.jung.Jjoin.timeline.service.LikesService;

import jakarta.servlet.http.HttpSession;

@RestController
public class LikesRestController {

	private LikesService likesService;
	
	public LikesRestController(LikesService likesService) {
		this.likesService = likesService;
	}
	
	@PostMapping("/timeline/like")
	public Map<String, String> likes(
			@RequestParam("postId") int postId
			, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId"); // 다운 캐스팅 해준거
		
		Likes likes= likesService.addLikes(postId, userId); // int count가 아니구나
		
		Map<String, String> resultMap = new HashMap<>();
		if(likes != null) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	
	@DeleteMapping("/timeline/unlike")
	public Map<String, String>unlike(
			@RequestParam("postId") int postId
			, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		// 2번 호출했따는건 메소드가 2개있었다는거고 그러면 결국 메소드가 두개있었다는거 likesService.deleteLike(postId, userId) 이게 2개 있었음
		
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(likesService.deleteLike(postId, userId)) {
			
			resultMap.put("result", "success");
		}else{
			
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	
	
	
	
}
