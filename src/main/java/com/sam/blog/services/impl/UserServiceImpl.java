package com.sam.blog.services.impl;

import com.sam.blog.config.AppConstants;
import com.sam.blog.entities.Role;
import com.sam.blog.entities.User;
import com.sam.blog.exceptions.ResourceNotFoundException;
import com.sam.blog.payloads.UserDTO;
import com.sam.blog.repositories.RoleRepositories;
import com.sam.blog.repositories.UserRepositories;
import com.sam.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepositories userRepositories;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepositories roleRepositories;

	public UserServiceImpl(UserRepositories userRepositories, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleRepositories roleRepositories) {
		this.userRepositories = userRepositories;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
		this.roleRepositories = roleRepositories;
	}

	@Override
	public UserDTO registerNewUser(UserDTO userDTO) {
		User user = this.modelMapper.map(userDTO, User.class);
		//password encoding
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		//finding roles
		Role role = this.roleRepositories.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		User newUser = this.userRepositories.save(user);
		return this.modelMapper.map(newUser,UserDTO.class);
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
