package com.board.controller;

import com.board.domain.Board;
import com.board.domain.Member;
import com.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import jakarta.servlet.http.HttpSession;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriUtils;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final String uploadDir = "C:/uploads/";

    @GetMapping("/list")
    public String list(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) String searchTerm,
            Model model,
            HttpSession session) {
        int pageSize = 10;
        List<Board> boards;
        int totalPages;
        
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            boards = boardService.searchByPage(searchTerm, page, pageSize);
            totalPages = boardService.getTotalPages(searchTerm, pageSize);
        } else {
            boards = boardService.getByPage(page, pageSize);
            totalPages = boardService.getTotalPages(pageSize);
        }
        
        model.addAttribute("boards", boards);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("searchTerm", searchTerm);
        
        return "board";
    }

    @GetMapping("/write")
    public String writeForm(HttpSession session) {
        if (session.getAttribute("loginMember") == null) {
            return "redirect:/member/login";
        }
        return "write";
    }

    /**
     * 기능: 게시글 등록
     * 설명: @RequestParam MultipartFile을 통해 파일을 받아 서비스로 전달
     */
    @PostMapping("/write")
    public String write(Board board, @RequestParam("file") MultipartFile file, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null) {
            return "redirect:/member/login";
        }
        board.setWriter(loginMember.getUsername());
        boardService.insert(board, file);
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

    /**
     * 기능: 게시글 수정
     * 설명: 수정 시 새로운 파일을 받아서 기존 파일을 교체
     */
    @PostMapping("/edit/{boardId}")
    public String edit(@PathVariable Long boardId, Board board, @RequestParam("file") MultipartFile file, HttpSession session) {
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
        boardService.update(board, file);
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

    /**
     * 기능: 게시글 상세보기
     * 설명: 게시글 정보와 댓글을 가져올 boardId를 뷰에 전달
     */
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

        // 댓글 기능을 위해 게시글 ID를 뷰에 추가로 전달합니다.
        model.addAttribute("boardId", boardId);
        
        return "detail";
    }

    /**
     * 기능: 파일 다운로드
     * 설명: URL에서 파일명을 받아 파일을 읽고 다운로드 응답을 보냅니다.
     */
    @GetMapping("/download/{boardId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long boardId) throws IOException {
        Board board = boardService.getById(boardId);
        if (board == null || board.getFilePath() == null) {
            return ResponseEntity.notFound().build();
        }

        Path path = Paths.get(board.getFilePath());
        Resource resource = new UrlResource(path.toUri());

        String encodedFileName = UriUtils.encode(board.getFileName(), "UTF-8");
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
            .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
            .body(resource);
    }
}