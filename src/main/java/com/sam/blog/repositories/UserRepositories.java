package com.sam.blog.repositories;

import com.sam.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositories extends JpaRepository<User,Long> {
}
