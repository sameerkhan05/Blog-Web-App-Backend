package com.sam.blog.controllers;

import com.sam.blog.payloads.ApiResponse;
import com.sam.blog.payloads.PostDTO;
import com.sam.blog.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Long userId, @PathVariable Long categoryId) {
		PostDTO createPost = this.postService.createPost(postDTO, userId, categoryId);
		return new ResponseEntity<PostDTO>(createPost, HttpStatus.CREATED);
	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable Long userId) {
		List<PostDTO> posts = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable Long categoryId) {
		List<PostDTO> posts = this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<List<PostDTO>> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize
	) {
		List<PostDTO> allPost = this.postService.getAllPost(pageNumber, pageSize);
		return new ResponseEntity<List<PostDTO>>(allPost, HttpStatus.OK);
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Long postId) {
		PostDTO postDTO = this.postService.getPostById(postId);
		return new ResponseEntity<PostDTO>(postDTO, HttpStatus.OK);
	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId) {
		this.postService.deletePost(postId);
		ApiResponse deleteResponse = new ApiResponse(
				"Post deleted successfully",
				true,
				"Post",
				"id",
				postId,
				LocalDateTime.now(),
				"POST_DELETED"
		);
		return new ResponseEntity<>(deleteResponse, HttpStatus.OK);
	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable Long postId) {
		PostDTO updatePost = this.postService.updatePost(postDTO, postId);
		return new ResponseEntity<PostDTO>(updatePost, HttpStatus.OK);
	}

}
