package com.board.domain;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * Member Domain 클래스
 * DB의 member 테이블과 1:1 매핑되는 객체
 * - memberId : 회원 번호 (PK, 자동 증가)
 * - email    : 로그인용 이메일 (아이디 대신 사용)
 * - password : 비밀번호 (BCrypt 암호화 저장)
 * - username : 사용자 실명
 * - createdAt: 가입일
 */
@Data
public class Member {
    private Long memberId;          // PK, 자동 증가
    private String email;           // 로그인용 이메일
    private String password;        // 암호화된 비밀번호
    private String username;        // 사용자 실명
    private LocalDateTime createdAt;// 가입일
    private LocalDateTime updatedAt;// 수정일
}
