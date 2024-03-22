package com.ait.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
	
	private Integer postId;

	//post title should not be null or empty
	//title should have at least 2 characters
	@NotEmpty
	@Size(min = 2,message = "post title should be atleast 2 characters")
	private String title;

	//post title should not be null or empty
		//title should have at least 10 characters
	@NotEmpty
	@Size(min = 10,message = "post descreption should have atleast 10 characters")
	private String description;

	//post title should not be null or empty
	@NotEmpty
	private String content;
	
	private List<CommentDto> comment;

}
