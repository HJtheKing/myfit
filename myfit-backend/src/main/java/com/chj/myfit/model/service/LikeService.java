package com.chj.myfit.model.service;

import com.chj.myfit.model.dto.Like;

import java.util.List;

public interface LikeService {
    List<Like> selectAll();
    
    List<Like> searchByMemberId(int memberId);
    
    List<Integer> findMemberIdByVideoId(int videoId);
    
    Like searchById(int id);
    
    int insertLike(Like like);
    
    int deleteLike(int id);
}
