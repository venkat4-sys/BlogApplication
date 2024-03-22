package com.ait.dto;

import java.util.List;

import lombok.Data;

@Data
public class PostResponse {
	private List<PostDto> content;
	private int pageNumber;
	
	private int pageSize;
	
	private int totalElements;
	
	private int totalPages;
	
	private boolean last;

}
