package com.chj.myfit.model.dto;

public class Like {
    
    private int likeId;
    
    private int memberId;
    
    private int videoId;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getLikeId() {
        return likeId;
    }
    
    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    @Override
    public String toString() {
        return "Like [likeId=" + likeId + ", memberId=" + memberId + ", videoId=" + videoId + "]";
    }
}
