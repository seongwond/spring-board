package com.board.mapper;

import com.board.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    void insertMember(Member member);        // 회원 등록
    Member findByEmail(String email);  // 이메일 조회
}
