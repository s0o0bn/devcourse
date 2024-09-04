package com.example.board.music.dto;

import com.example.board.music.entity.Music;
import lombok.Builder;

@Builder
public record MusicItemDto(int no, String title, String artist, int likeCount, String createdAt) {
    public static MusicItemDto from(Music music) {
        return MusicItemDto.builder()
                .no(music.getNo())
                .title(music.getTitle())
                .artist(music.getArtist())
                .likeCount(music.getLikeCount())
                .createdAt(music.getCreatedAt())
                .build();
    }
}
