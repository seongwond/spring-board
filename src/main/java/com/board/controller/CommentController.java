package com.board.controller;

import com.board.domain.Comment;
import com.board.domain.Member;
import com.board.service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 기능: 댓글(Comment) 관련 HTTP 요청을 처리하는 컨트롤러
 * 설명: RESTful API 형태로 댓글 생성, 조회, 수정, 삭제 기능을 제공합니다.
 */
@RestController // @ResponseBody가 자동으로 포함되어 JSON 형태로 응답합니다.
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 기능: 특정 게시글의 모든 댓글 목록을 조회
     * @param boardId 댓글을 조회할 게시글의 ID
     * @return 댓글 목록과 HTTP 상태 코드
     */
    @GetMapping("/{boardId}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long boardId) {
        List<Comment> comments = commentService.getCommentsByBoardId(boardId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    /**
     * 기능: 새로운 댓글을 등록
     * 설명:
     * - @RequestBody: 클라이언트에서 JSON 형태로 전송된 데이터를 Comment 객체로 변환합니다.
     * - HttpSession: 로그인된 사용자 정보를 가져와 작성자로 설정합니다.
     * @param comment 등록할 댓글 객체
     * @param session 현재 세션
     * @return 성공 여부와 HTTP 상태 코드
     */
    @PostMapping
    public ResponseEntity<String> addComment(@RequestBody Comment comment, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null) {
            return new ResponseEntity<>("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED); // 401 Unauthorized
        }

        comment.setWriter(loginMember.getUsername());
        commentService.addComment(comment);
        return new ResponseEntity<>("댓글 등록 성공", HttpStatus.CREATED); // 201 Created
    }

    /**
     * 기능: 댓글을 수정
     * @param commentId 수정할 댓글의 ID
     * @param comment 수정할 내용이 담긴 댓글 객체
     * @param session 현재 세션
     * @return 성공 여부와 HTTP 상태 코드
     */
    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentId, @RequestBody Comment comment, HttpSession session) {
        // TODO: 댓글 수정 권한 확인 로직 추가 예정
        comment.setCommentId(commentId);
        commentService.updateComment(comment);
        return new ResponseEntity<>("댓글 수정 성공", HttpStatus.OK);
    }

    /**
     * 기능: 댓글을 삭제
     * @param commentId 삭제할 댓글의 ID
     * @param session 현재 세션
     * @return 성공 여부와 HTTP 상태 코드
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, HttpSession session) {
        // TODO: 댓글 삭제 권한 확인 로직 추가 예정
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("댓글 삭제 성공", HttpStatus.OK);
    }
}