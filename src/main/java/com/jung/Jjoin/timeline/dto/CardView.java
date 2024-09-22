package com.jung.Jjoin.timeline.dto;

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
	
	private boolean likesStatus;
	private int likesCount;
	
	

	
	
	
}
