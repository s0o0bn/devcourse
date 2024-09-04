package com.example.board.music.dto;

import com.example.board.music.entity.Music;
import lombok.Builder;

@Builder
public record MusicDetailDto(
        int no,
        String title,
        String artist,
        String genre,
        String memo,
        String author,
        int likeCount,
        String createdAt
) {
    public static MusicDetailDto from(Music music) {
        return MusicDetailDto.builder()
                .no(music.getNo())
                .title(music.getTitle())
                .artist(music.getArtist())
                .genre(music.getGenre())
                .memo(music.getMemo())
                .author(music.getAuthor())
                .likeCount(music.getLikeCount())
                .createdAt(music.getCreatedAt())
                .build();
    }
}
