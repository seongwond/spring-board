package com.board.domain;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * Board Domain 클래스
 * DB의 board 테이블과 1:1 매핑되는 객체
 * - id : 글 번호
 * - title : 글 제목
 * - content : 글 내용
 * - writer : 작성자
 * - createdAt : 작성일
 * - updatedAt : 수정일
 */
@Data
public class Board {
    private Long id;                 // PK, 자동증가
    private String title;            // 글 제목
    private String content;          // 글 내용
    private String writer;           // 작성자
    private LocalDateTime createdAt; // 작성일
    private LocalDateTime updatedAt; // 수정일
}
