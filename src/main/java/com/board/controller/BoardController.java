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
            @RequestParam(defaultValue = "1") int page, // URL 파라미터로 페이지 번호 받기
            Model model) {

        int pageSize = 10; // 한 페이지에 보여줄 글 수

        // 페이지별 게시글 조회
        model.addAttribute("boards", boardService.getByPage(page, pageSize));

        // 총 페이지 수 계산
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", boardService.getTotalPages(pageSize));

        return "board"; // board.jsp
    }
    
    // 게시글 작성 폼 이동
    @GetMapping("/write")
    public String writeForm() {
        return "write"; // write.jsp
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

    // 게시글 삭제
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/board/list";
    }
    
    // 게시글 상세 보기
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Board board = boardService.getById(id);
        if (board == null) {
            model.addAttribute("message", "존재하지 않는 게시글입니다.");
            return "error"; // error.jsp
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd (E) HH:mm:ss")
                                                       .withLocale(Locale.KOREAN);

        String createdDate = board.getCreatedAt() != null ? board.getCreatedAt().format(formatter) : "";
        String modifiedDate = board.getUpdatedAt() != null ? board.getUpdatedAt().format(formatter) : "";

        model.addAttribute("board", board);
        model.addAttribute("createdDate", createdDate);
        model.addAttribute("modifiedDate", modifiedDate);

        return "detail"; // detail.jsp
    }


}
