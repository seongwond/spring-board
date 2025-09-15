package com.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.board.domain.Board;
import java.util.List;

@Mapper
public interface BoardMapper {
    
    // 기존 전체 조회
    List<Board> findAll();

    // PK 기반 단건 조회
    Board findById(Long boardId);

    // 등록
    int insert(Board board);

    // 수정
    int update(Board board);

    // 삭제
    int delete(Long boardId);

    // 페이징용 조회
    List<Board> findByPage(@Param("size") int size, @Param("offset") int offset);

    // 전체 글 수 계산
    int countBoards();
}
