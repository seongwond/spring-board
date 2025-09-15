package com.board.domain;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * Board Domain 클래스
 * DB의 board 테이블과 1:1 매핑되는 객체
 * - boardId : 글 번호 (PK)
 * - title : 글 제목
 * - content : 글 내용
 * - writer : 작성자 (추후 memberId로 연결 가능)
 * - createdAt : 작성일
 * - updatedAt : 수정일
 */
@Data
public class Board {
    private Long boardId;            // PK, 자동 증가
    private String title;            // 글 제목
    private String content;          // 글 내용
    private String writer;           // 작성자
    private LocalDateTime createdAt; // 작성일
    private LocalDateTime updatedAt; // 수정일
}
