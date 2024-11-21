package com.sam.blog.repositories;

import com.sam.blog.entities.Category;
import com.sam.blog.entities.Post;
import com.sam.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {
	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

}
