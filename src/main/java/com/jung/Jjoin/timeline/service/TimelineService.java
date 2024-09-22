package com.jung.Jjoin.timeline.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jung.Jjoin.common.FileManager;
import com.jung.Jjoin.timeline.domain.Post;
import com.jung.Jjoin.timeline.dto.CardView;
import com.jung.Jjoin.timeline.repository.LikesRepository;
import com.jung.Jjoin.timeline.repository.TimelineRepository;
import com.jung.Jjoin.user.Service.UserService;
import com.jung.Jjoin.user.domain.User;

@Service
public class TimelineService {

	private TimelineRepository timelineRepository;
	private UserService userService; // 서비스에서 번거롭더라도 만들어주고 호출해주는것이 바람직한 형태 userRepository에서 끌고 오는것보다
	private LikesRepository likesRepository;
	
	public TimelineService(TimelineRepository timelineRepository, UserService userService) {
		this.timelineRepository = timelineRepository;
		this.userService = userService;
		this.likesRepository = likesRepository;
	}
	
	
	public Post addTimeline(int userId, String contents, MultipartFile file){
		
		String urlPath = FileManager.saveFile(userId, file); // file은 따로 컴퓨터에 저장하고 그 경로를 서버에다가 넣어놓기위한 과정
		
		Post post = Post.builder()
					.userId(userId)
					.imagePath(urlPath)
					.contents(contents)
					.build();
		
		Post result = timelineRepository.save(post);
		
		return result;
	}
	
	public List<CardView> getTimeline() {
		
		List<Post> postList = timelineRepository.findAllByOrderByIdDesc();
		
		
		
		List<CardView> cardViewList = new ArrayList<>();
		// post에는 userId만 있을것 즉 userId로 User에서의 정보를 갖고와야한다.
		for(Post post:postList) {
			
			int userId = post.getUserId();
			int postId = post.getId();
			User user = userService.getUserById(userId); // 이래야 카드에 있는 user정보를 갖고올수 있음 // 카드에 넣을 멤버변수를 채워줄것들이 준비되어있음
//			int likesCount= likesRepository.likesCountByPostId(postId);
			
			CardView cardView = CardView.builder()
								.postId(post.getId())
								.userId(userId)
								.contents(post.getContents())
								.imagePath(post.getImagePath())
								.nickname(user.getNickName())
								.profileImage(user.getProfileImage())
//								.likesCount(likesCount)
								.build();
			
			cardViewList.add(cardView);
			
		}
		
		return cardViewList;
		
	}
	
	

	
	
	
}
