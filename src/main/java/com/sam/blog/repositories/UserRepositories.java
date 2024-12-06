package com.sam.blog.repositories;

import com.sam.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.SimpleTimeZone;

public interface UserRepositories extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
