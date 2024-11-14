package com.sam.blog.services;

import com.sam.blog.entities.User;
import com.sam.blog.payloads.UserDTO;

import java.util.List;

public interface UserService {
	UserDTO createUser(UserDTO user);
	UserDTO updateUser(UserDTO user, Long userId);
	UserDTO getUserById(Long userId);
	List<UserDTO> getAllUsers();
	void deleteUser(Long userId);
}
