package com.example.jpa.domain.dto;

import com.example.jpa.domain.Board;
import lombok.Builder;

@Builder
public record BoardThumbnailDTO(int no, String title, String writer, int readCount, String createdAt) {
    public static BoardThumbnailDTO from(Board entity) {
        return BoardThumbnailDTO.builder()
                .no(entity.getNo())
                .title(entity.getTitle())
                .writer(entity.getWriter())
                .readCount(entity.getReadCount())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
