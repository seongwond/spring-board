package com.board.service.impl;

import com.board.domain.Comment;
import com.board.mapper.CommentMapper;
import com.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 기능: 댓글(Comment) 관련 비즈니스 로직 구현
 * 설명: CommentMapper를 사용한 데이터베이스 상호작용
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    /**
     * 기능: 특정 게시글에 속한 댓글 목록 조회
     * 설명: 매퍼의 findByBoardId() 메서드 호출, 댓글 목록 반환
     */
    @Override
    public List<Comment> getCommentsByBoardId(Long boardId) {
        return commentMapper.findByBoardId(boardId);
    }

    /**
     * 기능: 새로운 댓글 등록
     * 설명: 컨트롤러 전달 댓글 정보 매퍼에 전달, 데이터베이스 저장
     */
    @Override
    public void addComment(Comment comment) {
        commentMapper.insert(comment);
    }

    /**
     * 기능: 댓글 수정
     * 설명: 수정된 댓글 정보 매퍼에 전달, 데이터베이스 업데이트
     */
    @Override
    public void updateComment(Comment comment) {
        commentMapper.update(comment);
    }

    /**
     * 기능: 댓글 삭제
     * 설명: 댓글 ID 매퍼에 전달, 데이터베이스에서 해당 댓글 삭제
     */
    @Override
    public void deleteComment(Long commentId) {
        commentMapper.delete(commentId);
    }
    
    /**
     * 기능: 특정 댓글 ID로 댓글 정보 조회
     * @param commentId 조회 댓글 ID
     * @return 댓글 객체
     */
    @Override
    public Comment getCommentById(Long commentId) {
        return commentMapper.findById(commentId);
    }
}