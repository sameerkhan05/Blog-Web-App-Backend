package com.sam.blog.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;

	@Column(name = "post_title",length = 100,nullable = false)
	private String title;

	@Column(length = 1000)
	private String content;

	private String imageName;

	private Date addedDate;

	@ManyToOne
	private Category category;

	@ManyToOne
	private User user;
}
