package com.board.service;

import java.util.List;

import com.board.domain.Board;
import org.springframework.web.multipart.MultipartFile;

/**
 * 기능: 게시판 관련 비즈니스 로직 정의
 */
public interface BoardService {
    // 기존 메서드들
    List<Board> getAll();                 // 전체 게시글 조회
    Board getById(Long boardId);          // 특정 글 조회
    
    // 파일 업로드 기능을 위해 MultipartFile 파라미터 추가
    void insert(Board board, MultipartFile file); // 게시글 추가
    void update(Board board, MultipartFile file); // 게시글 수정
    void delete(Long boardId);            // 게시글 삭제

    // 페이징 관련 메서드
    List<Board> getByPage(int page, int pageSize);      // 특정 페이지 글 조회
    int getTotalCount();                            // 전체 글 수 조회
    int getTotalPages(int pageSize);                // 총 페이지 수 계산

    // 검색 기능 관련 메서드
    List<Board> searchByPage(String searchTerm, int page, int pageSize);
    int getTotalCount(String searchTerm);
    int getTotalPages(String searchTerm, int pageSize);
}