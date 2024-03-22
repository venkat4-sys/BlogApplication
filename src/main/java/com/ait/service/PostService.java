package com.ait.service;

import java.util.List;

import com.ait.dto.PostDto;
import com.ait.dto.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postdto);
	PostDto getPostById(Integer id);
	List<PostDto> getAllPosts();
	PostDto updatePost(Integer id,PostDto postdto);
	String deletePostById(Integer id);
	PostResponse getAllPostPagination(int pageSize,int pageNumber,String sortBy);
	

}
