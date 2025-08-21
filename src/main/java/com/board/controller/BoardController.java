package com.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.board.domain.Board;
import com.board.service.BoardService;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 전체 게시글 조회
    @GetMapping("/list")
    public List<Board> getAllBoards() {
        return boardService.getAll();
    }

    // 특정 게시글 조회
    @GetMapping("/{id}")
    public Board getBoardById(@PathVariable Long id) {
        return boardService.getById(id);
    }

    // 새 게시글 추가
    @PostMapping("/write")
    public Board createBoard(@RequestBody Board board) {
        boardService.insert(board);
        return board;
    }

    // 게시글 수정
    @PutMapping("/edit/{id}")
    public Board updateBoard(@PathVariable Long id, @RequestBody Board board) {
        board.setId(id);
        boardService.update(board);
        return board;
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{id}")
    public String deleteBoard(@PathVariable Long id) {
        boardService.delete(id);
        return "Deleted board with id: " + id;
    }
}
