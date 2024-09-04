package com.example.jpa.controller;

import com.example.jpa.domain.dto.CommentDTO;
import com.example.jpa.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/{bno}/comment")
public class CommentController {
    private final CommentService service;

    @PostMapping("")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO comment,
                                                    @PathVariable("bno") int bno) {
        service.createComment(comment, bno);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable("bno") int bno) {
        return ResponseEntity.ok(service.getComments(bno));
    }

    @DeleteMapping("/{no}")
    public ResponseEntity<Void> deleteComment(@PathVariable("bno") int bno, @PathVariable("no") int no) {
        service.deleteComment(no);
        return ResponseEntity.noContent().build();
    }
}
