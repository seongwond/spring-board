package com.board.controller;

import com.board.domain.Board;
import com.board.domain.Member;
import com.board.service.BoardService;
import lombok.RequiredArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시판 목록 + 페이징
    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page, Model model, HttpSession session) {
        int pageSize = 10;
        model.addAttribute("boards", boardService.getByPage(page, pageSize));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", boardService.getTotalPages(pageSize));
        return "board";
    }

    @GetMapping("/write")
    public String writeForm(HttpSession session) {
        if (session.getAttribute("loginMember") == null) {
            return "redirect:/member/login";
        }
        return "write";
    }

    @PostMapping("/write")
    public String write(Board board, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        
        // 로그인 여부 체크
        if (loginMember == null) {
            return "redirect:/member/login"; // 로그인 페이지로 리다이렉트
        }

        board.setWriter(loginMember.getUsername());
        boardService.insert(board);
        return "redirect:/board/list";
    }


    // 게시글 수정 폼 (작성자만)
    @GetMapping("/edit/{boardId}")
    public String editForm(@PathVariable Long boardId, HttpSession session, Model model) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null) {
            return "redirect:/member/login";
        }

        Board board = boardService.getById(boardId);
        if (!loginMember.getUsername().equals(board.getWriter())) {
            return "redirect:/board/list";
        }

        model.addAttribute("board", board);
        return "edit";
    }

    // 게시글 수정 처리
    @PostMapping("/edit/{boardId}")
    public String edit(@PathVariable Long boardId, Board board, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null) {
            return "redirect:/member/login";
        }

        Board existing = boardService.getById(boardId);
        if (!loginMember.getUsername().equals(existing.getWriter())) {
            return "redirect:/board/list";
        }

        board.setBoardId(boardId);
        board.setWriter(existing.getWriter());
        boardService.update(board);
        return "redirect:/board/list";
    }

    // 게시글 삭제 (작성자만)
    @GetMapping("/delete/{boardId}")
    public String delete(@PathVariable Long boardId, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null) {
            return "redirect:/member/login";
        }

        Board board = boardService.getById(boardId);
        if (!loginMember.getUsername().equals(board.getWriter())) {
            return "redirect:/board/list";
        }

        boardService.delete(boardId);
        return "redirect:/board/list";
    }

    // 게시글 상세보기
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
