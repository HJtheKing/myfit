package com.chj.myfit.model.dao;

import com.chj.myfit.model.dto.Post;

import java.util.List;

public interface PostDao {
    List<Post> selectAll();
    
    // 특정 회원이 작성한 포스트
    List<Post> searchByMemberId(int memberId);
    
    // 특정 비디오에 작성된 포스트
    List<Post> searchByVideoId(int videoId);
    
    Post searchById(int id);
    
    int insertPost(Post post);
    
    int updatePost(Post post);
    
    int deletePost(int id);
    
    int insertFile(Post post);

}
