package com.board.controller;

import com.board.domain.Board;
import com.board.domain.Member;
import com.board.service.BoardService;
import lombok.RequiredArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;
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

 // 게시판 목록 + 페이징 + 검색
    @GetMapping("/list")
    public String list(
            // HTTP 요청에서 'page' 파라미터를 받아옵니다. 파라미터가 없으면 기본값으로 1을 사용합니다.
            @RequestParam(defaultValue = "1") int page,
            // HTTP 요청에서 'searchTerm' 파라미터를 받아옵니다. 이 파라미터는 필수가 아닙니다.
            @RequestParam(required = false) String searchTerm,
            Model model,
            HttpSession session) {
        
        int pageSize = 10;
        List<Board> boards;
        int totalPages;
        
        // 검색어가 비어있지 않은지 확인하는 조건문입니다.
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            // 검색어가 있을 경우, 검색 기능이 포함된 서비스 메서드를 호출합니다.
            boards = boardService.searchByPage(searchTerm, page, pageSize);
            totalPages = boardService.getTotalPages(searchTerm, pageSize);
        } else {
            // 검색어가 없을 경우, 기존의 전체 목록 조회 서비스 메서드를 호출합니다.
            boards = boardService.getByPage(page, pageSize);
            totalPages = boardService.getTotalPages(pageSize);
        }
        
        // 뷰(JSP)로 전달할 데이터를 Model 객체에 담습니다.
        model.addAttribute("boards", boards); // 게시글 목록
        model.addAttribute("currentPage", page); // 현재 페이지 번호
        model.addAttribute("totalPages", totalPages); // 총 페이지 수
        model.addAttribute("searchTerm", searchTerm); // 사용자가 입력한 검색어 (뷰에 다시 표시하기 위함)
        
        // 'board.jsp' 뷰 파일을 반환하여 화면을 렌더링합니다.
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
