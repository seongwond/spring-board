package com.board.mapper;

import com.board.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CommentMapper {

    /**
     * 기능: 특정 게시글에 속한 모든 댓글 조회
     * @param boardId 댓글 조회 게시글 ID
     * @return 댓글 목록
     */
    List<Comment> findByBoardId(Long boardId);

    /**
     * 기능: 새로운 댓글 등록
     * @param comment 등록 댓글 객체
     * @return 성공 시 1, 실패 시 0
     */
    int insert(Comment comment);

    /**
     * 기능: 댓글 수정
     * @param comment 수정 댓글 객체
     * @return 성공 시 1, 실패 시 0
     */
    int update(Comment comment);

    /**
     * 기능: 댓글 삭제
     * @param commentId 삭제 댓글 ID
     * @return 성공 시 1, 실패 시 0
     */
    int delete(Long commentId);
    
    /**
     * 기능: 특정 댓글 ID로 댓글 조회
     * @param commentId 조회 댓글 ID
     * @return 댓글 객체
     */
    Comment findById(Long commentId);

    /**
     * 기능: 댓글 ID와 작성자 이름으로 댓글 조회 (권한 확인용)
     * @param commentId 댓글 ID
     * @param writer 작성자 이름
     * @return 댓글 객체
     */
    Comment findByIdAndWriter(@Param("commentId") Long commentId, @Param("writer") String writer);
}