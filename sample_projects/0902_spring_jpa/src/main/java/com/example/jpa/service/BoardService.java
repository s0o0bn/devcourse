package com.example.jpa.service;

import com.example.jpa.domain.Board;
import com.example.jpa.domain.File;
import com.example.jpa.domain.dto.BoardDTO;
import com.example.jpa.domain.dto.BoardThumbnailDTO;
import com.example.jpa.domain.dto.FileDTO;
import com.example.jpa.domain.dto.NewBoardDTO;
import com.example.jpa.repository.BoardRepository;
import com.example.jpa.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;

    public BoardDTO getBoard(int bno) {
        Board board = boardRepository.findById(bno).orElseThrow();
        return BoardDTO.from(board);
    }

    @Transactional
    public void createBoardWithFiles(NewBoardDTO dto, String user, List<FileDTO> fileDTOs) {
        Board board = Board.of(dto, user);
        boardRepository.save(board);
        List<File> files = fileDTOs.stream()
                .map(f -> File.builder()
                        .fileName(f.originalName())
                        .filePath(f.savedPath())
                        .board(board)
                        .build())
                .toList();
        fileRepository.saveAll(files);
    }

    // 현재 보고자 하는 페이지 정보가 들어왔을 때, 실제 해당 페이지에 보여져야 하는 List<BoardDTO>를 포함해서 페이지가 총 몇개 필요하고, 하단 페이지 링크는 1~10 or 11~20 같은 페이지 구간 계산
    @Transactional
    public Page<BoardThumbnailDTO> getBoards(Pageable page) {
        Page<Board> boardPage = boardRepository.findAll(page);
        return boardPage.map(BoardThumbnailDTO::from);
    }

    public FileDTO getFileInfo(int fno) {
        File file = fileRepository.findById(fno).orElseThrow();
        return FileDTO.from(file);
    }
}
