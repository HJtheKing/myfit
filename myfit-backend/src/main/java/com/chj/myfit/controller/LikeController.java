package com.chj.myfit.controller;

import com.chj.myfit.model.dto.Like;
import com.chj.myfit.model.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "찜 관리", description = "찜 정보를 관리하는 컨트롤러")
@RequestMapping("/api-like")
@RestController
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @Operation(summary = "찜 전체 조회 메서드")
    @GetMapping("/like")
    public ResponseEntity<?> list() {
        List<Like> result = likeService.selectAll();
        return new ResponseEntity<List<Like>>(result, HttpStatus.OK);
    }

    @Operation(summary = "찜 상세 조회 메서드")
    @GetMapping("/like/{id}")
    public ResponseEntity<?> detail(@PathVariable("id") int id) {
        Like result = likeService.searchById(id);
        if (result == null) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Like>(result, HttpStatus.OK);
    }

    @Operation(summary = "찜 등록 메서드")
    @PostMapping("/like")
    public ResponseEntity<?> regist(@ModelAttribute Like like) {
        System.out.println("memberId : " + like.getMemberId() + " videoId : " + like.getVideoId());
        int result = likeService.insertLike(like);
        return new ResponseEntity<Integer>(result, result == 1 ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "특정 회원 찜 목록 조회 메서드")
    @GetMapping("/like/member/{id}")
    public ResponseEntity<?> listByMemberId(@PathVariable("id") int id) {
        List<Like> result = likeService.searchByMemberId(id);
        if (result == null || result.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Like>>(result, HttpStatus.OK);
    }

    @Operation(summary = "찜 삭제 메서드")
    @DeleteMapping("/like/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        int result = likeService.deleteLike(id);
        return new ResponseEntity<Integer>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
