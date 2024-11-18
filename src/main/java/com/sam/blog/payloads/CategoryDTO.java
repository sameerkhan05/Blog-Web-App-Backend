package com.sam.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {
	private Long categoryId;

	@NotBlank
	@Size(min = 4, message = "Title consist at least 4 character..")
	private String categoryTitle;

	@NotBlank
	@Size(min = 10, message = "Description must be of minimum 10 Character..")
	private String getCategoryDescription;
}
