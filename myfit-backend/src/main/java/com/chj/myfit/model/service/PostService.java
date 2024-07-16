package com.chj.myfit.model.service;

import com.chj.myfit.model.dto.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    List<Post> selectAll();
    List<Post> searchByMemberId(int memberId);
    List<Post> searchByVideoId(int videoId);
    Post searchById(int id);
    int createPost(List<MultipartFile> multipartFiles, Post post);
    int updatePost(List<MultipartFile> multipartFiles, Post post);
    int deletePost(int id);
}
