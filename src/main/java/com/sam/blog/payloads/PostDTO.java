package com.sam.blog.payloads;

import com.sam.blog.entities.Comment;
import com.sam.blog.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class PostDTO {

	private Long postId;
	private String title;
	private String content;
	private String imageName;
	private Date adddedDate;
	private CategoryDTO category;
	private UserDTO user;
	private Set<CommentDTO> comments = new HashSet<>();


}
