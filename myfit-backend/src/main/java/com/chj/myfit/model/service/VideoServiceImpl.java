package com.chj.myfit.model.service;

import com.chj.myfit.model.dao.LikeDao;
import com.chj.myfit.model.dao.VideoDao;
import com.chj.myfit.model.dto.Like;
import com.chj.myfit.model.dto.Member;
import com.chj.myfit.model.dto.SearchCondition;
import com.chj.myfit.model.dto.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
	private final VideoDao videoDao;
	private final LikeDao likeDao;

	@Autowired
	public VideoServiceImpl(VideoDao videoDao, LikeDao likeDao) {
		this.videoDao = videoDao;
		this.likeDao = likeDao;
	}

	@Override
	public List<Video> selectAll() {
		return videoDao.selectAll();
	}


	@Override
	public Video selectById(int videoId, int memberId) {
		videoDao.plusViewCnt(videoId);
		Video result = videoDao.selectById(videoId);
		List<Integer> likeList = likeDao.findMemberIdByVideoId(videoId);
		if(likeList.contains(memberId)) {
			result.setLike(true);
		}
		return result;
	}
	
	@Override
	public List<Video> searchByCondition(SearchCondition condition) {
		return videoDao.selectByCondition(condition);
	}

	@Override
	public int registVideo(Video video) {
		return videoDao.insertVideo(video);
	}

	@Override
	public int editVideo(int id, Video video) {
		video.setVideoId(id);
		return videoDao.updateVideo(video);
	}


	@Override
	public int likeVideo(int memberId, int videoId) {
		videoDao.plusLikeCnt(videoId);
		Like like = new Like();
		like.setMemberId(memberId);
		like.setVideoId(videoId);
		return videoDao.insertLike(like);
	}

	@Override
	public List<Member> findLikeUserByVideoId(int videoId) {
		return videoDao.selectLikeUserByVideoId(videoId);
	}

	@Override
	public int deleteLike(int memberId, int videoId) {
		videoDao.minusLikeCnt(videoId);
		Like like = new Like();
		like.setMemberId(memberId);
		like.setVideoId(videoId);
		return videoDao.deleteLike(like);
	}

	@Override
	public Video searchById(int id) {
		return videoDao.selectById(id);
	}
}
