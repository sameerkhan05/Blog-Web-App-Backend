package com.sam.blog.payloads;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

	private int id;

	@NotEmpty
	@Size(min = 5, message = "Username consist at least 4 characters..")
	private String name;

	@Email(message = "Enter a valid email address..")
	private String email;

	@NotEmpty
	@Size(min = 8, max = 10, message = "Password must be minimum 8 character")
	private String password;

	@NotEmpty
	private String about;
}
