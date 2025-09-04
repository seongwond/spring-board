package com.board.controller;

import com.board.domain.Board;
import com.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 전체 게시글 조회
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("boards", boardService.getAll());
        return "board"; // board.jsp
    }

    // 게시글 등록 처리
    @PostMapping("/write")
    public String write(Board board) {
        boardService.insert(board);
        return "redirect:/board/list";
    }

    // 게시글 수정 폼 이동 (선택적으로 board.jsp 하나로 합칠 수도 있음)
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getById(id));
        return "edit"; // edit.jsp (추후 필요시)
    }

    // 게시글 수정 처리
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Board board) {
        board.setId(id);
        boardService.update(board);
        return "redirect:/board/list";
    }

    // 게시글 삭제 (GET 방식)
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/board/list";
    }
}
