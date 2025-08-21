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
    public Board getById(Long id) {
        return boardMapper.findById(id);
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
    public void delete(Long id) {
        boardMapper.delete(id);
    }
}
