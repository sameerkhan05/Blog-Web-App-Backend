package com.sam.blog.services;

import com.sam.blog.payloads.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

	PostDTO createPost(PostDTO postDTO, Long userId, Long categoryId);

	PostDTO updatePost(PostDTO postDTO, Long postId);

	void deletePost(Long postId);

	List<PostDTO> getAllPost(Integer pageNumber, Integer pageSize);

	PostDTO getPostById(Long postId);

	List<PostDTO> getPostsByCategory(Long categoryId);

	List<PostDTO> getPostByUser(Long userId);

	List<PostDTO> searchPosts(String keyword);
}
