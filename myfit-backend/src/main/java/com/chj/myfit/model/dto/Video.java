package com.chj.myfit.model.dto;

import java.util.List;

public class Video {
    // Fields
    // 자동 증가 비디오 id (PK)
    private int videoId;
    // 영상 제목
    private String title;
    // 채널 이름
    private String channelName;
    // 운동 부위
    private String exerciseArea;
    // 조회수
    private int viewCnt;
    // 좋아요
    private int likeCnt;
    // 영상의 youtube id
    private String youtubeId;
    // 좋아요를 누른 회원의 List
    private List<Member> likeMember;
    // 회원이 좋아요를 눌렀는지 여부
    private boolean isLike;
    
	// Constructors
    public Video(){}    
    public Video(String title, String channelName, String exerciseArea, String youtubeId) {
        this.title = title;
        this.channelName = channelName;
        this.exerciseArea = exerciseArea;
        this.youtubeId = youtubeId;
    }
    
    // Methods
    public int getVideoId() {
        return videoId;
    }
    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getChannelName() {
        return channelName;
    }
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getExerciseArea() {
        return exerciseArea;
    }
    public void setExerciseArea(String exerciseArea) {
        this.exerciseArea = exerciseArea;
    }
    public int getViewCnt() {
        return viewCnt;
    }
    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }
    public int getLikeCnt() {
        return likeCnt;
    }
    public void setLikeCnt(int likeCnt) {
        this.likeCnt = likeCnt;
    }
    public String getYoutubeId() {
        return youtubeId;
    }
    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }
    public List<Member> getLikeMember() {
        return likeMember;
    }
    public void setLikeMember(List<Member> likeMember) {
        this.likeMember = likeMember;
    }
    public boolean isLike() {
		return isLike;
	}
	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}
	
    @Override
    public String toString() {
        return "Video [videoId=" + videoId + ", title=" + title + ", channelName=" + channelName + ", exerciseArea="
                + exerciseArea + ", viewCnt=" + viewCnt + ", likeCnt=" + likeCnt + ", youtubeId=" + youtubeId
                + ", likeMember=" + likeMember + "]";
    }
}
