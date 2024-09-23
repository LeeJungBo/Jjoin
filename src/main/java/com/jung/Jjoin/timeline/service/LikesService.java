package com.jung.Jjoin.timeline.service;

import org.springframework.stereotype.Service;

import com.jung.Jjoin.timeline.domain.Likes;
import com.jung.Jjoin.timeline.repository.LikesRepository;

@Service
public class LikesService {

	private LikesRepository likesRepository;
	
	
	public LikesService(LikesRepository likesRepository) {
		this.likesRepository = likesRepository;
	}
	
	
	public Likes addLikes(int postId, int userId) {
		
		Likes likes= Likes.builder()
					 .postId(postId)
					 .userId(userId)
					 .build();
		
		     
		return likesRepository.save(likes);
		
		
		
	}
	
	// 게시글에 대응되는 좋아요 개수 조회
	public int getLikeCount(int postId){
		
		return likesRepository.countByPostId(postId);
		
	}
	
	
	// 특정사용자가 특정 게시글에 좋아요를 했는지 안했는지
	public boolean isLikesByuserIdAndPostId(int userId, int postId){
		// 특정 userId와 postId가 일치하는 행 조회
		
		int count = likesRepository.countByUserIdAndPostId(userId, postId);
		
		if(count == 0) {
			return false;
		}else {
			return true;
		}
	}
	
	
//	public int deleteLikes(int imageId, int userId) {
//		return likesRepository.deleteByPostIdAndUserId(imageId, userId);
//	}
	
	
//	@Transactional
//	트랜잭션(Transaction)이란?
//			트랜잭션은 우리말로 ‘거래’라는 뜻을 갖고 있으므로, 먼저 거래와 관련된 간단한 예시를 들어보겠다.
//
//			어떤 사람과 거래를 하고 있는 상황이다.
//
//			물건을 받기 위해 상대방에게 선입금을 했다.
//			그런데 벽돌이 도착했다.
//			사기당한 사실을 깨닫고 나면, 시간을 되돌리고 싶지 않을까?
//
//			트랜잭션 개념은 이를 가능하게 한다.
//
//			상대방에게 입금하는 작업이 성공했더라도 원하던 물건을 받지 못했다면 입금이 취소되었을 것이다.
//
//			모든 작업들이 성공적으로 완료되어야 작업 묶음의 결과를 적용하고, 어떤 작업에서 오류가 발생했을 때는 이전에 있던 모든 작업들이 성공적이었더라도 없었던 일처럼 완전히 되돌리는 것이 트랜잭션의 개념이다.
//
//			데이터베이스를 다룰 때 트랜잭션을 적용하면 데이터 추가, 갱신, 삭제 등으로 이루어진 작업을 처리하던 중 오류가 발생했을 때 모든 작업들을 원상태로 되돌릴 수 있다. 모든 작업들이 성공해야만 최종적으로 데이터베이스에 반영하도록 한다.
//
//			스프링에서 @Transactional 을 이용하여 트랜잭션 처리를 하는 방법
//			DB와 관련된, 트랜잭션이 필요한 서비스 클래스 혹은 메서드에 @Transactional 어노테이션을 달아주면된다.
//
//			클래스, 메서드 모두에 @Transactional 어노테이션을 붙이면 메서드 레벨의 @Transactional 선언이 우선 적용된다.
//
//			java //게시판의 게시글을 삭제하는 메서드 @Transactional public void removeBoard(Long id) throws Exception { replyDAO.removeAll(id); //삭제할 게시글의 답글 삭제 boardDAO.deleteBoard(id); //게시글 삭제 }
//
//			@Transactional이 붙은 메서드는 메서드가 포함하고 있는 작업 중에 하나라도 실패할 경우 전체 작업을 취소한다.
	
	
}
