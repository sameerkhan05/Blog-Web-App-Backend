package com.sam.blog.controllers;

import com.sam.blog.config.AppConstants;
import com.sam.blog.payloads.ApiResponse;
import com.sam.blog.payloads.PostDTO;
import com.sam.blog.payloads.PostResponse;
import com.sam.blog.services.FileService;
import com.sam.blog.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
	private final PostService postService;
	private final FileService fileService;
	@Value("${project.image}")
	private String path;

	public PostController(PostService postService, FileService fileService) {
		this.postService = postService;
		this.fileService = fileService;
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
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
	) {
		PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
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

	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDTO>> searchByTitle(@PathVariable("keywords") String keywords) {
		List<PostDTO> result = this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDTO>>(result, HttpStatus.OK);
	}

	//Image uploading Api
	@PostMapping("posts/image/upload/{postId}")
	public ResponseEntity<PostDTO> uploadPostImage(
			@RequestParam("image") MultipartFile image, @PathVariable Long postId) throws IOException {
		PostDTO postDTO = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path, image);
		postDTO.setImageName(fileName);
		PostDTO updatePost = this.postService.updatePost(postDTO, postId);
		return new ResponseEntity<PostDTO>(updatePost, HttpStatus.OK);
	}

	@GetMapping(value = "/posts/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void getImageFdb(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}


}
