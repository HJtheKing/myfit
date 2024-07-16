package com.chj.myfit.controller;

import com.chj.myfit.model.dto.Post;
import com.chj.myfit.model.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Tag(name = "포스트 관리", description = "포스트 정보를 관리하는 컨트롤러")
@RequestMapping("/api-post")
@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }
    
    @Operation(summary = "포스트 전체 조회 메서드")
    @GetMapping("/post")
    public ResponseEntity<?> list(){
        List<Post> list = postService.selectAll();
        if(list == null) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<List<Post>>(list, HttpStatus.OK);
    }
    
    @Operation(summary = "포스트 상세 조회 메서드")
    @GetMapping("/post/{id}")
    public ResponseEntity<?> detail(@PathVariable("id") int id){
        Post result = postService.searchById(id);
        if(result == null) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Post>(result, HttpStatus.OK);
    }
    
    @Operation(summary = "특정 회원이 작성한 포스트 조회 메서드")
    @GetMapping("/member/{id}")
    public ResponseEntity<?> memberList(@PathVariable("id") int id){
        List<Post> list = postService.searchByMemberId(id);
        if(list == null) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<List<Post>>(list, HttpStatus.OK);
    }
    
    @Operation(summary = "비디오에 달린 포스트 조회 메서드")
    @GetMapping("/video/{id}")
    public ResponseEntity<?> videoList(@PathVariable("id") int id){
        List<Post> list = postService.searchByVideoId(id);
        if(list == null) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<List<Post>>(list, HttpStatus.OK);
    }

    @Operation(summary = "포스트 등록 메서드")
    @PostMapping("/post")
    @Transactional
    public ResponseEntity<?> registPost(@RequestPart Map<String, String> post,
            @RequestPart(required = false) List<MultipartFile> imgList) throws IllegalStateException, IOException{
    	Post madePost = new Post();
    	madePost.setTitle(post.get("title"));
    	madePost.setContent(post.get("content"));
    	madePost.setMemberId(Integer.parseInt(post.get("memberId")));
    	madePost.setVideoId(Integer.parseInt(post.get("videoId")));
    	int result = postService.createPost(imgList, madePost);
    	return new ResponseEntity<Integer>(result, result == 1 ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }
    
    @Operation(summary = "포스트 수정 메서드")
    @PutMapping("/post/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int postId, @RequestPart Post post,
            @RequestPart(required = false) List<MultipartFile> imgList) throws IllegalStateException, IOException{
//    	post.setPostId(postId);
    	System.out.println(post);
    	int result = postService.updatePost(imgList, post);
    	return new ResponseEntity<Integer>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
    
    @Operation(summary = "포스트 삭제 메서드")
    @DeleteMapping("post/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        int result = postService.deletePost(id);
        return new ResponseEntity<Integer>(result, result == 1 ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST);
    }
}
