package com.sam.blog.services;

import com.sam.blog.payloads.CommentDTO;


public interface CommentService {
	CommentDTO createComment(CommentDTO commentDTO, Long postId);

	void deleteComment(Long commentId);
}
