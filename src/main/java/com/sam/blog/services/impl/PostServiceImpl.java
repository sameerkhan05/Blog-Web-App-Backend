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
import java.util.stream.Collectors;

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
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());
		post.setImageName(postDTO.getImageName());
		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDTO.class);
	}

	@Override
	public void deletePost(Long postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		this.postRepo.delete(post);
	}

	@Override
	public List<PostDTO> getAllPost() {
		List<Post> allPosts = this.postRepo.findAll();
		List<PostDTO> postDTOS = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDTOS;
	}

	@Override
	public PostDTO getPostById(Long postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));

		return this.modelMapper.map(post, PostDTO.class);
	}

	@Override
	public List<PostDTO> getPostsByCategory(Long categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		List<Post> posts = this.postRepo.findByCategory(category);
		List<PostDTO> postDTOS = posts.stream().map((post) -> this.modelMapper
						.map(post, PostDTO.class))
				.collect(Collectors.toList());
		return postDTOS;
	}

	@Override
	public List<PostDTO> getPostByUser(Long userId) {
		User user = this.userRepositories.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDTO> postDTOS = posts.stream().map((post) -> this.modelMapper
						.map(post, PostDTO.class))
				.collect(Collectors.toList());
		return postDTOS;
	}

	@Override
	public List<PostDTO> searchPosts(String keyword) {
		return null;
	}
}
