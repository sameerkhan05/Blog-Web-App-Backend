package com.sam.blog.services.impl;

import com.sam.blog.entities.User;
import com.sam.blog.exceptions.ResourceNotFoundException;
import com.sam.blog.payloads.UserDTO;
import com.sam.blog.repositories.UserRepositories;
import com.sam.blog.services.UserService;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

	private final UserRepositories userRepositories;
	private final ModelMapper modelMapper;

	public UserServiceImpl(UserRepositories userRepositories, ModelMapper modelMapper) {
		this.userRepositories = userRepositories;
		this.modelMapper = modelMapper;
	}

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		User saveUser = userRepositories.save(user);
		return modelMapper.map(saveUser, UserDTO.class);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Long userId) {
		User user = this.userRepositories.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		modelMapper.map(userDTO, user);
		User updateUser = this.userRepositories.save(user);
		return modelMapper.map(updateUser, UserDTO.class);
	}

	@Override
	public UserDTO getUserById(Long userId) {
		User user = this.userRepositories.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return modelMapper.map(user, UserDTO.class);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = this.userRepositories.findAll();
		List<UserDTO> userDTOList = users.stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
		return userDTOList;
	}

	@Override
	public void deleteUser(Long userId) {
		User user = this.userRepositories.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepositories.delete(user);
	}
}
