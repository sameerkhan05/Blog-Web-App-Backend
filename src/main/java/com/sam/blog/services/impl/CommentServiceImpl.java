package com.sam.blog.services.impl;

import com.sam.blog.entities.Comment;
import com.sam.blog.entities.Post;
import com.sam.blog.exceptions.ResourceNotFoundException;
import com.sam.blog.payloads.CommentDTO;
import com.sam.blog.repositories.CommentRepositories;
import com.sam.blog.repositories.PostRepo;
import com.sam.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

	private final PostRepo postRepo;
	private final CommentRepositories commentRepositories;
	private final ModelMapper modelMapper;

	public CommentServiceImpl(PostRepo postRepo, CommentRepositories commentRepositories, ModelMapper modelMapper) {
		this.postRepo = postRepo;
		this.commentRepositories = commentRepositories;
		this.modelMapper = modelMapper;
	}

	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Long postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		Comment comment = this.modelMapper.map(commentDTO, Comment.class);
		comment.setPost(post);
		Comment savedComment = this.commentRepositories.save(comment);
		return this.modelMapper.map(savedComment, CommentDTO.class);
	}

	@Override
	public void deleteComment(Long commentId) {
		Comment comment = this.commentRepositories.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));
		this.commentRepositories.delete(comment);
	}
}
