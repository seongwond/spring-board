package com.board.service;

import com.board.domain.Comment;
import java.util.List;

/**
 * 기능: 댓글(Comment) 관련 비즈니스 로직 정의
 * 설명: Controller와 CommentMapper 사이의 중간 역할 수행
 */
public interface CommentService {

    /**
     * 기능: 특정 게시글에 속한 댓글 목록 조회
     * @param boardId 댓글 조회 게시글 ID
     * @return 해당 게시글의 댓글 목록
     */
    List<Comment> getCommentsByBoardId(Long boardId);

    /**
     * 기능: 새로운 댓글 등록
     * @param comment 등록 댓글 객체
     */
    void addComment(Comment comment);

    /**
     * 기능: 댓글 수정
     * @param comment 수정 댓글 객체
     */
    void updateComment(Comment comment);

    /**
     * 기능: 댓글 삭제
     * @param commentId 삭제 댓글 ID
     */
    void deleteComment(Long commentId);

    /**
     * 기능: 특정 댓글 ID로 댓글 정보 조회
     * @param commentId 조회 댓글 ID
     * @return 댓글 객체
     */
    Comment getCommentById(Long commentId);
}