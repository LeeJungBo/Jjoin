package com.jung.Jjoin.timeline.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jung.Jjoin.timeline.domain.Likes;

public interface LikesRepository extends JpaRepository<Likes, Integer> {

	// SELECT count(*) FROM `likes` WHERE `postId` = #{postId}
	public int countByPostId(int postId);
	
	// SELECT count(*) FROM `likes` WHERE`userId` = #{userId} AND `postId` = #{postId}
	public int countByUserIdAndPostId(int userId, int postId);
	
	public List<Likes> findByPostIdOrderByIdDesc(int postId);
	// @Transaction 이걸 넣어줘야함(이게 기똥찬게 insert select update 처럼 한꺼번에 수행되다가 update가 에러가 걸릴때 insert까지 취소됨
	// 얘는 rmsid public void deleteByPostId(postId);해도됨 하지만 애를 넣을라면 SELECT가 먼저 수행되어서( 같이 묶어줘야함(이게 바로 transaction역할임 어노테이션)
	
	
	
	//DELETE FROM `likes` WHERE `postId` = #{postId} AND `userId` = #{userId}
	public Optional<Likes> findByPostIdAndUserId(int postId, int userId);
	
//	@Modifying // Query 어노테이션을 통해 작성된 변경이 일어나는 쿼리(INSERT, DELETE, UPDATE )를 실행할 때 사용된다.
//			   // @Modifying을 변경이 일어나는 쿼리와 함께 사용해야 JPA에서 변경 감지와 관련된 처리를 생략하고 더 효율적인 실행이 가능하다.
//	@Query(value = "INSERT INTO likes(postId, userId, createDate) VALUES(:postId, :userId, now())", nativeQuery = true)
//	public int insertLikes(@Param("postId") int postId, @Param("userId") int userId);
	
	
	
//	public int deleteByPostIdAndUserId(int postId, int userId);
	
//	@Query(value = "SELECT count(*) FROM `likes` WHERE `postId`= :postId", nativeQuery = true)
//	public int likesCountByPostId(@Param("postId") int postId);
}
