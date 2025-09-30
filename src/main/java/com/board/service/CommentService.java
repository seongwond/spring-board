package com.board.service;

import com.board.domain.Comment;
import java.util.List;

/**
 * 기능: 댓글(Comment) 관련 비즈니스 로직을 정의하는 서비스 인터페이스
 * 설명:
 * - Controller와 CommentMapper 사이의 중간 역할 수행
 * - 댓글 조회, 등록, 수정, 삭제 등의 비즈니스 로직 메서드 선언
 */
public interface CommentService {

    /**
     * 기능: 특정 게시글에 속한 댓글 목록을 조회
     * @param boardId 댓글을 조회할 게시글의 ID
     * @return 해당 게시글의 댓글 목록
     */
    List<Comment> getCommentsByBoardId(Long boardId);

    /**
     * 기능: 새로운 댓글을 등록
     * @param comment 등록할 댓글 객체
     */
    void addComment(Comment comment);

    /**
     * 기능: 댓글을 수정
     * @param comment 수정할 댓글 객체
     */
    void updateComment(Comment comment);

    /**
     * 기능: 댓글을 삭제
     * @param commentId 삭제할 댓글의 ID
     */
    void deleteComment(Long commentId);
}