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
 *
 * 새로 추가된 파일 관련 필드
 * - fileName: 업로드된 파일의 원래 이름
 * - filePath: 서버에 저장된 파일의 실제 경로
 */
@Data
public class Board {
    private Long boardId;            // PK, 자동 증가
    private String title;            // 글 제목
    private String content;          // 글 내용
    private String writer;           // 작성자
    private LocalDateTime createdAt; // 작성일
    private LocalDateTime updatedAt; // 수정일
    
    // =========================================================
    // 파일 업로드 기능을 위해 새로 추가된 필드
    // =========================================================
    private String fileName;         // 업로드된 파일의 원본 이름 (예: my_image.jpg)
    private String filePath;         // 서버에 저장된 파일의 경로 (예: /uploads/uuid_my_image.jpg)
}