package com.chj.myfit.model.dao;

import com.chj.myfit.model.dto.Diet;
import com.chj.myfit.model.dto.Member;
import com.chj.myfit.model.dto.SearchCondition;

import java.util.List;
import java.util.Map;

public interface MemberDao {

    List<Member> selectAll();

    Member searchById(int id);

    Member selectOne(Map<String, String> info);

    Member selectByEmail(String email);

    List<Member> searchByCondition(SearchCondition con);

    int insertMember(Member member);

    int deleteMember(int id);

    int updateMember(Member member);

	int insertDiet(Diet diet);

	int deleteDiet(int dietId);

	List<Diet> selectAllDiet();
}
