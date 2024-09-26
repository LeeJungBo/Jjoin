package com.jung.Jjoin.timeline.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jung.Jjoin.common.FileManager;
import com.jung.Jjoin.timeline.domain.Post;
import com.jung.Jjoin.timeline.dto.CardView;
import com.jung.Jjoin.timeline.dto.CommentView;
import com.jung.Jjoin.timeline.repository.TimelineRepository;
import com.jung.Jjoin.user.Service.UserService;
import com.jung.Jjoin.user.domain.User;

@Service
public class TimelineService {

	private TimelineRepository timelineRepository;
	private UserService userService; // 서비스에서 번거롭더라도 만들어주고 호출해주는것이 바람직한 형태 userRepository에서 끌고 오는것보다
	private LikesService likesService;
	private CommentService commentService;
	
	public TimelineService(
			TimelineRepository timelineRepository, 
			UserService userService, 
			LikesService likesService,
			CommentService commentService) {
		
		this.timelineRepository = timelineRepository;
		this.userService = userService;
		this.likesService = likesService;
		this.commentService = commentService;
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
	
	
	public List<CardView> getTimeline(int loginUserId) {
		
		List<Post> postList = timelineRepository.findAllByOrderByIdDesc();
		
		List<CardView> cardViewList = new ArrayList<>();
		// post에는 userId만 있을것 즉 userId로 User에서의 정보를 갖고와야한다.
		for(Post post:postList) {
			
			int userId = post.getUserId();
			int postId = post.getId();
			User user = userService.getUserById(userId); // 이래야 카드에 있는 user정보를 갖고올수 있음 // 카드에 넣을 멤버변수를 채워줄것들이 준비되어있음
//			int likesCount= likesRepository.likesCountByPostId(postId);
			List<CommentView> commentList = commentService.getCommentListByPostId(post.getId());
			
//			Comment comment = new Comment(); 혹시 댓글의 유저정보를 갖고올라면 HttpSession을 그냥 넣어버리면 될려나 (맞았다 ㅋㅋㅋ loginUserId 이걸통해서 알수있음)
			int likesCount= likesService.getLikeCount(post.getId()); //(post.getId() postId를 이걸로 표현
			boolean isLikes = likesService.isLikesByuserIdAndPostId(loginUserId, post.getId()); // loginUserId 이건 로그인한 사용자의 id를 써야함
															// 그래야 지금 로그인되어있는 사용자가 좋아요를 클릭했을때 하트모양이 채워짐
			CardView cardView = CardView.builder()
								.postId(post.getId())
								.userId(userId)
								.contents(post.getContents())
								.imagePath(post.getImagePath())
								.nickname(user.getNickName())
								.profileImage(user.getProfileImage())
								.commentList(commentList)
								.likesCount(likesCount)
								.isLikes(isLikes)
								.build();
			
			cardViewList.add(cardView);
		}
		
		return cardViewList;
	
	}
	
	
	public Post updatePost(int id, String contents) {
		
		Optional<Post> optionalPost = timelineRepository.findById(id);
		
		Post post = optionalPost.orElse(null);
		
		if(post != null) {
			 Post updatePost = post.toBuilder()
					        	   .contents(contents)
					        	   .build();
			 
			return  timelineRepository.save(updatePost);
		}
			
			return null;
			
	}
	
	
	
	
	
	public int deleteTimeline(int postId, int userId) {
		
		Optional<Post> optionalPost = timelineRepository.findByIdAndUserId(postId, userId);
		commentService.deleteComment(postId);
		likesService.deleteLikes(postId);
		
		
		
		
		int count = 0;
		if(optionalPost.isPresent()) {
			timelineRepository.delete(optionalPost.get());
			FileManager.removeFile(optionalPost.get().getImagePath());
			count++;
		}
		
		return count;
		
		
	}
	
	
	
	
	
	

	
	
	
}
