package com.board.service;

import java.util.List;

import com.board.domain.Board;

/**
 * BoardService 인터페이스
 * - 게시판 관련 비즈니스 로직을 정의
 */
public interface BoardService {
    // 기존 메서드들
    List<Board> getAll();                 // 전체 게시글 조회
    Board getById(Long boardId);          // 특정 글 조회
    void insert(Board board);             // 게시글 추가
    void update(Board board);             // 게시글 수정
    void delete(Long boardId);            // 게시글 삭제

    // 페이징 관련 메서드
    List<Board> getByPage(int page, int pageSize);      // 특정 페이지 글 조회
    int getTotalCount();                            // 전체 글 수 조회
    int getTotalPages(int pageSize);                // 총 페이지 수 계산

    // =========================================================
    // 새로 추가된 검색 기능 관련 메서드
    // =========================================================

    /**
     * 검색어를 포함한 게시글 목록을 페이징하여 조회
     * @param searchTerm 검색어 (제목 또는 작성자)
     * @param page 현재 페이지 번호
     * @param pageSize 한 페이지에 보여줄 게시글 수
     * @return 해당 검색 조건에 맞는 게시글 목록
     */
    List<Board> searchByPage(String searchTerm, int page, int pageSize);

    /**
     * 검색 조건에 맞는 게시글의 총 개수를 조회
     * @param searchTerm 검색어
     * @return 검색 조건에 맞는 게시글의 총 개수
     */
    int getTotalCount(String searchTerm);

    /**
     * 검색 조건에 맞는 총 페이지 수를 계산
     * @param searchTerm 검색어
     * @param pageSize 한 페이지에 보여줄 게시글 수
     * @return 검색 결과의 총 페이지 수
     */
    int getTotalPages(String searchTerm, int pageSize);
}