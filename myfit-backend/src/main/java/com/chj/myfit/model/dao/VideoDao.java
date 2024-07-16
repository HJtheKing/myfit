package com.chj.myfit.model.dao;

import com.chj.myfit.model.dto.Like;
import com.chj.myfit.model.dto.Member;
import com.chj.myfit.model.dto.SearchCondition;
import com.chj.myfit.model.dto.Video;

import java.util.List;

public interface VideoDao {
	public List<Video> selectAll();
	public Video selectById(int id);
	public void plusViewCnt(int id);
	public List<Video> selectByCondition(SearchCondition condition);
	public int insertVideo(Video video);
	public int updateVideo(Video video);
	public int insertLike(Like like);
	public List<Member> selectLikeUserByVideoId(int videoId);
	public int deleteLike(Like like);
	public void plusLikeCnt(int videoId);
	public void minusLikeCnt(int videoId);
}
