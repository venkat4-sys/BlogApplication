package com.ait.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {

	private int id;

	//name should not be null or empty
	@NotEmpty(message = "name should not be null or empty")
	private String name;

	//email should not be null or empty
	@NotEmpty(message = "email should not be null or empty")
	@Email
	private String email;

	//content body should not be null or empty
	//content body must be minimum 10 characters
	@NotEmpty(message = "content body should not be null or empty")
	@Size(min = 10,message = "comment body must be minimum 10 characters")
	private String body;

}
