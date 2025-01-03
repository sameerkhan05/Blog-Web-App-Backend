package com.sam.blog.controllers;

import com.sam.blog.payloads.ApiResponse;
import com.sam.blog.payloads.UserDTO;
import com.sam.blog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
		UserDTO createdUser = userService.createUser(userDTO);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> getAllUsers = userService.getAllUsers();
		return ResponseEntity.ok(getAllUsers);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Long userId) {
		UserDTO updateUser = userService.updateUser(userDTO, userId);
		return ResponseEntity.ok(updateUser);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
		UserDTO getUserById = userService.getUserById(userId);
		return ResponseEntity.ok(getUserById);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
		ApiResponse response = new ApiResponse(
				"User deleted successfully",
				true,
				"User",
				"id",
				userId,
				LocalDateTime.now(),
				"USER_DELETED"
		);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
