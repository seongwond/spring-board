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
    
    // =========================================================
    // 새로 추가된 검색 기능 관련 메서드
    // =========================================================

    /**
     * 기능: 검색어에 해당하는 게시글을 페이지네이션하여 조회
     * 설명:
     * - @Param 어노테이션을 사용하여 XML 쿼리의 파라미터 이름과 메서드의 파라미터 이름을 연결
     * @param searchTerm 검색어 (제목 또는 작성자)
     * @param size 한 페이지에 보여줄 게시글 수
     * @param offset 시작 위치 (건너뛸 게시글 수)
     * @return Board 객체 리스트
     */
    List<Board> searchByPage(@Param("searchTerm") String searchTerm, @Param("size") int size, @Param("offset") int offset);

    /**
     * 기능: 검색어에 해당하는 게시글의 총 개수를 계산
     * @param searchTerm 검색어 (제목 또는 작성자)
     * @return 총 게시글 수
     */
    int countBoardsBySearch(@Param("searchTerm") String searchTerm);
}