package com.chj.myfit.controller;

import com.chj.myfit.model.dto.Inbody;
import com.chj.myfit.model.dto.Member;
import com.chj.myfit.model.service.InbodyService;
import com.chj.myfit.model.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "회원 관리", description = "회원 정보를 관리하는 컨트롤러")
@RestController
@RequestMapping("/api-member")
public class MemberController {

    private final MemberService memberService;
    private final InbodyService inbodyService;

    @Autowired
    public MemberController(MemberService memberService, InbodyService inbodyService) {
        this.memberService = memberService;
        this.inbodyService = inbodyService;
    }

    @Operation(summary = "회원 전체 조회 메서드")
    @GetMapping("/member")
    public ResponseEntity<?> list() {
        List<Member> list = memberService.getMemberList();
        return new ResponseEntity<List<Member>>(list, HttpStatus.OK);
    }

    @Operation(summary = "회원 조회 메서드(id)")
    @GetMapping("/member/{id}")
    public ResponseEntity<?> detailById(@PathVariable("id") int id) {
        Member member = memberService.readMember(id);
        member.setInbodies(inbodyService.searchByMemberId(id));
        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }

    @Operation(summary = "회원 조회 메서드(email)")
    @PostMapping("/member/{email}")
    public ResponseEntity<?> detailByEmail(@PathVariable("email") String email) {
        Member member = memberService.readMemberByEmail(email);
        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }

    @Operation(summary = "회원 정보 추가 메서드")
    @PostMapping("/signup")
    public ResponseEntity<?> write(@RequestBody Member member) {
    	System.out.println(member.getEmail());
        member.setSignupDate(LocalDate.now());
        memberService.signup(member);
        return new ResponseEntity<Member>(member, HttpStatus.CREATED);
    }

    @Operation(summary = "인바디 정보 추가 메서드")
    @PostMapping("/inbody")
    public ResponseEntity<?> writeInbody(@RequestBody Inbody inbody) {
        inbodyService.registInbody(inbody);
        return new ResponseEntity<Inbody>(inbody, HttpStatus.CREATED);
    }

    @Operation(summary = "회원 로그인 메서드")
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Member member) {
    	HttpStatus status = null;
    	Map<String, Object> result = new HashMap<>();
    	
    	Member tmp = memberService.login(member.getEmail(), member.getPassword());
    	
        if (tmp == null) {
            result.put("message", "FAIL");
            status = HttpStatus.UNAUTHORIZED;
        } else {
            result.put("message", "OK");
            result.put("id", tmp.getMemberId());
            status = HttpStatus.ACCEPTED;
        }
        
        return new ResponseEntity<>(result, status);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
    	HttpSession session = request.getSession();
    	System.out.println(session.getAttribute("loginMember"));
    	if(session != null) {
    		session.invalidate();
    	}
    	return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Operation(summary = "회원 탈퇴 메서드")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> resign(@PathVariable("id") int id) {
        memberService.resign(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    
    @Operation(summary = "이메일 충복 체크 메서드")
    @GetMapping("/check/{email}")
    public ResponseEntity<?> dupleCheck(@PathVariable("email") String email){
    	Member member = memberService.readMemberByEmail(email);
    	
    	if(member == null) return new ResponseEntity<Void>(HttpStatus.OK);
    	System.out.println(member.getEmail());
    	return new ResponseEntity<String>(member.getEmail(), HttpStatus.CONFLICT);
    }
    
    @Operation(summary = "회원 수정 메서드")
    @PutMapping("/member/{memberId}")
    public ResponseEntity<?> editMember(@PathVariable("memberId") int memberId, @RequestBody Member member){
    	member.setMemberId(memberId);
    	int result = memberService.updateMember(member);
    	return new ResponseEntity<Integer>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
