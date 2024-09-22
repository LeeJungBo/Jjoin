package com.jung.Jjoin.timeline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jung.Jjoin.timeline.domain.Likes;

public interface LikesRepository extends JpaRepository<Likes, Integer> {

	
	
	
	
//	@Modifying // Query 어노테이션을 통해 작성된 변경이 일어나는 쿼리(INSERT, DELETE, UPDATE )를 실행할 때 사용된다.
//			   // @Modifying을 변경이 일어나는 쿼리와 함께 사용해야 JPA에서 변경 감지와 관련된 처리를 생략하고 더 효율적인 실행이 가능하다.
//	@Query(value = "INSERT INTO likes(postId, userId, createDate) VALUES(:postId, :userId, now())", nativeQuery = true)
//	public int insertLikes(@Param("postId") int postId, @Param("userId") int userId);
	
	
	
//	public int deleteByPostIdAndUserId(int postId, int userId);
	
//	@Query(value = "SELECT count(*) FROM `likes` WHERE `postId`= :postId", nativeQuery = true)
//	public int likesCountByPostId(@Param("postId") int postId);
}
