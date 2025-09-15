package com.board.controller;

import com.board.domain.Board;
import com.board.service.BoardService;
import lombok.RequiredArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 전체 게시글 조회 + 페이징 적용
    @GetMapping("/list")
    public String list(
            @RequestParam(defaultValue = "1") int page,
            Model model) {

        int pageSize = 10;

        model.addAttribute("boards", boardService.getByPage(page, pageSize));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", boardService.getTotalPages(pageSize));

        return "board";
    }

    // 게시글 작성 폼 이동
    @GetMapping("/write")
    public String writeForm() {
        return "write";
    }

    // 게시글 등록 처리
    @PostMapping("/write")
    public String write(Board board) {
        boardService.insert(board);
        return "redirect:/board/list";
    }

    // 게시글 수정 폼 이동
    @GetMapping("/edit/{boardId}")
    public String editForm(@PathVariable Long boardId, Model model) {
        model.addAttribute("board", boardService.getById(boardId));
        return "edit";
    }

    // 게시글 수정 처리
    @PostMapping("/edit/{boardId}")
    public String edit(@PathVariable Long boardId, Board board) {
        board.setBoardId(boardId); // 수정됨
        boardService.update(board);
        return "redirect:/board/list";
    }

    // 게시글 삭제
    @GetMapping("/delete/{boardId}")
    public String delete(@PathVariable Long boardId) {
        boardService.delete(boardId);
        return "redirect:/board/list";
    }

    // 게시글 상세 보기
    @GetMapping("/detail/{boardId}")
    public String detail(@PathVariable Long boardId, Model model) {
        Board board = boardService.getById(boardId);
        if (board == null) {
            model.addAttribute("message", "존재하지 않는 게시글입니다.");
            return "error";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd (E) HH:mm:ss")
                                                       .withLocale(Locale.KOREAN);

        String createdDate = board.getCreatedAt() != null ? board.getCreatedAt().format(formatter) : "";
        String modifiedDate = board.getUpdatedAt() != null ? board.getUpdatedAt().format(formatter) : "";

        model.addAttribute("board", board);
        model.addAttribute("createdDate", createdDate);
        model.addAttribute("modifiedDate", modifiedDate);

        return "detail";
    }
}
