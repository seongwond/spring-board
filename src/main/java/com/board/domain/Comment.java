package com.board.domain;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 기능: 댓글(Comment) 도메인 클래스
 * 설명: 데이터베이스의 comment 테이블과 매핑되는 객체
 * - commentId : 댓글의 고유 번호 (Primary Key)
 * - boardId   : 댓글이 속한 게시글의 번호 (Foreign Key)
 * - writer    : 댓글을 작성한 사용자 이름
 * - content   : 댓글 내용
 * - createdAt : 댓글 작성일
 * - updatedAt : 댓글 수정일
 */
@Data
public class Comment {
    private Long commentId;        // PK, 자동 증가
    private Long boardId;          // FK, 어떤 게시글에 달린 댓글인지
    private String writer;         // 작성자
    private String content;        // 댓글 내용
    private LocalDateTime createdAt; // 작성일
    private LocalDateTime updatedAt; // 수정일
}