package com.sam.blog.payloads;

import com.sam.blog.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDTO {

	private String title;
	private String content;
	private String imageName;
	private Date adddedDate;
	private CategoryDTO category;
	private UserDTO user;

}
