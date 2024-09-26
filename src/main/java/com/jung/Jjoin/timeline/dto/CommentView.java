package com.jung.Jjoin.timeline.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentView { // 화면구성을 위한 CommenView dto만들기 // 즉, dto란 클래스들끼리 합쳐주기 위한 기능을 함

	private int commentId;
	private int userId;
	
	private String contents;
	private String loginEmail;
	private String nickName;
	
	
}
