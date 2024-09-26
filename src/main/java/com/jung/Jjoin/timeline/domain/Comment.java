package com.jung.Jjoin.timeline.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.jung.Jjoin.user.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder=true)
@Getter
@Table(name="comment")
@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="postId")
	private int postId;
	
	@Column(name="userId")
	private int userId;
	
	@Column(length=100, nullable=false)
	private String contents;
	
	@Column(name="createdAt")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name="updatedAt")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	

	
	
//	@JoinColumn(name="postId", nullable=false)
//	@ManyToOne(fetch = FetchType.EAGER)
//	private Post post;
//	
//	@JoinColumn(name="userId", nullable=false)
//	@ManyToOne(fetch = FetchType.EAGER)
//	private User user;
	
	
}
