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
    
    // ===================== 페이징 기능 =====================
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
}
