package com.sam.blog.services.impl;

import com.sam.blog.entities.Category;
import com.sam.blog.entities.Post;
import com.sam.blog.entities.User;
import com.sam.blog.exceptions.ResourceNotFoundException;
import com.sam.blog.payloads.PostDTO;
import com.sam.blog.repositories.CategoryRepo;
import com.sam.blog.repositories.PostRepo;
import com.sam.blog.repositories.UserRepositories;
import com.sam.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

	private final PostRepo postRepo;
	private final ModelMapper modelMapper;
	private final UserRepositories userRepositories;
	private final CategoryRepo categoryRepo;

	public PostServiceImpl(PostRepo postRepo, ModelMapper modelMapper, UserRepositories userRepositories, CategoryRepo categoryRepo) {
		this.postRepo = postRepo;
		this.modelMapper = modelMapper;
		this.userRepositories = userRepositories;
		this.categoryRepo = categoryRepo;
	}

	@Override
	public PostDTO createPost(PostDTO postDTO, Long userId, Long categoryId) {
		User user = this.userRepositories.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));

		Post post = this.modelMapper.map(postDTO, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost = this.postRepo.save(post);

		return modelMapper.map(newPost, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO, Long postId) {
		return null;
	}

	@Override
	public PostDTO deletePost(Long postId) {
		return null;
	}

	@Override
	public List<PostDTO> getAllPost() {
		return null;
	}

	@Override
	public PostDTO getPostById(Long postId) {
		return null;
	}

	@Override
	public List<PostDTO> getPostsByCategory(Long categoryId) {
		return null;
	}

	@Override
	public List<PostDTO> getPostByUser(Long userId) {
		return null;
	}

	@Override
	public List<PostDTO> searchPosts(String keyword) {
		return null;
	}
}
