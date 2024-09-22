package com.jung.Jjoin.timeline;

import java.util.HashMap;
import java.util.Map;

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
	
	
	
	
	
}
