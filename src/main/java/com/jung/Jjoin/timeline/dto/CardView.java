package com.jung.Jjoin.timeline.dto;

import java.util.List;

import com.jung.Jjoin.timeline.domain.Comment;
import com.jung.Jjoin.user.domain.User;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CardView {

	
	private int userId;
	private int postId;
	
	private String contents;
	private String imagePath;
	
	private String nickname;
	private String profileImage;
	
	private List<User> user;
	private List<CommentView> commentList;
	
	private int likesCount;
	private boolean isLikes;
	

	
	
	
}
