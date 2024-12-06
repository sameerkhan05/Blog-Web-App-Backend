package com.sam.blog.security;

import com.sam.blog.entities.User;
import com.sam.blog.exceptions.ResourceNotFoundException;
import com.sam.blog.repositories.UserRepositories;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
	private final UserRepositories userRepositories;

	public CustomUserDetailService(UserRepositories userRepositories) {
		this.userRepositories = userRepositories;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//here loading user data from db
		User user = this.userRepositories.findByEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "email : " + username, 0));
		System.out.println("Fetching user: " + username);
		return user;
	}
}
