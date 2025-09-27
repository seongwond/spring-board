package com.board.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.board.domain.Board;
import com.board.mapper.BoardMapper;
import com.board.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    // 생성자 주입(Constructor Injection)
    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    // 기존 메서드들...
    @Override
    public List<Board> getAll() {
        return boardMapper.findAll();
    }

    @Override
    public Board getById(Long boardId) {
        return boardMapper.findById(boardId);
    }

    @Override
    public void insert(Board board) {
        boardMapper.insert(board);
    }

    @Override
    public void update(Board board) {
        boardMapper.update(board);
    }

    @Override
    public void delete(Long boardId) {
        boardMapper.delete(boardId);
    }
    
    @Override
    public List<Board> getByPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize; // 몇 개 건너뛸지 계산
        return boardMapper.findByPage(pageSize, offset);
    }

    @Override
    public int getTotalCount() {
        return boardMapper.countBoards();
    }

    @Override
    public int getTotalPages(int pageSize) {
        int totalCount = getTotalCount();
        return (int) Math.ceil((double) totalCount / pageSize);
    }
    
    // =========================================================
    // 새로 추가된 검색 기능 구현
    // =========================================================

    /**
     * 기능: 검색어를 포함한 게시글 목록을 페이징하여 조회하는 메서드.
     * BoardMapper의 searchByPage() 메서드를 호출합니다.
     */
    @Override
    public List<Board> searchByPage(String searchTerm, int page, int pageSize) {
        // SQL의 OFFSET 값(몇 개의 데이터를 건너뛸지)을 계산합니다.
        int offset = (page - 1) * pageSize;
        // 매퍼의 searchByPage() 메서드에 검색어와 페이징 정보를 전달하여 결과를 반환
        return boardMapper.searchByPage(searchTerm, pageSize, offset);
    }

    /**
     * 기능: 검색 조건에 맞는 게시글의 총 개수를 조회하는 메서드.
     * BoardMapper의 countBoardsBySearch() 메서드를 호출합니다.
     */
    @Override
    public int getTotalCount(String searchTerm) {
        // 매퍼의 countBoardsBySearch() 메서드를 호출하여 검색 조건에 맞는 게시글의 총 개수를 가져dha
        return boardMapper.countBoardsBySearch(searchTerm);
    }

    /**
     * 기능: 검색 조건에 맞는 총 페이지 수를 계산하는 메서드.
     * getTotalCount(searchTerm) 메서드를 호출하여 총 글 수를 가져온 뒤, 페이지 크기로 나누어 올림 처리합니다.
     */
    @Override
    public int getTotalPages(String searchTerm, int pageSize) {
        int totalCount = getTotalCount(searchTerm);
        // Math.ceil() 함수를 사용하여 소수점을 올림하여 총 페이지 수를 정확히 계산
        return (int) Math.ceil((double) totalCount / pageSize);
    }
}