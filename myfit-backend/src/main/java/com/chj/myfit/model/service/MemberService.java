package com.chj.myfit.model.service;

import com.chj.myfit.model.dto.Diet;
import com.chj.myfit.model.dto.Member;

import java.util.List;

public interface MemberService {
    // 회원 전체 조회
    List<Member> getMemberList();
    // 회원 등록
    void signup(Member member);
    // 회원 탈퇴
    void resign(int id); 
    // 로그인
    Member login(String email, String password);
    // ID로 검색
    Member readMember(int id);
    // 이메일로 검색
    Member readMemberByEmail(String email);
    // 식단 추가
	int saveDiet(Diet diet);
	// 식단 삭제
	int deleteDiet(int dietId);
	// 식단 전체 조회
	List<Diet> getDiet();
	
	int updateMember(Member member);
}
