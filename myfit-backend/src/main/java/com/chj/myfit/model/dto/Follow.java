package com.chj.myfit.model.dto;

public class Follow {
    
    private int memberId;
    
    private int followingId;

    @Override
    public String toString() {
        return "Follow [memberId=" + memberId + ", followingId=" + followingId + "]";
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getFollowingId() {
        return followingId;
    }

    public void setFollowingId(int followingId) {
        this.followingId = followingId;
    }
}
