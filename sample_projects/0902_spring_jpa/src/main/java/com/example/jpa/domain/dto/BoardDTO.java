package com.example.jpa.domain.dto;


import com.example.jpa.domain.Board;
import lombok.Builder;

import java.util.List;

@Builder
public record BoardDTO (
    int no,
    String title,
    String content,
    String writer,
    int readCount,
    String createdAt,
    List<FileDTO> fileDTOList
) {
    public static BoardDTO from(Board entity) {
        return BoardDTO.builder()
                .no(entity.getNo())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .readCount(entity.getReadCount())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
