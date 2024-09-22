package com.jung.Jjoin.timeline.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.jung.Jjoin.user.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;




@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="likes",uniqueConstraints = {@UniqueConstraint(name = "userAndLikesStatus", columnNames = {"postId","userId"})})
// like란 키워드는 쿼리의 문법으로 사용하는거v  따라서 여기다가도 ``를 붙여주는게 좋음
@Entity    
public class Likes {

	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="postId")
	private int postId;
	@Column(name="userId")
	private int userId;
	@CreationTimestamp
	@Column(name="createdAt")
	private LocalDateTime createdAt;
	
	
	//	@OnDelete(action = OnDeleteAction.CASCADE) // 단방향에서 post를 지워줬을때 그에 매칭되는 좋아요도 삭제될수있게 도와주는것
//	@JoinColumn(name="postId") // Post클래스를 조회해줄수 있게 도와주는것
//	@ManyToOne // 1대N관계로써 N에 해당하는데다가 넣어주기 즉, 포스트 하나당 좋아요가 여러개이니
//	private Post post;
//	
//	@JoinColumn(name="userId")
//	@ManyToOne
//	private User user;
	
	
}
