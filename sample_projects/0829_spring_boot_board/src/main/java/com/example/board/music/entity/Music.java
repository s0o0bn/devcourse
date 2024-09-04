package com.example.board.music.entity;

import com.example.board.music.dto.ModifiableMusicDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Music {
    private Integer no;
    private String title;
    private String artist;
    private String memo;
    private String genre;
    private String author;
    private int likeCount;
    private String createdAt;

    public static Music of(ModifiableMusicDto dto, String user) {
        return Music.builder()
                .title(dto.title())
                .artist(dto.artist())
                .genre(dto.genre())
                .author(user)
                .memo(dto.memo())
                .build();
    }

    public boolean isModifiable(String user) {
        return this.author.equals(user);
    }

    public void update(ModifiableMusicDto dto) {
        if (dto.title() != null && !dto.title().isEmpty() && !this.title.equals(dto.title())) {
            this.title = dto.title();
        }
        if (dto.artist() != null && !dto.artist().isEmpty() && !this.artist.equals(dto.artist())) {
            this.artist = dto.artist();
        }
        if (dto.genre() != null && !dto.genre().isEmpty() && !this.genre.equals(dto.genre())) {
            this.genre = dto.genre();
        }
        if (dto.memo() != null && !dto.memo().isEmpty() && !this.memo.equals(dto.memo())) {
            this.memo = dto.memo();
        }
    }
}
