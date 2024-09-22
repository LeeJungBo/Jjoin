package com.jung.Jjoin.timeline;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		
		int userId = (Integer)session.getAttribute("userId");
		List<CardView> cardViewList = timelineService.getTimeline();
		model.addAttribute("cardViewList", cardViewList);
		return "timeline/timelineDetailView";
		
	}
	
	
	
	
	@GetMapping("/timeline-create")
	public String timelineView(){
		
		return "timeline/timelineCreateView";
	
	}
	
	
	
}
