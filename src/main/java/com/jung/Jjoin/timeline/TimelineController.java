package com.jung.Jjoin.timeline;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jung.Jjoin.timeline.domain.Post;
import com.jung.Jjoin.timeline.dto.CardView;
import com.jung.Jjoin.timeline.service.TimelineService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/post")
public class TimelineController {

	private TimelineService timelineService;
	
	
	public TimelineController(TimelineService timelineService) {
		
		this.timelineService = timelineService;
	
	}
	
	
	@GetMapping("/timeline-Detail")
	public String timelineDetail(
			 Model model
			, HttpSession session) {
		
		int loginUserId = (Integer)session.getAttribute("userId");
		List<CardView> cardViewList = timelineService.getTimeline(loginUserId);
		model.addAttribute("cardViewList", cardViewList);
		
		
		
		return "timeline/timelineDetailView";
		
	}
	
	@GetMapping("/timeline-update")
	public String timelineUpdate(
			Model model
			, @RequestParam("id") int id){
		
		Post post = timelineService.getTimelineById(id);
		
		model.addAttribute(post);
		
		
		return "timeline/timelineUpdateView";
	
	}
	
	
	
	@GetMapping("/timeline-create")
	public String timelineView(){
		
		return "timeline/timelineCreateView";
	
	}
	
	
	
}
