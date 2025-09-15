package com.board.service;

import java.util.List;

import com.board.domain.Board;

/**
 * BoardService 인터페이스
 * - 비즈니스 로직을 정의
 * - Controller와 Mapper 사이의 중간 역할
 */
public interface BoardService {
    List<Board> getAll();                 // 전체 게시글 조회
    Board getById(Long boardId);          // 특정 글 조회
    void insert(Board board);             // 게시글 추가
    void update(Board board);             // 게시글 수정
    void delete(Long boardId);            // 게시글 삭제

    // 페이징 관련 메서드
    List<Board> getByPage(int page, int pageSize); // 특정 페이지 글 조회
    int getTotalCount();                            // 전체 글 수 조회
    int getTotalPages(int pageSize);                // 총 페이지 수 계산
}
