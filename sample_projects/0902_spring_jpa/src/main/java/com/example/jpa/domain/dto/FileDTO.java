package com.example.jpa.domain.dto;

import com.example.jpa.domain.File;
import lombok.Builder;

@Builder
public record FileDTO(int no, int bno, String originalName, String savedPath) {
    public static FileDTO from(File entity) {
        return FileDTO.builder()
                .no(entity.getNo())
                .bno(entity.getBoard().getNo())
                .originalName(entity.getFileName())
                .savedPath(entity.getFilePath())
                .build();
    }
}
