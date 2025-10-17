package com.board.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.board.domain.Board;
import com.board.mapper.BoardMapper;
import com.board.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;
    
    // 파일이 저장될 기본 경로 설정 (실제 경로에 맞게 수정 필요)
    private final String uploadDir = "C:/dev/workspace/spring-board/src/main/resources/static/file";

    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }


    @Override
    public List<Board> getAll() {
        return boardMapper.findAll();
    }

    @Override
    public Board getById(Long boardId) {
        return boardMapper.findById(boardId);
    }

    /**
     * 기능: 새로운 게시글 등록 + 파일 업로드 처리
     * 설명: MultipartFile을 받아 서버에 파일을 저장하고, 파일 정보를 Board 객체에 담아 DB에 저장
     */
    @Override
    public void insert(Board board, MultipartFile file) {
        // 파일이 비어있지 않을 경우에만 파일 업로드 처리
        if (!file.isEmpty()) {
            // 파일명 중복 방지를 위해 UUID를 사용
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            // 업로드 경로와 파일명을 합쳐 저장할 파일 객체 생성
            File dest = new File(uploadDir, fileName);
            
            try {
                // 파일을 지정된 경로에 실제로 저장
                file.transferTo(dest);
                // 파일 정보를 Board 객체에 설정
                board.setFileName(file.getOriginalFilename());
                board.setFilePath(dest.getAbsolutePath());
            } catch (IOException e) {
                // 파일 저장 실패 시 예외 처리
                throw new RuntimeException("파일 업로드 실패", e);
            }
        }
        boardMapper.insert(board);
    }
    
    /**
     * 기능: 게시글 수정 + 파일 업로드/교체 처리
     * 설명: 새로운 파일이 업로드되면 기존 파일을 삭제하고 새 파일로 교체
     */
    @Override
    public void update(Board board, MultipartFile file) {
        // 새로운 파일이 업로드되었는지 확인
        if (!file.isEmpty()) {
            // 기존 파일 삭제
            Board existingBoard = boardMapper.findById(board.getBoardId());
            if (existingBoard != null && existingBoard.getFilePath() != null) {
                File existingFile = new File(existingBoard.getFilePath());
                if (existingFile.exists()) {
                    existingFile.delete();
                }
            }
            // 새 파일 업로드
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            File dest = new File(uploadDir, fileName);
            try {
                file.transferTo(dest);
                board.setFileName(file.getOriginalFilename());
                board.setFilePath(dest.getAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException("파일 업로드 실패", e);
            }
        }
        boardMapper.update(board);
    }

    @Override
    public void delete(Long boardId) {
        boardMapper.delete(boardId);
    }
    
    @Override
    public List<Board> getByPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize; // 몇 개 건너뛸지 계산
        return boardMapper.findByPage(pageSize, offset);
    }

    @Override
    public int getTotalCount() {
        return boardMapper.countBoards();
    }

    @Override
    public int getTotalPages(int pageSize) {
        int totalCount = getTotalCount();
        return (int) Math.ceil((double) totalCount / pageSize);
    }
    
    @Override
    public List<Board> searchByPage(String searchTerm, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return boardMapper.searchByPage(searchTerm, pageSize, offset);
    }

    @Override
    public int getTotalCount(String searchTerm) {
        return boardMapper.countBoardsBySearch(searchTerm);
    }

    @Override
    public int getTotalPages(String searchTerm, int pageSize) {
        int totalCount = getTotalCount(searchTerm);
        return (int) Math.ceil((double) totalCount / pageSize);
    }
}