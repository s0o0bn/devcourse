package com.example.jpa.domain.dto;

import lombok.Builder;

@Builder
public record CommentDTO(int no, int bno, String writer, String content) {
}
