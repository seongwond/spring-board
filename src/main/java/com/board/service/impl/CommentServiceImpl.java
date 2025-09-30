package com.board.service.impl;

import com.board.domain.Comment;
import com.board.mapper.CommentMapper;
import com.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 기능: 댓글(Comment) 관련 비즈니스 로직을 구현하는 서비스 구현체
 * 설명: CommentMapper를 사용하여 데이터베이스와 상호작용
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    /**
     * 기능: 특정 게시글에 속한 댓글 목록을 조회
     * 설명: 매퍼의 findByBoardId() 메서드를 호출하여 댓글 목록을 반환
     */
    @Override
    public List<Comment> getCommentsByBoardId(Long boardId) {
        return commentMapper.findByBoardId(boardId);
    }

    /**
     * 기능: 새로운 댓글을 등록
     * 설명: 컨트롤러에서 전달받은 댓글 정보를 매퍼에 넘겨 데이터베이스에 저장
     */
    @Override
    public void addComment(Comment comment) {
        commentMapper.insert(comment);
    }

    /**
     * 기능: 댓글을 수정
     * 설명: 수정된 댓글 정보를 매퍼에 넘겨 데이터베이스를 업데이트
     */
    @Override
    public void updateComment(Comment comment) {
        commentMapper.update(comment);
    }

    /**
     * 기능: 댓글을 삭제
     * 설명: 댓글 ID를 매퍼에 넘겨 데이터베이스에서 해당 댓글을 삭제
     */
    @Override
    public void deleteComment(Long commentId) {
        commentMapper.delete(commentId);
    }
}