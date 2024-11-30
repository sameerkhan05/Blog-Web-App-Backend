package com.sam.blog.repositories;

import com.sam.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepositories extends JpaRepository<Comment, Long> {

}
