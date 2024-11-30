package com.sam.blog.controllers;

import com.sam.blog.entities.Comment;
import com.sam.blog.payloads.ApiResponse;
import com.sam.blog.payloads.CommentDTO;
import com.sam.blog.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/")
public class CommentController {
	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable Long postId) {
		CommentDTO createdComment = this.commentService.createComment(commentDTO, postId);
		return new ResponseEntity<CommentDTO>(createdComment, HttpStatus.CREATED);
	}

	@DeleteMapping("/comments")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentId){
		this.commentService.deleteComment(commentId );
		ApiResponse deleteResponse = new ApiResponse(
				"Comment deleted successfully",
				true,
				"Comment",
				"commentId",
				commentId,
				LocalDateTime.now(),
				"COMMENT_DELETED"
		);
		return new ResponseEntity<>(deleteResponse, HttpStatus.OK);
	}
}
