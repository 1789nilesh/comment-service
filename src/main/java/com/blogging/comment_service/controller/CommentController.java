package com.blogging.comment_service.controller;

import com.blogging.comment_service.model.Comment;
import com.blogging.comment_service.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping
    public ResponseEntity<Comment> addComment(@PathVariable String postId, @RequestBody Comment comment) {
        comment.setPostId(postId);
        Comment savedComment = commentRepository.save(comment);
        return ResponseEntity.ok(savedComment);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getComments(@PathVariable String postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable String id) {
        commentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}