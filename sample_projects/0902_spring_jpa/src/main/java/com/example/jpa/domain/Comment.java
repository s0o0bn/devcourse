package com.example.jpa.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment_tb")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    private String writer;
    private String content;
    @CreationTimestamp
    private String createdAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bno")
    private Board board;
}
