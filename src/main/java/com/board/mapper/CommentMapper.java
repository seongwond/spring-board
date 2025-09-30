package com.board.mapper;

import com.board.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CommentMapper {

    /**
     * 기능: 특정 게시글에 속한 모든 댓글을 조회
     * 설명: 게시글 ID(boardId)를 기준으로 해당 게시글의 모든 댓글을 가져옴
     * @param boardId 댓글을 조회할 게시글의 ID
     * @return 댓글 목록 (List<Comment>)
     */
    List<Comment> findByBoardId(Long boardId);

    /**
     * 기능: 새로운 댓글을 데이터베이스에 등록
     * @param comment 등록할 댓글 객체
     * @return 데이터베이스에 영향을 받은 행의 수 (1 또는 0)
     */
    int insert(Comment comment);

    /**
     * 기능: 기존 댓글의 내용을 수정
     * 설명: 수정할 댓글의 고유 ID와 새로운 내용을 받아서 업데이트
     * @param comment 수정할 댓글 객체 (commentId, content, updatedAt 포함)
     * @return 데이터베이스에 영향을 받은 행의 수 (1 또는 0)
     */
    int update(Comment comment);

    /**
     * 기능: 특정 댓글을 삭제
     * @param commentId 삭제할 댓글의 고유 ID
     * @return 데이터베이스에 영향을 받은 행의 수 (1 또는 0)
     */
    int delete(Long commentId);

    /**
     * 기능: 댓글의 고유 ID와 작성자 이름을 기준으로 특정 댓글을 조회
     * 설명: 댓글 수정/삭제 시 권한을 확인하는 데 사용
     * @param commentId 댓글의 고유 ID
     * @param writer 작성자 이름
     * @return 조건에 맞는 댓글 객체
     */
    Comment findByIdAndWriter(@Param("commentId") Long commentId, @Param("writer") String writer);
}