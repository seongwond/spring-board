package com.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.board.domain.Board;
import java.util.List;

@Mapper
public interface BoardMapper {
    List<Board> findAll();        // 기존 전체 조회
    Board findById(Long id);
    int insert(Board board);
    int update(Board board);
    int delete(Long id);

    // 페이징용 메서드 추가
    List<Board> findByPage(@Param("size") int size, @Param("offset") int offset);
    int countBoards(); // 전체 글 수 계산
}

