package com.board.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.board.domain.Member;

public interface MemberService extends UserDetailsService {
    void register(Member member);                    // 회원가입 (암호화 포함)
    Member login(String email, String plainPassword);// 로그인 검증 (성공시 Member 리턴, 실패 null)
    Member findByEmail(String email);                // 이메일로 회원 조회 (중복체크용)
}
