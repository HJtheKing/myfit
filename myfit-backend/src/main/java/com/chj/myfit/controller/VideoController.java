package com.chj.myfit.controller;

import com.chj.myfit.model.dto.Member;
import com.chj.myfit.model.dto.SearchCondition;
import com.chj.myfit.model.dto.Video;
import com.chj.myfit.model.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "비디오 관리", description = "비디오 정보를 관리하는 컨트롤러")
@RequestMapping("/api-video")
@RestController
public class VideoController {
	private final VideoService videoService;
	private final ResourceLoader resourceLoader;

	@Autowired
	public VideoController(VideoService videoService, ResourceLoader resourceLoader) {
		this.videoService = videoService;
		this.resourceLoader = resourceLoader;
	}

	@Operation(summary = "비디오 전체 조회 메서드")
	@GetMapping("/video")
	public ResponseEntity<?> list() {
		List<Video> videos = videoService.selectAll();
		if (videos == null || videos.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Video>>(videos, HttpStatus.OK);
	}
	
	@Operation(summary = "id로 검색하는 비디오 상세 조회 메서드")
	@GetMapping("/video/{id}")
	public ResponseEntity<?> searchById(@PathVariable("id") int id){
		Video result = videoService.searchById(id);
		if(result == null) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Video>(result, HttpStatus.OK);
	}
	
	@Operation(summary = "비디오 상세 조회 메서드")
	@GetMapping("/detail")
	public ResponseEntity<?> detail(@RequestParam("videoId") int videoId, @RequestParam("memberId")int memberId) {
		Video video = videoService.selectById(videoId, memberId);
		if (video == null) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Video>(video, HttpStatus.OK);
	}

	@Operation(summary = "검색 조건에 맞는 값 반환 메서드")
	@GetMapping("/video/search")
	public ResponseEntity<?> searchVideo(@ModelAttribute SearchCondition condition) throws Exception {
		List<Video> videoList = videoService.searchByCondition(condition);
		if (videoList == null || videoList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Video>>(videoList, HttpStatus.OK);
	}

	@Operation(summary = "영상 정보 추가 메서드")
	@PostMapping("/video")
	public ResponseEntity<?> registVideo(@RequestBody Video video) {
		int result = videoService.registVideo(video);
		if (video == null) return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		return result==1 ? new ResponseEntity<Void>(HttpStatus.CREATED) : new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
	
	@Operation(summary = "영상 정보 수정 메서드")
	@PutMapping("/video/{id}")
	public ResponseEntity<?> registVideo(@PathVariable("id") int id, @RequestBody Video video) {
		if(video == null) return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		int result = videoService.editVideo(id, video);
		if(result>0) return new ResponseEntity<Void>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	// 최종적인 모습 : sessionStorage에서 userId를 받아오고, pathvariable에 있는 정보를 별도로 발송하여 두가지 정보가 도착
	@Operation(summary = "영상 좋아요 누르기")
	@PostMapping("/video/like")
	public ResponseEntity<?> likeVideo(@RequestBody Map<String, Integer> input){
		int memberId = input.get("memberId");
		int videoId = input.get("videoId");
		int result = videoService.likeVideo(memberId, videoId);
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}
	
	@Operation(summary = "좋아요 누른 사람 조회하기")
	@GetMapping("/video/like/{videoId}")
	public ResponseEntity<?> findLikeUser(@PathVariable("videoId") int videoId){
		List<Member> likeList = videoService.findLikeUserByVideoId(videoId);
		if(likeList == null || likeList.size()==0) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Member>>(likeList, HttpStatus.OK);
	}
	
	@Operation(summary="좋아요 삭제하기")
	@DeleteMapping("video/like")
	public ResponseEntity<?> deleteLike(@RequestParam("videoId") int videoId, @RequestParam("memberId")int memberId){
		int result = videoService.deleteLike(memberId, videoId);
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
