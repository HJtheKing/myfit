package com.chj.myfit.model.service;

import com.chj.myfit.model.dto.Member;
import com.chj.myfit.model.dto.SearchCondition;
import com.chj.myfit.model.dto.Video;

import java.util.List;

public interface VideoService {
	public List<Video> selectAll();
	public Video selectById(int videoId, int memberId);
	public List<Video> searchByCondition(SearchCondition condition);
	public int registVideo(Video video);
	public int editVideo(int id, Video video);
	public int likeVideo(int memberId, int videoId);
	public List<Member> findLikeUserByVideoId(int videoId);
	public int deleteLike(int memberId, int videoId);
	public Video searchById(int id);
}
