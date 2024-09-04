package com.example.jpa.domain;

import com.example.jpa.domain.dto.BoardDTO;
import com.example.jpa.domain.dto.NewBoardDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post_tb")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    private String title;
    private String content;
    private String writer;
    private int readCount;
    @CreationTimestamp
    private String createdAt;

    @OneToMany(mappedBy = "board", orphanRemoval = true)
    private List<File> files;
    @OneToMany(mappedBy = "board", orphanRemoval = true)
    private List<Comment> comments;

    public static Board of(NewBoardDTO dto, String writer) {
        return Board.builder()
                .title(dto.title())
                .content(dto.content())
                .writer(writer)
                .build();
    }
}
