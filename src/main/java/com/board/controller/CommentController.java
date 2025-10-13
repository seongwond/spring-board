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
 * 기능: 댓글(Comment) 관련 HTTP 요청 처리
 * 설명: RESTful API 형태로 댓글 생성, 조회, 수정, 삭제 기능 제공
 */
@RestController // JSON 형태로 응답 반환
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 기능: 특정 게시글의 모든 댓글 목록 조회
     * @param boardId 댓글 조회 게시글 ID
     * @return 댓글 목록과 HTTP 상태 코드
     */
    @GetMapping("/{boardId}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long boardId) {
        List<Comment> comments = commentService.getCommentsByBoardId(boardId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    /**
     * 기능: 새로운 댓글 등록
     * 설명:
     * - @RequestBody: 클라이언트 JSON 데이터를 Comment 객체로 변환
     * - HttpSession: 로그인 사용자 정보로 작성자 설정
     * @param comment 등록 댓글 객체
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
     * 기능: 댓글 수정
     * 설명: 로그인 사용자가 자신의 댓글만 수정하도록 권한 확인
     * @param commentId 수정 댓글 ID
     * @param comment 수정 내용이 담긴 댓글 객체
     * @param session 현재 세션
     * @return 성공 여부와 HTTP 상태 코드
     */
    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentId, @RequestBody Comment comment, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null) {
            return new ResponseEntity<>("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED); // 401 Unauthorized
        }

        // 수정 권한 확인: 로그인 사용자가 해당 댓글 작성자인지 확인
        Comment existingComment = commentService.getCommentById(commentId);
        if (existingComment == null || !existingComment.getWriter().equals(loginMember.getUsername())) {
            return new ResponseEntity<>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN); // 403 Forbidden
        }

        comment.setCommentId(commentId);
        comment.setWriter(loginMember.getUsername()); // 작성자 정보 위조 방지
        commentService.updateComment(comment);
        return new ResponseEntity<>("댓글 수정 성공", HttpStatus.OK);
    }

    /**
     * 기능: 댓글 삭제
     * 설명: 로그인 사용자가 자신의 댓글만 삭제하도록 권한 확인
     * @param commentId 삭제 댓글 ID
     * @param session 현재 세션
     * @return 성공 여부와 HTTP 상태 코드
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null) {
            return new ResponseEntity<>("로그인이 필요합니다.", HttpStatus.UNAUTHORIZED); // 401 Unauthorized
        }

        // 삭제 권한 확인: 로그인 사용자가 해당 댓글 작성자인지 확인
        Comment existingComment = commentService.getCommentById(commentId);
        if (existingComment == null || !existingComment.getWriter().equals(loginMember.getUsername())) {
            return new ResponseEntity<>("삭제 권한이 없습니다.", HttpStatus.FORBIDDEN); // 403 Forbidden
        }

        commentService.deleteComment(commentId);
        return new ResponseEntity<>("댓글 삭제 성공", HttpStatus.OK);
    }
}