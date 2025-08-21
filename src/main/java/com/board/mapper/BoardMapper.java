package com.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.board.domain.Board;
import java.util.List;

@Mapper
public interface BoardMapper {
    List<Board> findAll();        // XML의 <select id="findAll">와 일치
    Board findById(Long id);      // XML의 <select id="findById">
    int insert(Board board);      // XML의 <insert id="insert">
    int update(Board board);      // XML의 <update id="update">
    int delete(Long id);          // XML의 <delete id="delete">
}
