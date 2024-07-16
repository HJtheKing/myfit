package com.chj.myfit.model.dao;

import com.chj.myfit.model.dto.Like;

import java.util.List;

public interface LikeDao {
	List<Like> selectAll();
	
	// 회원이 찜한 영상 리스트
	List<Like> searchByMemberId(int memberId);
	
	// 해당 영상을 찜한 회원 ID 리스트
	List<Integer> findMemberIdByVideoId(int videoId);
	
	Like searchById(int id);
	
	int insertLike(Like like);
	
	int deleteLike(int id);
}
