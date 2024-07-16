package com.chj.myfit.model.dto;

import java.time.LocalDate;

public class Post {
    
    private int postId;
    
    private int videoId;
    
    private int memberId;
    
    private String content;
    
    private String title;
    
    private String writer;
    
    private String imgSrc1;
    
    private String imgSrc2;
    
    private String imgSrc3;
    
    private LocalDate regDate;
    
    private String fileId;
    
    private String fileName;

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", videoId=" + videoId + ", memberId=" + memberId + ", content=" + content
				+ ", title=" + title + ", writer=" + writer + ", imgSrc1=" + imgSrc1 + ", imgSrc2=" + imgSrc2
				+ ", imgSrc3=" + imgSrc3 + ", regDate=" + regDate + ", fileId=" + fileId + ", fileName=" + fileName
				+ "]";
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getImgSrc1() {
		return imgSrc1;
	}

	public void setImgSrc1(String imgSrc1) {
		this.imgSrc1 = imgSrc1;
	}

	public String getImgSrc2() {
		return imgSrc2;
	}

	public void setImgSrc2(String imgSrc2) {
		this.imgSrc2 = imgSrc2;
	}

	public String getImgSrc3() {
		return imgSrc3;
	}

	public void setImgSrc3(String imgSrc3) {
		this.imgSrc3 = imgSrc3;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
