package com.jung.Jjoin.timeline;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jung.Jjoin.timeline.domain.Post;
import com.jung.Jjoin.timeline.service.TimelineService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/timeline")
@RestController
public class TimelineRestController {

	private TimelineService timelineService;
	
	public TimelineRestController(TimelineService timelineService) {
		this.timelineService = timelineService;
	}
	
	@PostMapping("/create")
	public Map<String, String> timelineCreate(
			@RequestParam("imageFile") MultipartFile file
			, @RequestParam("contents") String contents
			, HttpSession session){// HttpSession session 넣는 이유 이미 로그인 되어있는 유저의 userId에 해당하는 정보를 갖고와야 그 유저의 타임라인을 작성할수 있으므로
		
		int userId = (Integer)session.getAttribute("userId");
		Post post = timelineService.addTimeline(userId, contents, file);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(post != null) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
			
		return resultMap;
	}
	
	
	
	
	
}
